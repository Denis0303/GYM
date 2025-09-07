package com.javapackage;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Clanarina extends JFrame {

    JLabel labelMembership;
    ImageIcon imageIcon;
    JPanel panel;
    JButton backButton;
    private JTable jTable;
   private DefaultTableModel model;
   private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    JButton deleteButton;
    JButton registerButton;
    private JLabel pageLabel;
    private java.util.List<String[]> allData = new ArrayList<>();
    private java.util.List<String[]> currentData = new ArrayList<>();
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

        labelMembership = new JLabel();
        imageIcon = new ImageIcon("C:\\Users\\denis\\IdeaProjects\\gym-membership\\src\\com\\javapackage\\logo.png");
        labelMembership.setText("CLANARINE");
        labelMembership.setFont(new Font("Calibri",Font.PLAIN,22));
        labelMembership.setIcon(imageIcon);
        labelMembership.setIconTextGap(25);
        labelMembership.setHorizontalTextPosition(JLabel.CENTER);
        labelMembership.setVerticalTextPosition(JLabel.BOTTOM);

        clanarinaCreatePanel();

        this.add(panel, BorderLayout.NORTH);

        registerButton = new JButton("Registracija");
        registerButton.setFocusable(false);
        registerButton.addActionListener(e -> {
            // Open the registration frame
            MyFrame myFrame = new MyFrame(Clanarina.this);
            myFrame.setVisible(true);

        });
    }

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

        backButton = new JButton("Natrag...");
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            MyLogin myLogin = new MyLogin();
            myLogin.setVisible(true);
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

        registerButton = new JButton("Registracija");
        registerButton.setFocusable(false);
        registerButton.addActionListener(e -> {
            MyFrame myFrame = new MyFrame(Clanarina.this);
            myFrame.setVisible(true);


        });

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
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

    private void addSearchPanel() {

        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
    }


    public DefaultTableModel getTableModel() {
        return model;
    }

    public void addRowToTable(String[] rowData) {
        allData.add(rowData);
        currentData.add(rowData);
        showPage(currentPage); // refresh the current page
    }


    private void clanarinaCreatePanel(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(170,170));
        panel.setBackground(Color.white);
        panel.add(labelMembership);


        this.add(panel, BorderLayout.NORTH);
    }
}


