package com.margaret;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sn0173nd on 11/25/2015.
 */
public class HoneyGUI extends JFrame {
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JComboBox comboBox1;
    private JLabel pickHivesLabel;
    private JLabel startYearLabel;
    private JComboBox comboBox2;
    private JLabel endYearLabel;
    private JPanel rootPanel;

    // for GUI - buttons for hives; start year drop down; end year drop down; populate drop downs with sorted data from db

    public HoneyGUI () {
        super("Honey Database Search Options");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

}
