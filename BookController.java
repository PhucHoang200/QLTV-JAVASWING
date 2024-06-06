package com.librarymanagement.controllers;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookController {
    public static final String connUrl = "jdbc:sqlserver://LAPTOP-VI7NDRKV:1433;user=sa;password=123456789;databaseName=QLTVTEST;encrypt=false";

    public static void checkConnection(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        String sql = "SELECT * FROM Sach";
        try (Connection conn = DriverManager.getConnection(connUrl);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = new Object[6]; // Removed status column
                for (int i = 0; i < 6; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
    }
    
    
}
