package com.margaret;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by sn0173nd on 12/1/2015.
 */
public class RowInsert {

    Scanner s = new Scanner(System.in);
    private Connection conn = null;
    private ResultSet insertSet;
    private Statement statement;

    public RowInsert (){
        InsertRow();
    }

    protected void InsertRow (){
        try {
            conn = DriverManager.getConnection(HoneyDatabase.DB_CONNECTION_URL + HoneyDatabase.DB_NAME, HoneyDatabase.USER, HoneyDatabase.PASS);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


            System.out.println("Please enter the hive location:");
            String location = s.next();
            System.out.println("Please enter year that hive was harvested:");
            String dateStr = s.next();
            System.out.println("Please enter number of pounds harvested:");
            String weightStr = s.next();
            int date = Integer.parseInt(dateStr);
            int weight = Integer.parseInt(weightStr);

            String searchQuery = "SELECT " + HoneyDatabase.DATE_COLUMN + ", " + HoneyDatabase.WEIGHT_COLUMN + ", " + HoneyDatabase.HARVESTS_TABLE_NAME + "." + HoneyDatabase.HIVES_PK_COL + ", " + HoneyDatabase.LOCATION_COLUMN + " FROM " + HoneyDatabase.HIVES_TABLE_NAME + ", " + HoneyDatabase.HARVESTS_TABLE_NAME + " WHERE " + HoneyDatabase.HIVES_TABLE_NAME + "." + HoneyDatabase.HIVES_PK_COL + " = " + HoneyDatabase.HARVESTS_TABLE_NAME + "." + HoneyDatabase.HIVES_PK_COL + ";";
            // SELECT hdate, hweight, harvests.hiveID, hiveLocation FROM hives, harvests WHERE hives.hiveID = harvests.hiveID;
            System.out.println(searchQuery);
            insertSet = statement.executeQuery(searchQuery);

        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }

    }
}
