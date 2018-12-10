# Diary-App-with-Java-Swing
Diary App, Java Swing, GUI, SQL

# Description: 

The project consists of a Diary App, written with Java Swing for creating the GUI with persistent storage in a Database. This project was done as part of an Object Oriented Programming with Java course at Laurea UAS.

Also there's a SQL file containing the code for importing and creating a table in a Local Relational Database connected to the Diary App through a JDBC API in order to save/delete/read any entries done by the user. Additionally, Prepared Statements were used to ensure the query parameters are of proper data type and sanitized. The SQL file defines the data types as well as the primary key (no foreign key applicable).

JDBC supports different DBMS vendors and no other API is needed for connecting the Diary App to the Database. (more details can be found at:  https://docs.oracle.com/javase/tutorial/jdbc/overview/)

As Eclipse was the used IDE for this project and additional step was required for configuring eclipse to access MySQL with JDBC consisting of downloading and adding a MySQL JDBC Driver file (mysql-connectorjava-8.0.13.jar available at: https://dev.mysql.com/downloads/connector/j/)

# Installation instructions: 

1.- Download all 4 files.
2.- Select preferred Java IDE and SQL DBMS.
3.- Define/change to your own credentials for the Database Server.
4.- Import SQL file to your DBMS for creating the DB table.
5.- Make sure your Java IDE supports Swing.
6.- Configure your Java IDE to support MySQL JDBC Driver to be able to connect with the Database. (more details: https://docs.oracle.com/javase/tutorial/jdbc/overview/)
7.- Import or copy/paste the 3 remaining Java files to your IDE.
8.- Press play and ready for use/test.

# Usage: 

The Diary App was designed with WindowBuilder’s Design Tool. It starts with a welcome screen. Following screen is the actual "Diary" where entries can be Added by pressing "Add" button, Read by clicking specific cell and Deleted by preselecting row with mouse and pressing the "Delete" button. Adding and Deleting will add or delete entries from the GUI as well as the Database. Content in the Diary Table is automatically refreshed/updated.

Additional screenshots of the Diary GUI are included in a separate file named "Screenshots".

# Credits: 

Bianca Iacoviello bianca.iacoviello@student.laurea.fi or biancaiacoviello@gmail.com
