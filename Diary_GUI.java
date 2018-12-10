import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date; // to be able to use DATE as data type
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane; 

public class Diary_GUI extends JFrame {
	
	final static int MAX_QTY = 10; // Code changed here 

	private Diary_DB diary_DB;
	private ArrayList<Diary_Entries> allEntries;
	private Diary_Entries currentEntries;
	
	static JTable tableDiary; 
	static JButton btnAddEntry; 
	private JButton btnDeleteEntry;
	
	public Diary_GUI(){
		super("My Diary");

		diary_DB = new Diary_DB();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
		setBounds(0,0,436,331); 
		setLocationRelativeTo(null); 

		JLabel lblEntries = new JLabel("Diary Entries:");
		lblEntries.setBounds(10, 11, 187, 14);
		getContentPane().add(lblEntries);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 240, 155);
		getContentPane().add(scrollPane);
		
		
		tableDiary = new JTable();
		scrollPane.setViewportView(tableDiary);
		tableDiary.setRowSelectionAllowed(true);
		tableDiary.setModel(new DefaultTableModel(
			new Object [MAX_QTY][3],  // Code changed here
			new String[] {"Date", "Title", "Entry"} // Code changed here
		));
		
		tableDiary.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		btnAddEntry = new JButton("Add Entry");
		btnAddEntry.setBounds(270, 89, 89, 23); // Code changed here
		getContentPane().add(btnAddEntry);
		
		populateTable();
		
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddEntry.addActionListener(commandHandler);
		
		btnDeleteEntry = new JButton("Delete Entry");
		btnDeleteEntry.setBounds(260, 131, 109, 21);
		getContentPane().add(btnDeleteEntry);
		
		tableDiary.addMouseListener(new java.awt.event.MouseAdapter() { //For Selecting and Reading Cell Entry Information

			public void mouseClicked(java.awt.event.MouseEvent e) {

				int row=tableDiary.rowAtPoint(e.getPoint());

				int col= tableDiary.columnAtPoint(e.getPoint());
				
				JOptionPane.showMessageDialog(null, "Info:  " + "" + tableDiary.getValueAt(row,col).toString());
			}

        });
				
		
		btnDeleteEntry.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel myPanel = new JPanel();
				int result = JOptionPane.showConfirmDialog(null, myPanel, "You want to delete this entry?", JOptionPane.OK_CANCEL_OPTION);
				
				if (result == JOptionPane.OK_OPTION) {
					try {
					
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary", "root", "Laurea123");
						int row = tableDiary.getSelectedRow();
						String value = (tableDiary.getModel().getValueAt(row,0).toString());
						String delete_query = "DELETE FROM diary WHERE current_day = ('" + value + "')";
						PreparedStatement ps = conn.prepareStatement(delete_query);
						ps.executeUpdate(); //updates in database
						DefaultTableModel model = (DefaultTableModel)tableDiary.getModel();
						model.removeRow(row); //updates in GUI table
						
						
					}
					catch (SQLException sqlException)
					{
						sqlException.printStackTrace();
					}


				}
				
			}
		});
	}

	private void populateTable(){
		
		allEntries = diary_DB.getAllEntries();
		
		for (int row=0; row<allEntries.size(); row++){
			
			currentEntries= allEntries.get(row);
			
			tableDiary.setValueAt(currentEntries.getCurrent_day(), row, 0);  
			tableDiary.setValueAt(currentEntries.getTitle(), row, 1);  
			tableDiary.setValueAt(currentEntries.getEntry(), row, 2); // Setting values to be added in columns to same row
		}
	}

	private class MyEventHandler implements ActionListener {
		public void actionPerformed (ActionEvent myEntry) {
			if (myEntry.getSource() == btnAddEntry){
				if (allEntries.size() < MAX_QTY){ // If current amount of Entries in the database is smaller than MAX_QTY entry is added
					getNewEntryFromUser();
					populateTable();
				}
				else{
					JOptionPane.showMessageDialog(null, "Your Diary is full and the entry can not be saved", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		}
	}
	
	private void getNewEntryFromUser() {
		JTextField current_date = new JTextField(10);
	    JTextField title = new JTextField(10);
	    JTextArea entry = new JTextArea(10,30);
	    // Code added here
 
	    JPanel myPanel = new JPanel();
	    
	    myPanel.add(new JLabel("Date"));
	    myPanel.add(current_date);
	    
	    myPanel.add(new JLabel("Title"));
	    myPanel.add(title);

	    myPanel.add(new JLabel("Write Entry"));
	    myPanel.add(entry);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Write a new Entry in your Diary", JOptionPane.OK_CANCEL_OPTION);
	    
	    if (result == JOptionPane.OK_OPTION) {
			// User input of "Date" as string and changed to DATE SQL format with Date.valueOf
	    	diary_DB.addDiary_Entries(Date.valueOf(current_date.getText()), title.getText(), entry.getText());  	
	    }
	}

	private static void showWellcomeDialog() { //welcome screen
		JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Welcome to your Diary! Press OK to Continue"));
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Diary", JOptionPane.DEFAULT_OPTION);

	}
	
	public static void main(String[] args) { //main method
		showWellcomeDialog();
		Diary_GUI frame = new Diary_GUI();
		frame.setVisible(true);
	}
}