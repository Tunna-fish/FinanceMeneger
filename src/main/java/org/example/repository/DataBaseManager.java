package org.example.repository;

import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:finance.db";

    public DataBaseManager() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount REAL," +
                    "category TEXT," +
                    "type TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}