package com.javapackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class myLogin extends JFrame implements ActionListener {

    ImageIcon imageIcon;
    JLabel label;
    JPanel panel;
    TextField textField;

    JPasswordField passwordField;

    JButton button;

    Color lightBlue;

    JButton buttonTwo;



    myLogin(){

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

        label = new JLabel("PRIJAVA");
        label.setIcon(imageIcon);
        label.setHorizontalTextPosition(JLabel.CENTER); //Setting the text Center of Image
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setIconTextGap(30);


        label.setFont(new Font("Calibri",Font.PLAIN,22));


        this.add(label);

        textField = new TextField();

        textField.setText("E-mail");
        textField.setPreferredSize(new Dimension(100,30));
        textField.setForeground(Color.gray);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("E-mail")){
                    textField.setForeground(Color.BLACK);
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                    textField.setText("E-mail");
                    textField.setForeground(Color.gray);
                }

            }
        });

        this.add(textField);

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

        button = new JButton();
        button.setText("POTVRDI");
        button.setBounds(100,50,120,30);
        button.addActionListener(this);
        button.setFocusable(false);


        this.add(button);

        lightBlue = new Color(0,0,182,155);

        buttonTwo = new JButton();
        buttonTwo.setText("Niste Registrirani?");
        buttonTwo.setFocusable(false);
        buttonTwo.setForeground(lightBlue);
        buttonTwo.setBounds(328,180,150,20);
        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame myFrame = new MyFrame();
                myFrame.setVisible(true);
                myLogin.this.dispose();

            }
        });

        this.add(buttonTwo);

        panel = new JPanel();

        panel.setPreferredSize(new Dimension(500,250));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.red, 7));
        panel.add(label);
        panel.add(textField);
        panel.add(passwordField);

        panel.add(button);


        this.add(panel, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonTwo){
            System.out.println();

        }

    }



}
