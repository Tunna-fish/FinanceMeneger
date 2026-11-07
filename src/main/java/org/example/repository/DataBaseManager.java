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
    public void addTransaction(double amount, String category, String type) {
        String sql = "INSERT INTO transactions(amount, category, type) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, category);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearAllTransactions() {
        String sql = "DELETE FROM transactions";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}