import java.sql.Date; //to be able to use DATE as a data type

public class Diary_Entries { //variables for user input

	private Date current_day;
	private String title;
	private String entry;
		
	public Diary_Entries(Date aCurrent_Day, String aTitle, String aEntry)
	{
		this.current_day = aCurrent_Day;
		this.title = aTitle;
		this.entry = aEntry;
	}

		// Getters will be needed in file Diary_DB.java
	public Date getCurrent_day()
	{
			
		return this.current_day;
	}
		
	public String getTitle()
	{
		return this.title;
	}
		
	public String getEntry()
	{
		return this.entry;
	}
		
}
