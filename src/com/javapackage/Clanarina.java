package com.javapackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Clanarina extends JFrame {

    JLabel label;
    ImageIcon imageIcon;

    JPanel panel;

    JTable jTable;

    JScrollPane jScrollPane;

    Clanarina(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);

        setComponents();

        this.setVisible(true);

    }
    public void setComponents(){

        jTable = new JTable(0,4);
        jTable.setPreferredSize(new Dimension(600,600));

        this.add(new JScrollPane(jTable));

        label = new JLabel();
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");
        label.setText("CLANARINE");
        label.setFont(new Font("Calibri",Font.PLAIN,22));
        label.setIcon(imageIcon);
        label.setIconTextGap(25);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(170,170));
        panel.setBackground(Color.white);
        panel.add(label);

        this.add(panel, BorderLayout.NORTH);

    }

    public void addRowToJtable(Object[] dataRow){

        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        model.addRow(dataRow);

    }




}


