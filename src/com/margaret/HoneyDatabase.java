package com.margaret;

import java.sql.*;

//first
//ALTER TABLE harvests
//ADD COLUMN harvestHiveID INT;
//then
//ALTER TABLE harvests MODIFY COLUMN hiveID INT NOT NULL, ADD CONSTRAINT hivesFKID FOREIGN KEY (hiveID) REFERENCES hives(hiveID);
public class HoneyDatabase {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "honey";
    // database created using command line for my sql
    static final String USER = "root";
    static final String PASS = "itecitec";

    static Statement statement = null;  // The object used for executing a static SQL statement and returning the results it produces.
    static Connection conn = null;
    static ResultSet rs = null;

    public final static String HIVES_TABLE_NAME = "hives";
    public final static String HIVES_PK_COL = "hiveID";

    public final static String LOCATION_COLUMN = "hiveLocation";
//    public final static String YEAR_COLUMN = "year_released";
//    public final static String RATING_COLUMN = "rating";

    public final static String HARVESTS_TABLE_NAME = "harvests";
    public final static String HARVESTS_PK_COL = "harvestID";

    public final static String DATE_COLUMN = "hDate";
    public final static String WEIGHT_COLUMN = "hWeight";
//    public final static String RATING_COLUMN = "rating";

    private static HoneyDataModel honeyDataModel;

    public static void main(String[] args) {


        //setup creates database (if it doesn't exist), opens connection, and adds sample data

        if (!setup()) {
            System.exit(-1);
        }

//        if (!loadAllHives()) {
//            System.exit(-1);
//        }
//
//        if (!loadAllHarvests()) {
//            System.exit(-1);
//        }
//        //If no errors, then start GUI
//        HoneyOptionsGUI tableGUI = new HoneyOptionsGUI(honeyDataModel);

        // if no errors, then create a join for the first task of inserting a row
        System.out.println("Now you can insert a row.");
        RowInsert rowInsert = new RowInsert();


    }

    //Create or recreate a ResultSet containing the whole database, and give it to movieDataModel
//    public static boolean loadAllHives(){
//
//        try{
//
//            if (rs!=null) {
//                rs.close();
//            }
//
//            String getAllData = "SELECT * FROM " + HIVES_TABLE_NAME;
//            rs = statement.executeQuery(getAllData); // TODO do I give the result sets different names so they both can exist?
//
//            if (honeyDataModel == null) {
//                //If no current honeyDataModel, then make one
////                honeyDataModel = new HoneyDataModel(rs);  // TODO do I need more than one honeyDataModel, or more than one model and one of each for each table?
//            } else {
//                //Or, if one already exists, update its ResultSet
//                honeyDataModel.updateResultSet(rs);
//            }
//
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Error loading or reloading hives");
//            System.out.println(e);
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//    public static boolean loadAllHarvests(){
//
//        // TODO I don't want two HoneyDataModels
//
//        try{
//
//            if (rs!=null) {
//                rs.close();
//            }
//
//            String getAllData = "SELECT * FROM " + HARVESTS_TABLE_NAME;
//            rs = statement.executeQuery(getAllData);
//
//            if (honeyDataModel == null) {
//                //If no current honeyDataModel, then make one
//                honeyDataModel = new HoneyDataModel(rs);
//            } else {
//                //Or, if one already exists, update its ResultSet
//                honeyDataModel.updateResultSet(rs);
//            }
//
//            return true;
//
//        } catch (Exception e) {
//            System.out.println("Error loading or reloading harvests");
//            System.out.println(e);
//            e.printStackTrace();
//            return false;
//        }
//
//    }

    public static boolean setup(){
        try {

            //Load driver class
            try {
//                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found. Quitting");
                System.out.println(cnfe);
                return false;
            }

            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);

            // The first argument ResultSet.TYPE_SCROLL_INSENSITIVE
            // allows us to move the cursor both forward and backwards through the RowSet
            // we get from this statement.

            // Another option is TYPE_SCROLL_SENSITIVE, which means the ResultSet will be updated when
            // something *else* changes the database. If your DB server was shared, you might need to be concerned about this.)

            // The TableModel will need to go forward and backward through the ResultSet.
            // by default, you can only move forward - it's less
            // resource-intensive than being able to go in both directions.
            // If you set one argument, you need the other.
            // The second one (CONCUR_UPDATABLE) means you will be able to change the ResultSet and these
            // changes will be made to the DB.... so long as you have a table with a primary key in it. (Otherwise
            // your database isn't able to definitively identify what has been changed).
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //Does the table exist? If not, create it.
            if (!hiveTableExists()) {

                //Create a table in the database with 3 columns: Movie title, year and rating
                String createTableSQL = "CREATE TABLE " + HIVES_TABLE_NAME + " (" + HIVES_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + LOCATION_COLUMN + " varchar(50)" + ")";
                System.out.println(createTableSQL);
                statement.executeUpdate(createTableSQL);

                System.out.println("Created hives table");

                String addDataSQL = "INSERT INTO " + HIVES_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location1')";
                System.out.println(addDataSQL);
                statement.executeUpdate(addDataSQL);
                addDataSQL = "INSERT INTO " + HIVES_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location2')";
                System.out.println(addDataSQL);
                statement.executeUpdate(addDataSQL);
                addDataSQL = "INSERT INTO " + HIVES_TABLE_NAME + "(" + LOCATION_COLUMN + ")" + " VALUES ('Location3')";
                System.out.println(addDataSQL);
                statement.executeUpdate(addDataSQL);
            }

            if (!harvestTableExists()) {
                //Create a table in the database with 3 columns: Movie title, year and rating
                String createTableSQL2 = "CREATE TABLE " + HARVESTS_TABLE_NAME + " (" + HARVESTS_PK_COL + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + DATE_COLUMN + " varchar(50), \n" + WEIGHT_COLUMN + " int)";
                System.out.println(createTableSQL2);
                statement.executeUpdate(createTableSQL2);

                System.out.println("Created harvests table");

                String addColumnSQL = "ALTER TABLE " + HARVESTS_TABLE_NAME + " ADD COLUMN " + HIVES_PK_COL + " INT;";
                System.out.println(addColumnSQL);
                statement.executeUpdate(addColumnSQL);

                System.out.println("Added hiveID column to harvests");


                // Add some test data - change to some movies you like, if desired
                //Example SQL: INSERT INTO movie_reviews ( title, year_released, rating ) VALUES ( 'Back to the future', 1985, 5)
                //Here we have to specify which columns the data will go into, because we want to omit the ID column and have MySQL fill it in for us.
                //But, since we are only adding 3 pieces of data for 4 columns, we have to specify which columns each data item is for.


                String addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date1', 100)";
                System.out.println(addDataSQL2);
                statement.executeUpdate(addDataSQL2);
                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date2', 200)";
                System.out.println(addDataSQL2);
                statement.executeUpdate(addDataSQL2);
                addDataSQL2 = "INSERT INTO " + HARVESTS_TABLE_NAME + "(" + DATE_COLUMN + ", " + WEIGHT_COLUMN + ")" + " VALUES ('date3', 300)";
                System.out.println(addDataSQL2);
                statement.executeUpdate(addDataSQL2);

                String addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + HIVES_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 1;";
                System.out.println(addFKData);
                System.out.println("UPDATE harvests SET hiveID = 2 WHERE harvestID = 3;");
                statement.executeUpdate(addFKData);
                addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + HIVES_PK_COL + " = 2 WHERE " + HARVESTS_PK_COL + " = 2;";
                statement.executeUpdate(addFKData);
                addFKData = "UPDATE " + HARVESTS_TABLE_NAME + " SET " + HIVES_PK_COL + " = 1 WHERE " + HARVESTS_PK_COL + " = 3;";
                statement.executeUpdate(addFKData);

                System.out.println("Added foreign key data to harvests");

                String constraintName = "hiveFKConst";
                String fkConstraint = "ALTER TABLE " + HARVESTS_TABLE_NAME + " MODIFY COLUMN " + HIVES_PK_COL + " INT NOT NULL, ADD CONSTRAINT " + constraintName + " FOREIGN KEY(" + HIVES_PK_COL + ") REFERENCES " + HIVES_TABLE_NAME + "(" + HIVES_PK_COL + ");";
                System.out.println(fkConstraint);
                statement.executeUpdate(fkConstraint);

//                ALTER TABLE harvests MODIFY COLUMN hiveID INT NOT NULL,
//                        ADD CONSTRAINT hiveFKConst
//                FOREIGN KEY(hiveID)
//                        REFERENCES hives(hiveID);

                System.out.println("Foreign key constraint added");

            }
            return true;

        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
            return false;
        }
    }

    private static boolean hiveTableExists() throws SQLException {

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + HIVES_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        if (tablesRS.next()) {    //If ResultSet has a next row, it has at least one row... that must be our table
            return true;
        }
        return false;

    }

    private static boolean harvestTableExists() throws SQLException {

        String checkTablePresentQuery = "SHOW TABLES LIKE '" + HARVESTS_TABLE_NAME + "'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        return tablesRS.next();

    }

//    //Close the ResultSet, statement and connection, in that order.
//    public static void shutdown(){
//        try {
//            if (rs != null) {
//                rs.close();
//                System.out.println("Result set closed");
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//
//        try {
//            if (statement != null) {
//                statement.close();
//                System.out.println("Statement closed");
//            }
//        } catch (SQLException se){
//            //Closing the connection could throw an exception too
//            se.printStackTrace();
//        }
//
//        try {
//            if (conn != null) {
//                conn.close();
//                System.out.println("Database connection closed");
//            }
//        }
//        catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }
}