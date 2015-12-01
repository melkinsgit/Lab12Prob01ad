//import java.sql.*;
//import java.util.LinkedList;
//
///**
// * Created by sn0173nd on 11/25/2015.
// */
//public class MakeHive {
//
//    protected String DB_NAME;
//    protected String TABLE_NAME;
//    protected String YEARS;
//    protected String END_YR_STR;
//
//    Statement statement = null;
//    Connection conn = null;
//    ResultSet rs = null;
//
//    PreparedStatement psInsert = null;
//    LinkedList<Statement> allStatements = new LinkedList<Statement>();
//
//    // constructor
//    MakeHive (String dbName){
//        this. DB_NAME = dbName;
//    }
//
//    // create table
//    protected void createTable() {
//        String createTableSQL = "CREATE TABLE  (day date, mintemp double, maxtemp double)";
//        try {
//            statement.executeUpdate(createTableSQL);
//            System.out.println("Created temp table");
//        } catch (SQLException sqle) {
//            //Seems the table already exists. Delete it and recreate it
//            if (sqle.getSQLState().startsWith("42")) {    //Error code for table already existing start with XO
//                System.out.println("Temp table appears to exist already, delete and recreate");
//                statement.executeUpdate(deleteTableSQL);
//                statement.executeUpdate(createTableSQL);
//            } else {
//                //Something else went wrong. If we can't create the table, no point attempting
//                //to run the rest of the code. Throw the exception again to be handled at the end of the program.
//                System.out.println("Got stuck in catch else " + sqle.getSQLState() + " is sql state");
//                throw sqle;
//            }
//        }
//    }
//
//    // insert row in table
//    protected void insertRow () {
//
//    }
//
//}
