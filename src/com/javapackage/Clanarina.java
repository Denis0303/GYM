package com.javapackage;


import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.TableRowSorter;

public class Clanarina extends JFrame {

    private static final String CSV_FILE_PATH =
            "C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\Clanovi.csv";

    JLabel label;
    ImageIcon imageIcon;

    JPanel panel;

    //Color lightBlue;

    JButton jButton;

    private JTable jTable;
   private DefaultTableModel model;

   private JPanel searchPanel;

    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;

    JScrollPane jScrollPane;

    JButton deleteButton;

    private JLabel pageLabel;

    private java.util.List<String[]> allData = new ArrayList<>();
    private java.util.List<String[]> currentData = new ArrayList<>();
    private TableRowSorter<DefaultTableModel> sorter;
    private int pageSize = 80;
    private int currentPage = 1;

    Clanarina(){



        this.setTitle("Članarina");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        DatabaseHelper.createTables();



        initializeTable();


        setComponents();
        addSearchPanel();
        setBottomButtons();


        loadDataFromDatabase();
        insertDummyDataIfEmpty();





        this.setVisible(true);


    }

    public void loadDataFromDatabase() {
        allData.clear(); // Clear previous rows


        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM People")) {

            while (rs.next()) {
                String[] row = {
                        rs.getString("Ime i prezime"),
                        rs.getString("E-mail"),
                        rs.getString("Broj telefona"),
                        rs.getString("Spol"),
                        rs.getString("Clanarina"),
                        rs.getString("Datum"),
                        rs.getString("Rok isteka")
                };
                allData.add(row);
            }

            currentData = new ArrayList<>(allData);
            showPage(1);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage());
        }
    }




    public void setComponents(){

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

        //lightBlue = new Color(0,0,182,155);






    }


    private void setJtableButton(){

        jButton = new JButton();
        jButton.setText("Natrag...");
        //jButton.setForeground(lightBlue);
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


   /* public void loadCsvDataFromFile(String filePath) {
        //model.setRowCount(0); // clear existing rows
        allData.clear();
        currentData.clear();

        File file = new File(filePath);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "CSV file does not exist: " + filePath);
            return;

        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split line on comma, trim spaces
                String[] data = line.split("\\s*,\\s*");
                allData.add(data);
                //model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV: " + e.getMessage());
        }

        currentData = new ArrayList<>(allData);
        showPage(1);
    }
*/

    private void showPage(int page){
        model.setRowCount(0);

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, currentData.size());

        for (int i = start; i < end; i++){
            model.addRow(currentData.get(i));
        }

        currentPage = page;

        if (pageLabel != null) {
            int totalPages = (int) Math.ceil((double) currentData.size() / pageSize);
            pageLabel.setText("Page: " + currentPage + " / " + totalPages);
        }
    }

    static class ExpirationDateCellRenderer extends DefaultTableCellRenderer{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

            try {
                // Find the "Rok isteka" column index (last column in your case)
                int expirationCol = table.getColumnCount() - 1;

                Object expirationValue = table.getValueAt(row, expirationCol);
                if (expirationValue != null) {
                    LocalDate expirationDate = LocalDate.parse(expirationValue.toString(), formatter);
                    if (expirationDate.isBefore(LocalDate.now())) {
                        // Membership expired → set row text color red
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                    }
                }
            } catch (Exception e) {
                c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            }

            return c;
        }
    }





    private void setBottomButtons() {
        JPanel buttonPanel = new JPanel(); // defaults to FlowLayout


        jButton = new JButton("Natrag...");
        jButton.setFocusable(false);
        jButton.addActionListener(e -> {
            MyFrame myFrame = new MyFrame();
            myFrame.setVisible(true);
            Clanarina.this.dispose();
        });

        deleteButton = new JButton("Obriši Red");
        deleteButton.setForeground(Color.RED);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow >= 0) {
                int modelRow = jTable.convertRowIndexToModel(selectedRow);
                Object email = model.getValueAt(modelRow, 1);

                String sql = "DELETE FROM People WHERE \"E-mail\" = ?";
                try (Connection conn = DatabaseHelper.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, email.toString());
                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                model.removeRow(selectedRow);
                // Remove from data lists
                allData.removeIf(row -> row[1].equals(email.toString()));
                currentData.removeIf(row -> row[1].equals(email.toString()));
            } else {
                JOptionPane.showMessageDialog(
                        Clanarina.this,
                        "Nema odabranog reda.",
                        "Greška",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });


        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        pageLabel = new JLabel("Page: " + currentPage);

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                showPage(currentPage - 1);

            }
        });

        nextButton.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) allData.size() / pageSize);
            if (currentPage < totalPages) {
                showPage(currentPage + 1);

            }
        });

        buttonPanel.add(jButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(pageLabel);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeTable() {
        String[] columns = {"Ime i prezime", "E-mail", "Broj telefona", "Spol", "Clanarina", "Datum", "Rok isteka"};
        model = new DefaultTableModel(columns, 0);
        jTable = new JTable(model);
        jTable.setRowHeight(25);

        ExpirationDateCellRenderer renderer = new ExpirationDateCellRenderer();
        for (int i = 0; i < jTable.getColumnCount(); i++){
            jTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane, BorderLayout.CENTER);
        }


   /* private void saveTableToCsv(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object cellValue = model.getValueAt(i, j);
                    bw.write(cellValue != null ? cellValue.toString() : "");
                    if (j < model.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Greška pri spremanju CSV: " + ex.getMessage());
        }*/










    private void loadDummyData(int count) {
        Random random = new Random();
        String[] genders = {"M", "F"};

        for (int i = 1; i <= count; i++) {
            String name = "User" + i + " Last" + i;
            String email = "user" + i + "@example.com";
            String phone = String.format("+385 91 %03d %04d", random.nextInt(1000), random.nextInt(10000));
            String gender = genders[random.nextInt(genders.length)];
            String membership = random.nextBoolean() ? "Standard" : "Premium";
            String startDate = String.format("%02d.%02d.2024", random.nextInt(28) + 1, random.nextInt(12) + 1);
            String expirationDate = String.format("%02d.%02d.2025", random.nextInt(28) + 1, random.nextInt(12) + 1);

            Object[] row = {name, email, phone, gender, membership, startDate, expirationDate};
            model.addRow(row);
        }

}


    private void addSearchPanel() {


        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // initialize it!




        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);



        // Add action listener for Search button
        searchButton.addActionListener(e -> {
            String text = searchField.getText().trim().toLowerCase();
            currentData.clear();
            if (text.isEmpty()) {
                currentData.addAll(allData);
            } else {
                for (String[] row : allData){
                    for (String cell : row){
                        if (cell.toLowerCase().contains(text)){
                            currentData.add(row);
                            break;
                        }
                    }
                }
            }

            showPage(1);


        });

        // Add action listener for Reset button
        resetButton.addActionListener(e -> {
            searchField.setText("");
            currentData.clear();
            currentData.addAll(allData);
            showPage(1);
        });

        this.add(searchPanel, BorderLayout.NORTH);
    }

  /*  private void insertCsvDataToDatabase() {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT OR IGNORE INTO People (\"Ime i prezime\",\"E-mail\",\"Broj telefona\",\"Spol\",\"Clanarina\",\"Datum\",\"Rok isteka\") VALUES (?,?,?,?,?,?,?)")) {

            for (String[] row : allData) {
                for (int i = 0; i < row.length; i++) {
                    pstmt.setString(i + 1, row[i]);
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

}*/

    private static void insertPerson(String imePrezime, String email, String phone,
                              String spol, String clanarina,
                              String datum, String rokIsteka) {
        String sql = "INSERT INTO People (\"Ime i prezime\",\"E-mail\",\"Broj telefona\",\"Spol\",\"Clanarina\",\"Datum\",\"Rok isteka\") VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, imePrezime);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, spol);
            pstmt.setString(5, clanarina);
            pstmt.setString(6, datum);
            pstmt.setString(7, rokIsteka);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void insertDummyDataIfEmpty() {
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM People")) {

            int count = rs.getInt("count");
            if (count == 0) {
                insertPerson("Test User", "test@example.com", "+385911234567", "M",
                        "Mjesečna", "01.08.2025", "01.09.2025");
                insertPerson("Another User", "another@example.com", "+385912345678", "Ž",
                        "Godišnja", "01.08.2025", "01.08.2026");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}


