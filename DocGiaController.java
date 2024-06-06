package com.librarymanagement.controllers;

import com.librarymanagement.models.DocGia;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class DocGiaController {
    private static final String connUrl = "jdbc:sqlserver://LAPTOP-VI7NDRKV:1433;" +
            "user=sa;password=123456789;databaseName=QLTVTEST;encrypt=false";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$");

    public static void checkConnection(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        String sql = "SELECT * FROM DocGia";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("MaDocGia");
                row[1] = rs.getString("TenDocGia");
                row[2] = rs.getString("Email");
                row[3] = rs.getDate("NgaySinh");
                row[4] = rs.getString("GioiTinh");
                row[5] = rs.getString("DiaChi");
                row[6] = rs.getString("SDT");
                row[7] = rs.getString("role");
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void addDocGia(DocGia docGia) {
        if (!isValidEmail(docGia.getEmail())) {
            JOptionPane.showMessageDialog(null, "Địa chỉ email không hợp lệ.");
            return;
        }

        String sql = "INSERT INTO DocGia (TenDocGia, Email, NgaySinh, GioiTinh, DiaChi, SDT, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, docGia.getTenDocGia());
            statement.setString(2, docGia.getEmail());
            statement.setDate(3, new Date(docGia.getNgaySinh().getTime()));
            statement.setString(4, docGia.getGioiTinh());
            statement.setString(5, docGia.getDiaChi());
            statement.setString(6, docGia.getSdt());
            statement.setString(7, docGia.getRole());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Thêm độc giả thành công!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm độc giả: " + e.getMessage());
        }
    }

    public void updateDocGia(int selectedRow, DocGia docGia) {
        if (!isValidEmail(docGia.getEmail())) {
            JOptionPane.showMessageDialog(null, "Địa chỉ email không hợp lệ.");
            return;
        }

        String sql = "UPDATE DocGia SET TenDocGia=?, Email=?, NgaySinh=?, GioiTinh=?, DiaChi=?, SDT=? role =? WHERE MaDocGia=?";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, docGia.getTenDocGia());
            statement.setString(2, docGia.getEmail());
            statement.setDate(3, new Date(docGia.getNgaySinh().getTime()));
            statement.setString(4, docGia.getGioiTinh());
            statement.setString(5, docGia.getDiaChi());
            statement.setString(6, docGia.getSdt());
            statement.setString(7, docGia.getRole());
            statement.setString(8, docGia.getMaDocGia());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật độc giả thành công!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật độc giả: " + e.getMessage());
        }
    }

    public void deleteDocGia(int selectedRow, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String maDocGia = (String) model.getValueAt(selectedRow, 0);

        String sql = "DELETE FROM DocGia WHERE MaDocGia=?";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, maDocGia);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Xóa độc giả thành công!");
                model.removeRow(selectedRow); // Remove the row from the table model
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa độc giả: " + e.getMessage());
        }
    }

    public void searchDocGia(String keyword, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        String sql = "SELECT * FROM DocGia WHERE MaDocGia LIKE ? OR TenDocGia LIKE ? OR Email LIKE ? OR DiaChi LIKE ? OR SDT LIKE ? OR GioiTinh LIKE ? OR NgaySinh LIKE ? OR role LIKE ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 8; i++) {
                statement.setString(i, searchKeyword);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[8];
                    row[0] = rs.getString("MaDocGia");
                    row[1] = rs.getString("TenDocGia");
                    row[2] = rs.getString("Email");
                    row[3] = rs.getDate("NgaySinh");
                    row[4] = rs.getString("GioiTinh");
                    row[5] = rs.getString("DiaChi");
                    row[6] = rs.getString("SDT");
                    row[7] = rs.getString("role");
                    model.addRow(row);
                }
           
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm độc giả: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm độc giả: " + e.getMessage());
        }
    }

    public boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

	public String generateMaDocGia() {
		// TODO Auto-generated method stub
		return null;
	}
}
