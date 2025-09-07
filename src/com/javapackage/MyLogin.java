package com.javapackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyLogin extends JFrame implements ActionListener {

    ImageIcon imageIcon;
    JLabel labelLogin;
    JPanel panel;
    TextField emailTextField;
    JPasswordField passwordField;
    JButton confirmButton;
    MyLogin(){

        this.setTitle("Clanarina");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);

        setComponents();
        this.setResizable(false);
        this.setVisible(true);


    }

    public void setComponents(){
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");

        labelLogin();
        createEmailTextField();
        passwordField();
        confirmButton();
        createPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        }


    private void createEmailTextField() {
        emailTextField = new TextField();

        emailTextField.setText("E-mail");
        emailTextField.setPreferredSize(new Dimension(100,30));
        emailTextField.setForeground(Color.gray);
        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailTextField.getText().equals("E-mail")){
                    emailTextField.setForeground(Color.BLACK);
                    emailTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().isEmpty()){
                    emailTextField.setText("E-mail");
                    emailTextField.setForeground(Color.gray);
                }

            }
        });
    }


    private void createPanel() {
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(500,250));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.red, 7));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        labelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextField.setMaximumSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(15)); // space above
        panel.add(labelLogin);
        panel.add(Box.createVerticalStrut(20));
        panel.add(emailTextField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(confirmButton);


        this.add(panel, BorderLayout.CENTER);;
    }

    private void labelLogin(){
        labelLogin = new JLabel("PRIJAVA");
        labelLogin.setIcon(imageIcon);
        labelLogin.setHorizontalTextPosition(JLabel.CENTER); //Setting the text Center of Image
        labelLogin.setVerticalTextPosition(JLabel.BOTTOM);
        labelLogin.setIconTextGap(5);


        labelLogin.setFont(new Font("Calibri",Font.PLAIN,22));


        this.add(labelLogin);
    }

    private void passwordField(){
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
    }

    private void confirmButton(){
        confirmButton = new JButton();
        confirmButton.setText("POTVRDI");
        confirmButton.setBounds(100,50,120,30);
        confirmButton.addActionListener(this);
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(e -> {
            String password = "Denis";
            String Email = "denisradocaj03@gmail.com";

            String enteredPassword = new String(passwordField.getPassword());
            String enteredEmail = emailTextField.getText();

            if (enteredPassword.equals(password) && enteredEmail.equals(Email)){

                Clanarina clanarinaFrame = new Clanarina();
                clanarinaFrame.setVisible(true);

                JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(confirmButton);
                loginFrame.dispose();

            }else{
                System.out.println("Access denied");
                JOptionPane.showMessageDialog(null, "Wrong E-mail or Password!");
            }

        });

        this.add(confirmButton);

    }


}
