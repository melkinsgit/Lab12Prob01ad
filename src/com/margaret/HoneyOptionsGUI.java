package com.margaret;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sn0173nd on 11/25/2015.
 */
public class HoneyOptionsGUI extends JFrame {
    private JComboBox optionsComboBox;
    private JLabel optionsLabel;
    private JPanel rootPanel;

    public HoneyOptionsGUI () {
        super("Honey Database Options");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final String start = "";
        final String addTable = "Add a Table";
        final String insertRow = "Add Honey Data";
        final String searchDB = "Search the Database";
        optionsComboBox.addItem(start);
        optionsComboBox.addItem(addTable);
        optionsComboBox.addItem(insertRow);
        optionsComboBox.addItem(searchDB);
    }
}
