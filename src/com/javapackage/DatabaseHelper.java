package com.javapackage;


import java.sql.*;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/denis/Documents/people.db";
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTables() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // Users table
            String sqlUsers = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL" +
                    ");";
            stmt.execute(sqlUsers);

            // People / Members table
            String sqlMembers = "CREATE TABLE IF NOT EXISTS People (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "\"Ime i prezime\" TEXT," +
                    "\"E-mail\" TEXT UNIQUE," +
                    "\"Broj telefona\" TEXT," +
                    "\"Spol\" TEXT," +
                    "\"Clanarina\" TEXT," +
                    "\"Datum\" TEXT," +
                    "\"Rok isteka\" TEXT" +
                    ");";
            stmt.execute(sqlMembers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
