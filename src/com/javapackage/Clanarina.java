package com.javapackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Clanarina extends JFrame {

    JLabel label;
    ImageIcon imageIcon;

    JPanel panel;

    Color lightBlue;

    JButton jButton;

    JTable jTable;
    DefaultTableModel model;

    JScrollPane jScrollPane;

    Clanarina(){



        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        //this.setLayout(null);
        setJtableButton();
        setComponents();
        //loadCsvDataFromFile("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\Clanovi.csv");





        this.setVisible(true);

    }
    public void setComponents(){

        String[] columnNames = {"Ime i prezime", "E-mail", "Broj telefona", "Spol", "Clanarina"};

        model = new DefaultTableModel(columnNames, 0);

        jTable = new JTable(model);
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

        lightBlue = new Color(0,0,182,155);


    }

    private void setJtableButton(){

        jButton = new JButton();
        jButton.setText("Natrag...");
        jButton.setForeground(lightBlue);
        jButton.setBounds(370,20,100,20);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame myFrame = new MyFrame();
                myFrame.setVisible(true);

                Clanarina.this.dispose();

            }
        });

        jButton.setFocusable(false);


        this.add(jButton);
    }

    public void addRowToJtable(Object[] dataRow){

        DefaultTableModel model = (DefaultTableModel)jTable.getModel();
        model.addRow(dataRow);

    }


    public void loadCsvDataFromFile(String filePath) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // clear existing rows

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split line on comma, trim spaces
                String[] data = line.split("\\s*,\\s*");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV: " + e.getMessage());
        }
    }


}


