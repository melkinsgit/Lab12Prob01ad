//package com.margaret;
//
//import java.sql.*;
//import java.util.LinkedList;
//
///**
// * Created by sn0173nd on 11/25/2015.
// */
//public class HoneyManager {
//
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/honey";
//    // database created using command line for my sql
//    static final String USER = "root";
//    static final String PASSWORD = "itecitec";
//
//    protected void openDB() {
//
////        Statement statement = null;
////        Connection conn = null;
////        ResultSet rs = null;
////
////        PreparedStatement psInsert = null;
////        LinkedList<Statement> allStatements = new LinkedList<Statement>();
//
//        try {
//            //Instantiate the driver
//            Class.forName(JDBC_DRIVER);
//
//        } catch (ClassNotFoundException cnfe) {
//            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
//            cnfe.printStackTrace();
//            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
//        }
//
//        try {
//
//            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
//            statement = conn.createStatement();
//            allStatements.add(statement);
//
//            //Create a table in the database. Stores today's date, and the min and max temperatures recorded.
//
////            String createTableSQL = "CREATE TABLE temp (day date, mintemp double, maxtemp double)";
//            String deleteTableSQL = "DROP TABLE temp";
////            try {
////                statement.executeUpdate(createTableSQL);
////                System.out.println("Created temp table");
////            } catch (SQLException sqle) {
////                //Seems the table already exists. Delete it and recreate it
////                if (sqle.getSQLState().startsWith("42")) {    //Error code for table already existing start with XO
////                    System.out.println("Temp table appears to exist already, delete and recreate");
////                    statement.executeUpdate(deleteTableSQL);
////                    statement.executeUpdate(createTableSQL);
////                } else {
////                    //Something else went wrong. If we can't create the table, no point attempting
////                    //to run the rest of the code. Throw the exception again to be handled at the end of the program.
////                    System.out.println("Got stuck in catch else " + sqle.getSQLState() + " is sql state");
////                    throw sqle;
////                }
////            }
///*
//            if (ticketMethods.ticketQueue.size() == 0) { //no tickets!
//                errorTextArea.setText("No tickets to display!");
//            }
//
//            for (Ticket ticket : ticketMethods.ticketQueue) {
//                toDisplay += ("Ticket ID: " + ticket.getTicketID() + " Issue: " + ticket.getDescription() + " Priority: " + ticket.getPriority() + " Reported by: " + ticket.getReporter() + " Reported on: " + ticket.getDateReported() + " Status: " + ticket.getStatus() + "\n");
//            }
//            System.out.println("all tickets are: " + toDisplay);
//            displayTextArea.setText(toDisplay);
//            String getAvgsSQL = "SELECT AVG(mintemp) AS rsMin, AVG(maxtemp) AS rsMax FROM temp";
//            rsMin = statement.executeQuery(getAvgsSQL);   */
//
//            // query total honey all years, including best year
//
//            // query hives with more honey this year than last and less honey than last year
//
//            // query hive totals for all years ranked
//
//            // call create table
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }
//
////    protected void createTable() {
////            String createTableSQL = "CREATE TABLE  (day date, mintemp double, maxtemp double)";
////            try {
////                statement.executeUpdate(createTableSQL);
////                System.out.println("Created temp table");
////            } catch (SQLException sqle) {
////                //Seems the table already exists. Delete it and recreate it
////                if (sqle.getSQLState().startsWith("42")) {    //Error code for table already existing start with XO
////                    System.out.println("Temp table appears to exist already, delete and recreate");
////                    statement.executeUpdate(deleteTableSQL);
////                    statement.executeUpdate(createTableSQL);
////                } else {
////                    //Something else went wrong. If we can't create the table, no point attempting
////                    //to run the rest of the code. Throw the exception again to be handled at the end of the program.
////                    System.out.println("Got stuck in catch else " + sqle.getSQLState() + " is sql state");
////                    throw sqle;
////                }
////            }
////        }
//
//            // call insert row
//
//    }
