package com.margaret;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/1/2015.
 */
public class RowInsert {

    Scanner s = new Scanner(System.in);
    private Connection conn = null;
    private ResultSet hiveSet;
    private ResultSet harvestSet;
    private Statement statement;

    public RowInsert (){
        System.out.println("creating a row insert obj");
        JoinQuery();
    }

    protected void JoinQuery (){
        System.out.println("In the method to get result sets");
        try {
            conn = DriverManager.getConnection(HoneyDatabase.DB_CONNECTION_URL + HoneyDatabase.DB_NAME, HoneyDatabase.USER, HoneyDatabase.PASS);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


//            System.out.println("Please enter the hive location:");
//            String location = s.next();
//            System.out.println("Please enter year that hive was harvested:");
//            String dateStr = s.next();
//            System.out.println("Please enter number of pounds harvested:");
//            String weightStr = s.next();
//            int date = Integer.parseInt(dateStr);
//            int weight = Integer.parseInt(weightStr);

            String allHiveData = "SELECT * FROM " + HoneyDatabase.HIVES_TABLE_NAME + ";";
            // SELECT hdate, hweight, harvests.hiveID, hiveLocation FROM hives, harvests WHERE hives.hiveID = harvests.hiveID;
            System.out.println(allHiveData);
            hiveSet = statement.executeQuery(allHiveData);

//            ArrayList<String> resContent = new ArrayList<String>();
//            int rsCount = 0;
//            try {
//                while (hiveSet.next()) {
//                    rsCount++;  // to make sure there is a result
//                    resContent.add(hiveSet.getString(HoneyDatabase.LOCATION_COLUMN));
//                }
//                if (rsCount == 0) {
//                    System.out.println("Nothing matches that solver entry.");
//                } else { // then output the results once you know there is a result set
//                    int loopCount = 0;  // have to count again
//                    System.out.println("Choose one of the dates:");
//                    for (String resName : resContent) {
//                        loopCount++;
//                        System.out.println(loopCount + ". " + resName);
//                    }
//                }
//            }
//            catch (Exception e){
//                System.out.println("The output exception is " + e);
//            }


            String allHarvestData = "SELECT * FROM " + HoneyDatabase.HARVESTS_TABLE_NAME + ";";
            // SELECT hdate, hweight, harvests.hiveID, hiveLocation FROM hives, harvests WHERE hives.hiveID = harvests.hiveID;
            System.out.println(allHarvestData);
            harvestSet = statement.executeQuery(allHarvestData);
            HoneyDataModel honeyDataModel = new HoneyDataModel(harvestSet);
            HiveDataModel hiveDataModel = new HiveDataModel(hiveSet);
//            insertSet.moveToInsertRow();
//            insertSet.updateInt(HoneyDatabase.DATE_COLUMN, date);
//            insertSet.updateInt(HoneyDatabase.WEIGHT_COLUMN, weight);
//            insertSet.updateString(HoneyDatabase.LOCATION_COLUMN, location);
//            insertSet.insertRow();  // adds a PK value
//            insertSet.moveToCurrentRow();
//            fireTableDataChanged();
            honeyDataModel.insertRow();
            hiveDataModel.insertRow();

        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }

    }
}
