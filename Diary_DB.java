import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date; //to be able to use DATE as a data type

public class Diary_DB {
	// DB connection details
	private static final String URL = "jdbc:mysql://localhost:3306/diary";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Laurea123";

	private Connection connection = null;
	private PreparedStatement selectAllEntries = null;
	private PreparedStatement insertEntry = null;
	
	public Diary_DB()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database
			selectAllEntries = connection.prepareStatement("SELECT * FROM diary"); // Prepare the select query that gets all entries from the database or Diary table
			
			// the insert entry query as a prepared statement
			insertEntry = connection.prepareStatement("INSERT INTO diary VALUES (?,?,?)");

		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	}
	
	public ArrayList<Diary_Entries> getAllEntries()
	{
		ArrayList<Diary_Entries> results = null;
		ResultSet resultSet = null;
		
		try
		{
			resultSet = selectAllEntries.executeQuery(); // Execute the select query. resultSet contains the rows returned by the query
			results = new ArrayList<Diary_Entries>();
		
			while(resultSet.next()) // for each row returned by the select query...
			{
				// Initialize a new Entry object with the row's data. Add the Entry object to the results ArrayList in the Diary
				results.add(new Diary_Entries(
				resultSet.getDate("Current_day"), // get the value associated to the Current_Day column, representing entry's date
				resultSet.getString("Title"), // get the value associated to the Title column, representing entry's title
				resultSet.getString("Entry"))); // get the value associated to the Entry column, representing entry's text
			}
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		finally
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		} // end finally
		
		return results;
	} // end method getAllEntries
	
	/*
	 * Method that inserts a new Entry in the database / Diary
	 */
	public void addDiary_Entries(Date current_day, String title, String entry)
	{
		try
		{
			// Setting the values for the question marks '?' in the prepared statement
			insertEntry.setDate(1, current_day);
			insertEntry.setString(2, title);
			insertEntry.setString(3, entry);
			
			// result will contain the amount of updated rows. It should be 1.
			int result = insertEntry.executeUpdate(); 
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}

}
