package com.margaret;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Margaret on 11/28/2015.
 */
public class HoneyDataModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    ResultSet resultSet;
    Scanner s = new Scanner(System.in);

    public HoneyDataModel(ResultSet rs) {
        this.resultSet = rs;
        System.out.println("Now we should output the rs");
        output(rs);
        setup();
    }

    private void output(ResultSet rs) {
        ArrayList<Integer> weightContent = new ArrayList<Integer>();
        ArrayList<String> dateContent = new ArrayList<>();

        int rsCount = 0;
        try {
            while (this.resultSet.next()) {
                rsCount++;  // to make sure there is a result
                weightContent.add(rs.getInt(HoneyDatabase.WEIGHT_COLUMN));
                dateContent.add(rs.getString(HoneyDatabase.DATE_COLUMN));
            }
            if (rsCount == 0) {
                System.out.println("Nothing matches that solver entry.");
            } else { // then output the results once you know there is a result set
                int loopCount = 0;  // have to count again
                System.out.println("Choose one of the weights:");
                for (int resName : weightContent) {
                    loopCount++;
                    System.out.println(loopCount + ". " + resName);
                }
                System.out.println("Choose one of the dates:");
                for (String resName : dateContent) {
                    loopCount++;
                    System.out.println(loopCount + ". " + resName);
                }
            }
        }
        catch (Exception e){
            System.out.println("The output exception is " + e);
        }
    }

    private void setup(){

        countRows();

        try{
            colCount = resultSet.getMetaData().getColumnCount();

        } catch (SQLException se) {
            System.out.println("Error counting columns" + se);
        }

    }


    public void updateResultSet(ResultSet newRS){
        resultSet = newRS;
        setup();
    }


    private void countRows() {
        rowCount = 0;
        try {
            //Move cursor to the start...
            resultSet.beforeFirst();
            // next() method moves the cursor forward one row and returns true if there is another row ahead
            while (resultSet.next()) {
                rowCount++;

            }
            resultSet.beforeFirst();

        } catch (SQLException se) {
            System.out.println("Error counting rows " + se);
        }

    }
    @Override
    public int getRowCount() {
        countRows();
        return rowCount;
    }

    @Override
    public int getColumnCount(){
        return colCount;
    }

    @Override
    public Object getValueAt(int row, int col){
        try{
            //  System.out.println("get value at, row = " +row);
            resultSet.absolute(row+1);
            Object o = resultSet.getObject(col+1);
            return o.toString();
        }catch (SQLException se) {
            System.out.println(se);
            //se.printStackTrace();
            return se.toString();

        }
    }

    @Override
    //This is called when user edits an editable cell
    public void setValueAt(Object newValue, int row, int col) {

        //Make sure newValue is an integer AND that it is in the range of valid ratings

        int newRating;
        String newHarvestDate = "";  // TODO why is this called out as potentially not initialized and newRaing isn't?
        String newHarvestWeight;

        // TODO why do you need to validate if the value is coming from a dropdown?
//        try {
//            newRating = Integer.parseInt(newValue.toString());
//
//            // input validation
//            if (newRating < HoneyDatabase.MOVIE_MIN_RATING || newRating > HoneyDatabase.MOVIE_MAX_RATING) {
//                throw new NumberFormatException("Movie rating must be within the valid range");
//            }
//        } catch (NumberFormatException ne) {
//            //Error dialog box. First argument is the parent GUI component, which is only used to center the
//            // dialog box over that component. We don't have a reference to any GUI components here
//            // but are allowed to use null - this means the dialog box will show in the center of your screen.
//            JOptionPane.showMessageDialog(null, "Try entering a number between " + MovieDatabase.MOVIE_MIN_RATING + " " + MovieDatabase.MOVIE_MAX_RATING);
//            //return prevents the following database update code happening...
//            return;
//        }

        //This only happens if the new date is valid
        try {
            resultSet.absolute(row + 1);  // rows start at 1, JTable rows start at 0
            // update the data
            resultSet.updateString(HoneyDatabase.DATE_COLUMN, newHarvestDate);
            // update the row
            resultSet.updateRow();
            // method that updates the GUI - updates the JTable
            fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println("error changing rating " + e);
        }

    }


    @Override
    //We only want user to be able to edit column 2 - the rating column.
    //If this method always returns true, the whole table will be editable.

    //TODO how can we avoid using a magic number (if col==3) ) here? This code depends on column 3 being the rating.
    //This might change if we were to add more data to our table, for example storing names of people who created the review.
    //TODO To fix: look into table column models, and generate the number columns based on the columns found in the ResultSet.

    // another override
    public boolean isCellEditable(int row, int col){
        if (col == 3) {
            return true;
        }
        return false;
    }

    //Delete row, return true if successful, false otherwise
    public boolean deleteRow(int row){
        try {
            resultSet.absolute(row + 1);
            resultSet.deleteRow();
            //Tell table to redraw itself
            fireTableDataChanged();
            return true;
        }catch (SQLException se) {
            System.out.println("Delete row error " + se);
            return false;
        }
    }

    //returns true if successful, false if error occurs TODO how do I differentiate by table?
    public boolean insertRow() {

        try {
            System.out.println("Please enter year that hive was harvested:");
            String dateStr = s.next();
            System.out.println(dateStr);
            int date = Integer.parseInt(dateStr);
            System.out.println(date);
            System.out.println("Please enter number of pounds harvested:");
            String weightStr = s.next();
            int weight = Integer.parseInt(weightStr);
            System.out.println(weightStr);
            System.out.println(weight);

            //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
            resultSet.moveToInsertRow();
            resultSet.updateInt(HoneyDatabase.DATE_COLUMN, date);
            resultSet.updateInt(HoneyDatabase.WEIGHT_COLUMN, weight);
            resultSet.insertRow();  // adds a PK value
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding row");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public String getColumnName(int col){
        //Get from ResultSet metadata, which contains the database column names
        //TODO translate DB column names into something nicer for display, so "YEAR_RELEASED" becomes "Year Released"
        try {
            return resultSet.getMetaData().getColumnName(col + 1);
        } catch (SQLException se) {
            System.out.println("Error fetching column names" + se);
            return "?";
        }
    }
// maybe a hashmap of columns and titles

}
