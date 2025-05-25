package com.javapackage;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class MyFrame extends JFrame implements ActionListener {

    JPanel panel;
    JPanel panelTwo;
    JPanel panelThree;
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


        button = new JButton();
        button.addActionListener(this);

        button.setBounds(360,500,100,50);
        button.setText("POTVRDI");
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Clanarina clanarinaFrame = new Clanarina();

                clanarinaFrame.setVisible(true);
                clanarinaFrame.addRowToJtable(new Object[]{
                        textField.getText(),
                        textfieldTwo.getText(),
                        textFieldThree.getText(),
                        spolMusko.isSelected() ? "M" : "Ž", // ovaj upitnik znaci or?

                });
                MyFrame.this.dispose();




            }
        });



        this.add(button);

        buttonTwo = new JButton();
        buttonTwo.addActionListener(this);
        buttonTwo.setBounds(100,500,100,50);
        buttonTwo.setText("PONIŠTI");
        buttonTwo.setFocusable(false);

        this.add(buttonTwo);

        lightBlue = new Color(0,0,182,155);

        buttonThree = new JButton();
        buttonThree.setText("Natrag..");
        buttonThree.setForeground(lightBlue);
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myLogin myLogin = new myLogin();
                myLogin.setVisible(true);
                MyFrame.this.dispose();


            }
        });
        buttonThree.setBounds(370,20,100,20);
        buttonThree.setFocusable(false);

        this.add(buttonThree);

        textField = new TextField();
        //textField.setPreferredSize(new Dimension(100,30));
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
        //textfieldTwo.setPreferredSize(new Dimension(100,30));
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

        passwordField.setPreferredSize(new Dimension(100,30));
        passwordField.setForeground(Color.gray);
        passwordField.setEchoChar((char) 0);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {


                if (passwordField.getText().equals("Lozinka")){

                    passwordField.setText(null);
                    passwordField.setEchoChar('*');
                    passwordField.setForeground(Color.black);

                }


            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()){
                    passwordField.setForeground(Color.gray);
                    passwordField.setText("Lozinka");
                    passwordField.setEchoChar((char)0);




                }

            }
        });


        this.add(passwordField);


        spolMusko = new JRadioButton("Muško");
        spolMusko.setBounds(160,410,120,30);
        spolZensko = new JRadioButton("Žensko");
        spolZensko.setBounds(310,410,120,30);
        spolZensko.addActionListener(this);
        spolMusko.addActionListener(this);
        this.add(spolMusko);
        this.add(spolZensko);

        muskoZensko = new ButtonGroup();
        muskoZensko.add(spolMusko);
        muskoZensko.add(spolZensko);


        mjesecna = new JRadioButton("Mjesečna 40€");
        mjesecna.setBounds(160,300,120,30);
        godisnja = new JRadioButton("Godišnja 480€");
        godisnja.setBounds(310,300,120,30);
        mjesecna.addActionListener(this);
        godisnja.addActionListener(this);
        this.add(mjesecna);
        this.add(godisnja);

        clanarina = new ButtonGroup();
        clanarina.add(mjesecna);
        clanarina.add(godisnja);


        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,250));
        panel.setBorder(BorderFactory.createLineBorder(Color.red,7));
        panel.setBackground(Color.white);
        panel.add(label);

        this.add(panel, BorderLayout.NORTH);


        //CLANARINA
        panelTwo = new JPanel();
        panelTwo.setPreferredSize(new Dimension(100,100));
        panelTwo.setBackground(Color.white);
        panelTwo.add(labelThree);

        this.add(panelTwo, BorderLayout.CENTER);




        //SPOL
        panelThree = new JPanel();
        panelThree.setPreferredSize(new Dimension(100,200));
        panelThree.setBorder(BorderFactory.createLineBorder(Color.black,4));
        panelThree.setBackground(Color.white);
        panelThree.add(labelTwo);

        this.add(panelThree, BorderLayout.SOUTH);

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
