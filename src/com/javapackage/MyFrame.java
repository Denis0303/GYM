package com.javapackage;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MyFrame extends JFrame implements ActionListener {
    JPanel panel;
    JPanel panelMembership;
    JPanel panelGender;
    JLabel registrationLabel;
    JLabel labelGender;
    JLabel labelMembership;
    ImageIcon imageIcon;
    JButton confirmButton;
    JButton deleteButton;
    JButton goBackButton;
    TextField nameSurnameText;
    TextField emailTextField;
    TextField phoneNumberText;
    JPasswordField passwordField;
    JRadioButton spolMusko;
    JRadioButton spolZensko;
    ButtonGroup muskoZensko;
    JRadioButton mjesecna;
    JRadioButton godisnja;
    ButtonGroup clanarina;

    MyFrame(){

        this.setTitle("Clanarina");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(getJMenuBar());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        components();

        this.setVisible(true);
    }

    public void components(){
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");

        registrationLabel = new JLabel(imageIcon);
        registrationLabel.setIcon(imageIcon);
        registrationLabel.setText("REGISTRACIJA");
        registrationLabel.setHorizontalTextPosition(JLabel.CENTER); //Setting the text Center of Image
        registrationLabel.setVerticalTextPosition(JLabel.BOTTOM); //Setting the text Bottom of image
        registrationLabel.setVerticalAlignment(JLabel.CENTER);
        registrationLabel.setHorizontalAlignment(JLabel.CENTER);
        registrationLabel.setIconTextGap(-25);


        registrationLabel.setFont(new Font("Calibri",Font.PLAIN,22)); //FONT SIZE AND TYPE

        this.add(registrationLabel);


        labelGender = new JLabel();
        labelGender.setText("SPOL");
        labelGender.setVerticalAlignment(JLabel.CENTER);
        labelGender.setHorizontalAlignment(JLabel.CENTER);
        labelGender.setFont(new Font("Calibri",Font.PLAIN,22));

        this.add(labelGender);

        labelMembership = new JLabel();
        labelMembership.setText("ČLANARINA");
        labelMembership.setVerticalAlignment(JLabel.CENTER);
        labelMembership.setHorizontalAlignment(JLabel.CENTER);
        labelMembership.setFont(new Font("Calibri",Font.PLAIN,22));

        this.add(labelMembership);

        createButton();


        deleteButton = new JButton();
        deleteButton.setBounds(100,500,100,50);
        deleteButton.setText("PONIŠTI");
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(e -> {
            nameSurnameText.setText("");
            emailTextField.setText("");
            phoneNumberText.setText("");
            muskoZensko.clearSelection();
            clanarina.clearSelection();


        });

        this.add(deleteButton);


        goBackButton = new JButton();
        goBackButton.setText("Natrag..");
        goBackButton.addActionListener(e ->  {
            new myRegister().setVisible(true);

            // Close the current frame
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(goBackButton);
            if (currentFrame != null) {
                currentFrame.dispose();
            } else {
                System.out.println("Could not find the parent frame to close.");
            }


        });
        goBackButton.setBounds(370,20,100,20);
        goBackButton.setFocusable(false);

        this.add(goBackButton);

        nameSurnameText = new TextField();
        nameSurnameText.setBounds(135,200,100,30);
        nameSurnameText.setText("Ime I Prezime");
        nameSurnameText.setForeground(Color.gray);
        nameSurnameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameSurnameText.getText().equals("Ime I Prezime")){
                    nameSurnameText.setText("");
                    nameSurnameText.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameSurnameText.getText().isEmpty()){
                    nameSurnameText.setForeground(Color.gray);
                    nameSurnameText.setText("Ime I Prezime");
                }

            }
        });

        this.add(nameSurnameText);



        emailTextField = new TextField();
        emailTextField.setBounds(245,200,100,30);
        emailTextField.setText("E-mail");
        emailTextField.setForeground(Color.gray);
        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailTextField.getText().equals("E-mail")){
                    emailTextField.setText("");
                    emailTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (emailTextField.getText().isEmpty()){
                    emailTextField.setForeground(Color.gray);
                    emailTextField.setText("E-mail");
                }

            }
        });

        this.add(emailTextField);


        phoneNumberText = new TextField();
        phoneNumberText.setBounds(355,200,100,30);
        phoneNumberText.setText("Broj telefona");
        phoneNumberText.setForeground(Color.gray);
        phoneNumberText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberText.getText().equals("Broj telefona")){
                    phoneNumberText.setText("");
                    phoneNumberText.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if (phoneNumberText.getText().isEmpty()){
                    phoneNumberText.setForeground(Color.gray);
                    phoneNumberText.setText("Broj telefona");
                }

            }
        });


        this.add(phoneNumberText);


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
        panel.setBackground(Color.white);
        panel.add(registrationLabel);

        this.add(panel, BorderLayout.NORTH);


        createMembershipPanel();
        createGenderPanel();
    }

    private void createButton() {
        confirmButton = new JButton();
        confirmButton.setBounds(360, 500, 100, 50);
        confirmButton.setText("POTVRDI");
        confirmButton.setFocusable(false);

        confirmButton.addActionListener(e -> {

            String name = nameSurnameText.getText().trim();
            String email = emailTextField.getText().trim();
            String phone = phoneNumberText.getText().trim();
            String gender = getSelectedGender();
            String membership = getSelectedMembership();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    (!spolMusko.isSelected() && !spolZensko.isSelected()) ||
                    membership.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please fill in all fields and make all selections!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String expirationDate;
            if (membership.equals("Mjesečna")) {
                expirationDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } else {
                expirationDate = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }

            try (java.sql.Connection conn = DatabaseHelper.connect()) {

                // Duplicate check
                java.sql.PreparedStatement checkStmt = conn.prepareStatement(
                        "SELECT COUNT(*) AS count FROM People WHERE \"Ime i prezime\" = ?");
                checkStmt.setString(1, name);
                java.sql.ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt("count") > 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "A member with this name already exists!",
                            "Duplicate Entry",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return; // stop here
                }

                // Insert into database
                java.sql.PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO People (\"Ime i prezime\", \"E-mail\", \"Broj telefona\", \"Spol\", \"Clanarina\", \"Datum\", \"Rok isteka\") VALUES (?,?,?,?,?,?,?)");

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                pstmt.setString(4, gender);
                pstmt.setString(5, membership);
                pstmt.setString(6, startDate);
                pstmt.setString(7, expirationDate);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Data exported successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Clear fields after success
                nameSurnameText.setText("Ime I Prezime");
                nameSurnameText.setForeground(Color.gray);

                emailTextField.setText("E-mail");
                emailTextField.setForeground(Color.gray);

                phoneNumberText.setText("Broj telefona");
                phoneNumberText.setForeground(Color.gray);

                passwordField.setText("Lozinka");
                passwordField.setForeground(Color.gray);
                passwordField.setEchoChar((char) 0);

                muskoZensko.clearSelection();
                clanarina.clearSelection();

            } catch (java.sql.SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error inserting to database: " + ex.getMessage());
            }

        });

        this.add(confirmButton);
    }

    private String getSelectedGender() {
        if (spolMusko.isSelected()) return "Muško";
        if (spolZensko.isSelected()) return "Žensko";
        return ""; // return empty if nothing selected
    }

    private String getSelectedMembership() {
        if (mjesecna.isSelected()) return "Mjesečna";
        if (godisnja.isSelected()) return "Godišnja";
        return ""; // return empty if nothing selected
    }

    private void createMembershipPanel() {
        panelMembership = new JPanel();
        panelMembership.setPreferredSize(new Dimension(100,100));
        panelMembership.setBackground(Color.white);
        panelMembership.add(labelMembership);

        this.add(panelMembership, BorderLayout.CENTER);
    }

    private void createGenderPanel() {
        panelGender = new JPanel();
        panelGender.setPreferredSize(new Dimension(100,200));
        //panelGender.setBorder(BorderFactory.createLineBorder(Color.black,4));
        panelGender.setBackground(Color.white);
        panelGender.add(labelGender);

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

        if (e.getSource() == deleteButton){
            nameSurnameText.setText("");
            emailTextField.setText("");
            passwordField.setText("");
            clanarina.clearSelection();
            muskoZensko.clearSelection();

        }
    }
}
