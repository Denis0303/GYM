package com.javapackage;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.Enumeration;


public class MyFrame extends JFrame implements ActionListener {



    JPanel panel;
    JPanel panelMembership;
    JPanel panelGender;
    JLabel label;

    JLabel labelTwo;

    JLabel labelThree;
    ImageIcon imageIcon;
    ImageIcon imageIconTwo;
    JButton button;
    JButton buttonTwo;

    JButton buttonThree;

    TextField textField;
    TextField textfieldTwo;

    TextField textFieldThree;

    JPasswordField passwordField;

    JRadioButton spolMusko;
    JRadioButton spolZensko;
    ButtonGroup muskoZensko;

    JRadioButton mjesecna;
    JRadioButton godisnja;
    ButtonGroup clanarina;

    JCheckBox checkBoxOne, checkBoxTwo;

    JCheckBox checkBoxThree, checkBoxFour;

    Color lightBlue;

    MyFrame(){
        
        this.setTitle("Clanarina");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(getJMenuBar());
        this.setLocationRelativeTo(null);

        components();

        this.setVisible(true);
    }

    public void components(){
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");

        label = new JLabel(imageIcon);
        label.setIcon(imageIcon);
        label.setText("REGISTRACIJA");
        label.setHorizontalTextPosition(JLabel.CENTER); //Setting the text Center of Image
        label.setVerticalTextPosition(JLabel.BOTTOM); //Setting the text Bottom of image
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-25);


        label.setFont(new Font("Calibri",Font.PLAIN,22)); //FONT SIZE AND TYPE

        this.add(label);


        labelTwo = new JLabel();
        labelTwo.setText("SPOL");
        labelTwo.setVerticalAlignment(JLabel.CENTER);
        labelTwo.setHorizontalAlignment(JLabel.CENTER);
        labelTwo.setFont(new Font("Calibri",Font.PLAIN,22));

        this.add(labelTwo);

        labelThree = new JLabel();
        labelThree.setText("ČLANARINA");
        labelThree.setVerticalAlignment(JLabel.CENTER);
        labelThree.setHorizontalAlignment(JLabel.CENTER);
        labelThree.setFont(new Font("Calibri",Font.PLAIN,22));

        this.add(labelThree);

        createButton();


        buttonTwo = new JButton();
        //buttonTwo.addActionListener(this);
        buttonTwo.setBounds(100,500,100,50);
        buttonTwo.setText("PONIŠTI");
        buttonTwo.setFocusable(false);
        buttonTwo.addActionListener(e -> {
            textField.setText("");
            textfieldTwo.setText("");
            textFieldThree.setText("");
            muskoZensko.clearSelection();
            clanarina.clearSelection();


        });

        this.add(buttonTwo);

        lightBlue = new Color(0,0,182,155);

        buttonThree = new JButton();
        buttonThree.setText("Natrag..");
        buttonThree.setForeground(lightBlue);
        buttonThree.addActionListener( e ->  {
            new myRegister().setVisible(true);

            // Close the current frame
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonThree);
            if (currentFrame != null) {
                currentFrame.dispose();
            } else {
                System.out.println("Could not find the parent frame to close.");
            }


        });
        buttonThree.setBounds(370,20,100,20);
        buttonThree.setFocusable(false);

        this.add(buttonThree);

        textField = new TextField();
        textField.setBounds(135,200,100,30);
        textField.setText("Ime I Prezime");
        textField.setForeground(Color.gray);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Ime I Prezime")){
                    textField.setText("");
                    textField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                    textField.setForeground(Color.gray);
                    textField.setText("Ime I Prezime");
                }

            }
        });



        this.add(textField);



        textfieldTwo = new TextField();
        textfieldTwo.setBounds(245,200,100,30);
        textfieldTwo.setText("E-mail");
        textfieldTwo.setForeground(Color.gray);
        textfieldTwo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textfieldTwo.getText().equals("E-mail")){
                    textfieldTwo.setText("");
                    textfieldTwo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (textfieldTwo.getText().isEmpty()){
                    textfieldTwo.setForeground(Color.gray);
                    textfieldTwo.setText("E-mail");
                }

            }
        });

        this.add(textfieldTwo);


        textFieldThree = new TextField();
        textFieldThree.setBounds(355,200,100,30);
        textFieldThree.setText("Broj telefona");
        textFieldThree.setForeground(Color.gray);
        textFieldThree.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldThree.getText().equals("Broj telefona")){
                    textFieldThree.setText("");
                    textFieldThree.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if (textFieldThree.getText().isEmpty()){
                    textFieldThree.setForeground(Color.gray);
                    textFieldThree.setText("Broj telefona");
                }

            }
        });


        this.add(textFieldThree);


            passwordField = new JPasswordField();
            passwordField.setText("Lozinka");

            passwordField.setPreferredSize(new Dimension(100, 30));
            passwordField.setForeground(Color.gray);
            passwordField.setEchoChar((char) 0);

            passwordField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {


                    if (passwordField.getText().equals("Lozinka")) {

                        passwordField.setText(null);
                        passwordField.setEchoChar('*');
                        passwordField.setForeground(Color.black);

                    }


                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (passwordField.getText().isEmpty()) {
                        passwordField.setForeground(Color.gray);
                        passwordField.setText("Lozinka");
                        passwordField.setEchoChar((char) 0);


                    }

                }
            });


        this.add(passwordField);





            spolMusko = new JRadioButton("Muško");
            spolMusko.setBounds(160, 410, 120, 30);
            spolZensko = new JRadioButton("Žensko");
            spolZensko.setBounds(310, 410, 120, 30);
            spolZensko.addActionListener(this);
            spolMusko.addActionListener(this);
            this.add(spolMusko);
            this.add(spolZensko);

            muskoZensko = new ButtonGroup();
            muskoZensko.add(spolMusko);
            muskoZensko.add(spolZensko);


            mjesecna = new JRadioButton("Mjesečna 40€");
            mjesecna.setBounds(160, 300, 120, 30);
            godisnja = new JRadioButton("Godišnja 480€");
            godisnja.setBounds(310, 300, 120, 30);
            mjesecna.addActionListener(this);
            godisnja.addActionListener(this);
            this.add(mjesecna);
            this.add(godisnja);

            clanarina = new ButtonGroup();
            clanarina.add(mjesecna);
            clanarina.add(godisnja);










        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,250));
        //panel.setBorder(BorderFactory.createLineBorder(Color.red,7));
        panel.setBackground(Color.white);
        panel.add(label);

        this.add(panel, BorderLayout.NORTH);

        
        createMembershipPanel();
        createGenderPanel();
    }

    private void createButton() {
        button = new JButton();
        //button.addActionListener(this);

        button.setBounds(360,500,100,50);
        button.setText("POTVRDI");
        button.setFocusable(false);
        button.addActionListener(e -> {


            String membershipType = "";
            Enumeration<AbstractButton> buttons = clanarina.getElements();
            while (buttons.hasMoreElements()){
                AbstractButton btn = buttons.nextElement();
                if (btn.isSelected()){
                    membershipType = btn.getText();
                    break;

                }
            }

            if (textField.getText().trim().isEmpty() ||
                    textfieldTwo.getText().trim().isEmpty() ||
                    textFieldThree.getText().trim().isEmpty() ||
                    (!spolMusko.isSelected() && !spolZensko.isSelected()) ||
                    membershipType.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please fill in all fields and make all selections!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return; // stop here if invalid
            }






            String[] rowData = {
                    textField.getText(),
                    textfieldTwo.getText(),
                    textFieldThree.getText(),
                    spolMusko.isSelected() ? "M" : "Ž",
                    membershipType
            };


            try {
                File file = new File("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\Clanovi.csv");
                if (!file.exists()){
                    file.createNewFile();

                }


                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for (int i = 0; i < rowData.length; i++){
                    bufferedWriter.write(rowData[i]);
                    if (i < rowData.length - 1){
                        bufferedWriter.write(",");
                    }


                }

                bufferedWriter.newLine();


                bufferedWriter.close();
                fileWriter.close();

                // JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
                // parentFrame.dispose();



                JOptionPane.showMessageDialog(null,"Data exported");

                //this.dispose();


            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        this.add(button);

    }

   /* public void loadCsvDataFromFile(String filePath) {
        Clanarina clanarina1 = new Clanarina();
        DefaultTableModel model = (DefaultTableModel) clanarina1.jTable.getModel();
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
*/



    private void createMembershipPanel() {
        panelMembership = new JPanel();
        panelMembership.setPreferredSize(new Dimension(100,100));
        panelMembership.setBackground(Color.white);
        panelMembership.add(labelThree);

        this.add(panelMembership, BorderLayout.CENTER);
    }

    private void createGenderPanel() {
        panelGender = new JPanel();
        panelGender.setPreferredSize(new Dimension(100,200));
        //panelGender.setBorder(BorderFactory.createLineBorder(Color.black,4));
        panelGender.setBackground(Color.white);
        panelGender.add(labelTwo);

        this.add(panelGender, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == spolMusko){

            System.out.println(spolMusko.getText());


        } else if (e.getSource() == spolZensko) {
            System.out.println(spolZensko.getText());

        }

        if (e.getSource() == mjesecna){
            System.out.println("Izabrali ste mjesecnu clanarinu");
        }else if (e.getSource() == godisnja){
            System.out.println("Izabrali ste godisnju clanarinu");
        }

        if (e.getSource() == buttonTwo){
            textField.setText("");
            textfieldTwo.setText("");
            passwordField.setText("");
            clanarina.clearSelection();
            muskoZensko.clearSelection();

        }



    }


}
