package com.javapackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class myRegister extends JFrame implements ActionListener {

    ImageIcon imageIcon;
    JLabel labelLogin;
    JPanel panel;
    TextField EmailTextField;
    JPasswordField passwordField;
    JButton confirmButton;
    JButton registerButton;

    myRegister(){

        this.setTitle("Clanarina");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(560,400);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);

        setComponents();
        this.setResizable(false);
        this.setVisible(true);


    }

    public void setComponents(){
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");

        labelLogin = new JLabel("PRIJAVA");
        labelLogin.setIcon(imageIcon);
        labelLogin.setHorizontalTextPosition(JLabel.CENTER); //Setting the text Center of Image
        labelLogin.setVerticalTextPosition(JLabel.BOTTOM);
        labelLogin.setIconTextGap(20);


        labelLogin.setFont(new Font("Calibri",Font.PLAIN,22));


        this.add(labelLogin);

        EmailTextField = new TextField();

        EmailTextField.setText("E-mail");
        EmailTextField.setPreferredSize(new Dimension(100,30));
        EmailTextField.setForeground(Color.gray);
        EmailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (EmailTextField.getText().equals("E-mail")){
                    EmailTextField.setForeground(Color.BLACK);
                    EmailTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (EmailTextField.getText().isEmpty()){
                    EmailTextField.setText("E-mail");
                    EmailTextField.setForeground(Color.gray);
                }

            }
        });

        this.add(EmailTextField);

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
                    passwordField.setEchoChar((char) 0);
                }

            }
        });


        this.add(passwordField);


        confirmButton = new JButton();
        confirmButton.setText("POTVRDI");
        confirmButton.setBounds(100,50,120,30);
        confirmButton.addActionListener(this);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "Denis";
                String Email = "denisradocaj03@gmail.com";

                String enteredPassword = new String(passwordField.getPassword());
                String enteredEmail = EmailTextField.getText();

                if (enteredPassword.equals(password) && enteredEmail.equals(Email)){

                    System.out.println("Access granted!");

                    Clanarina clanarinaFrame = new Clanarina();
                    // Load CSV data here, replace with your actual file path
                    String csvPath = "C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\Clanovi.csv";
                    //clanarinaFrame.loadCsvDataFromFile(csvPath);


                    clanarinaFrame.setVisible(true);

                    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(confirmButton);
                    loginFrame.dispose();


                }else{
                    System.out.println("Access denied");
                    JOptionPane.showMessageDialog(null, "Wrong E-mail or Password!");
                }

;            }
        });

        this.add(confirmButton);


        registerButton = new JButton();
        registerButton.setText("Registracija");
        registerButton.setFocusable(false);
        registerButton.setBounds(328,180,150,20);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the new frame
                MyFrame myFrame = new MyFrame();
                myFrame.setVisible(true);

                // Close the current frame
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(registerButton);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }
            }
        });

        this.add(registerButton);

        panel = new JPanel();

        panel.setPreferredSize(new Dimension(500,250));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.red, 7));
        panel.add(labelLogin);
        panel.add(EmailTextField);
        panel.add(passwordField);

        panel.add(confirmButton);


        this.add(panel, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton){
            System.out.println();

        }



    }











}
