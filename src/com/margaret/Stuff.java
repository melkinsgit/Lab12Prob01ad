package com.margaret;

//public class Main {
//
//    public static void main(String[] args) {
//
//    // instantiate HoneyOption GUI
//        HoneyManager.openDB();
//        HoneyOptionsGUI honeyOptionsGUI = new HoneyOptionsGUI();
//
//    }
//}

//
//createTable();
////        addElement();
//        //searchForElement();
//        }
//
//
//public static void createTable() {
//        try {
//        Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException cnfe) {
//        System.out.println("Can't instantiate driver class. Make sure driver string is correct.");
//        cnfe.printStackTrace(); // output the error information
//        System.exit(-1); // exit the program with non zero indicating problem
//        }
//
//        Statement statement = null;  // The object used for executing a static SQL statement and returning the results it produces.
//        Connection conn = null;
//
//        try {
//        conn = DriverManager.getConnection(DB_CONNECTION_URL, USER_NAME, PASS_WD); // make the connection
//        statement = conn.createStatement(); // Creates a Statement object for sending SQL statements to the database
//        String createCubesTable = "CREATE TABLE IF NOT EXISTS CubeTable (Rubiks_Solver VARCHAR (35), Solve_Time FLOAT)"; // String that will execute sql command to create a table with one column Rubiks_Solver that is a string of 35 characters or less and a second column Solve_Time that is a floating point number
//
//        statement.executeUpdate(createCubesTable); // execute update for strings that alter a table
//        System.out.println("Table created.");
//
//        String addToDatabase = "INSERT INTO CubeTable VALUES ('Cubestormer II robot', 5.270)";
//        statement.executeUpdate(addToDatabase);
//        addToDatabase = "INSERT INTO CubeTable VALUES ('Fakhri Raihaan (using his feet)', 27.93)";
//        statement.executeUpdate(addToDatabase);
//        addToDatabase = "INSERT INTO CubeTable VALUES ('Ruxin Liu (age 3)', 99.33)";
//        statement.executeUpdate(addToDatabase);
//        addToDatabase = "INSERT INTO CubeTable VALUES ('Mats Valk (human record holder)', 6.27)";
//        statement.executeUpdate(addToDatabase);
//
//        statement.close();
//        conn.close();
//
//        } catch (SQLException sqle) {
//        System.out.println("Can't connect to database.");
//        } catch (Exception e) {
//        System.out.println("Another error.");
//        } finally {
//        // you're done
//        }
//        } // end createTable method
//
//        }
