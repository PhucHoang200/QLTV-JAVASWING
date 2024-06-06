package com.librarymanagement.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.librarymanagement.models.Sach;

public class SachController {
    private static final String connUrl = "jdbc:sqlserver://LAPTOP-VI7NDRKV:1433;" +
            "user=sa;password=123456789;databaseName=QLTVTEST;encrypt=false";

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

    public void addSach(Sach sach) {
        String sql = "INSERT INTO Sach (TenSach, TacGia, TheLoai, NamXuatBan, SoLuong) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, sach.getTenSach());
            statement.setString(2, sach.getTacGia());
            statement.setString(3, sach.getTheLoai());
            statement.setDate(4, new Date(sach.getNamXuatBan().getTime()));
            statement.setInt(5, Integer.parseInt(sach.getSoLuong()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Sach was inserted successfully!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm sách: " + e.getMessage());
        }
    }

    public void updateSach(int selectedRow, Sach sach) {
        String sql = "UPDATE Sach SET TenSach=?, TacGia=?, TheLoai=?, NamXuatBan=?, SoLuong=? WHERE MaSach=?";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, sach.getTenSach());
            statement.setString(2, sach.getTacGia());
            statement.setString(3, sach.getTheLoai());
            statement.setDate(4, new Date(sach.getNamXuatBan().getTime()));
            statement.setInt(5, Integer.parseInt(sach.getSoLuong()));
            statement.setString(6, sach.getMaSach());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sach was updated successfully!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật sách: " + e.getMessage());
        }
    }

    public void deleteSach(int selectedRow, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String maSach = (String) model.getValueAt(selectedRow, 0);

        String sql = "DELETE FROM Sach WHERE MaSach=?";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, maSach);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Sach was deleted successfully!");
                model.removeRow(selectedRow); // Remove the row from the table model
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa sách: " + e.getMessage());
        }
    }

    public void searchSach(String keyword, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        String sql = "SELECT * FROM Sach WHERE MaSach LIKE ? OR TenSach LIKE ? OR TacGia LIKE ? OR TheLoai LIKE ? OR NamXuatBan LIKE ? OR SoLuong LIKE ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 6; i++) { // Updated loop range
                statement.setString(i, searchKeyword);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[6]; // Removed status column
                    for (int i = 0; i < 6; i++) {
                        row[i] = rs.getObject(i + 1);
                    }
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sách: " + e.getMessage());
        }
    }
}
