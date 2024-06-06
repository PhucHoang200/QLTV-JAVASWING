package com.librarymanagement.views;

import com.librarymanagement.controllers.BookController;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocGiaMainView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton btnMuonSach, btnTraSach, btnXemThongBao, btnDangXuat;
    private JTextField textFieldBookId;
    private JDateChooser dateChooserBorrowDate, dateChooserReturnDate;
    private JSpinner spinnerQuantity;
    private DefaultTableModel tableModel;
	private JTable phieuMuonTable;
	private JDialog returnDialog;
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DocGiaMainView docgiamainView = new DocGiaMainView(null);
                    docgiamainView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
    public DocGiaMainView(String tenTaiKhoan) {
        setTitle("Giao diện độc giả");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1028, 561);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 348, 980, 151);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableModel = new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
            },
            new String[] {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Năm xuất bản", "Số lượng"}
        );
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        btnMuonSach = new JButton("Mượn sách");
        btnMuonSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muonSach(tenTaiKhoan);
            }
        });
        btnMuonSach.setBounds(30, 20, 150, 32);
        getContentPane().add(btnMuonSach);

        btnTraSach = new JButton("Trả sách");
        btnTraSach.setBounds(276, 20, 150, 32);
        btnTraSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               hienThiPhieuMuon(tenTaiKhoan);
               
            }
        });
        getContentPane().add(btnTraSach);

        btnXemThongBao = new JButton("Xem thông báo");
        btnXemThongBao.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		xemThongBao(tenTaiKhoan);
        	}
        });
        btnXemThongBao.setBounds(509, 20, 150, 32);
        getContentPane().add(btnXemThongBao);

        btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		LoginView loginView = new LoginView();
        		loginView.setVisible(true);
        	}
        });
        btnDangXuat.setBounds(764, 20, 140, 32);
        getContentPane().add(btnDangXuat);

        BookController.checkConnection(table);

        JLabel lblBookId = new JLabel("Mã sách:");
        lblBookId.setBounds(30, 117, 82, 32);
        getContentPane().add(lblBookId);

        textFieldBookId = new JTextField();
        textFieldBookId.setBounds(118, 121, 187, 25);
        getContentPane().add(textFieldBookId);
        textFieldBookId.setColumns(10);

        JLabel lblBorrowDate = new JLabel("Ngày mượn");
        lblBorrowDate.setBounds(30, 176, 82, 32);
        getContentPane().add(lblBorrowDate);

        dateChooserBorrowDate = new JDateChooser();
        dateChooserBorrowDate.setBounds(118, 176, 187, 23);
        getContentPane().add(dateChooserBorrowDate);

        JLabel lblReturnDate = new JLabel("Ngày trả");
        lblReturnDate.setBounds(30, 232, 82, 32);
        getContentPane().add(lblReturnDate);

        dateChooserReturnDate = new JDateChooser();
        dateChooserReturnDate.setBounds(118, 232, 187, 23);
        getContentPane().add(dateChooserReturnDate);

        JLabel lblQuantity = new JLabel("Số lượng");
        lblQuantity.setBounds(30, 290, 82, 23);
        getContentPane().add(lblQuantity);

        spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        spinnerQuantity.setBounds(118, 289, 187, 25);
        getContentPane().add(spinnerQuantity);
    }

    private void muonSach(String username) {
        String bookId = textFieldBookId.getText();
        String readerId = getMaDocGiaFromAccount(username); // Lấy mã độc giả từ tài khoản đăng nhập
        java.util.Date borrowDate = dateChooserBorrowDate.getDate();
        java.util.Date returnDate = dateChooserReturnDate.getDate();
        int quantity = (Integer) spinnerQuantity.getValue();

        if (bookId.isEmpty() || readerId.isEmpty() || borrowDate == null || returnDate == null || quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đủ thông tin.");
            return;
        }

        if (quantity > 5) {
            JOptionPane.showMessageDialog(this, "Số lượng mượn tối đa là 5.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(BookController.connUrl);
             PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO PhieuMuon (MaDocGia, MaSach, SoLuongMuon, NgayMuon, NgayTraDuKien) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, readerId);
            stmt.setString(2, bookId);
            stmt.setInt(3, quantity);
            stmt.setDate(4, new java.sql.Date(borrowDate.getTime()));
            stmt.setDate(5, new java.sql.Date(returnDate.getTime()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Mượn sách thành công!");
                capNhatSoLuongSach(bookId, -quantity); // Giảm số lượng sách trong bảng sách
            } else {
                JOptionPane.showMessageDialog(this, "Mượn sách thất bại.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi mượn sách: " + ex.getMessage());
        }
    }

    private String getMaDocGiaFromAccount(String username) {
        String maDocGia = "";
        try (Connection conn = DriverManager.getConnection(BookController.connUrl)) {
            String sql = "SELECT d.MaDocGia FROM DocGia d INNER JOIN dangky dk ON d.role = dk.role WHERE dk.username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    maDocGia = rs.getString("MaDocGia");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy mã độc giả: " + ex.getMessage());
        }
        return maDocGia;
    }

    private void khoiTaoHienThiDialogTraSach() {
        returnDialog = new JDialog(this, "Danh sách phiếu mượn", true);
        returnDialog.setSize(800, 400);
        returnDialog.setLocationRelativeTo(this);

        String[] columnNames = {"Mã phiếu mượn", "Mã sách", "Số lượng mượn", "Ngày mượn", "Ngày trả dự kiến"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        phieuMuonTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(phieuMuonTable);
        returnDialog.add(scrollPane, BorderLayout.CENTER);

        JButton returnBookButton = new JButton("Trả sách");
        returnBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = phieuMuonTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(returnDialog, "Vui lòng chọn phiếu mượn cần trả.");
                    return;
                }

                String maPhieuMuon = (String) phieuMuonTable.getValueAt(selectedRow, 0);
                String maSach = (String) phieuMuonTable.getValueAt(selectedRow, 1);
                int soLuongMuon = (int) phieuMuonTable.getValueAt(selectedRow, 2);

                traSach(maPhieuMuon, maSach, soLuongMuon, maSach);
            }
        });
        returnDialog.add(returnBookButton, BorderLayout.SOUTH);
    }

    private void hienThiPhieuMuon(String tenTaiKhoan) {
        if (returnDialog == null) {
            khoiTaoHienThiDialogTraSach();
        }
        
        String maDocGia = getMaDocGiaFromAccount(tenTaiKhoan); // Lấy mã độc giả từ tài khoản đăng nhập
        
        if (maDocGia.isEmpty()) {
            //JOptionPane.showMessageDialog(this, "Không tìm thấy mã độc giả.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(BookController.connUrl)) {
            String sql = "SELECT MaPhieuMuon, MaSach, SoLuongMuon, NgayMuon, NgayTraDuKien FROM PhieuMuon WHERE MaDocGia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, maDocGia);
                ResultSet rs = stmt.executeQuery();
                DefaultTableModel model = (DefaultTableModel) phieuMuonTable.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    String maPhieuMuon = rs.getString("MaPhieuMuon");
                    String maSach = rs.getString("MaSach");
                    int soLuongMuon = rs.getInt("SoLuongMuon");
                    java.sql.Date ngayMuon = rs.getDate("NgayMuon");
                    java.sql.Date ngayTraDuKien = rs.getDate("NgayTraDuKien");

                    Object[] row = {maPhieuMuon, maSach, soLuongMuon, ngayMuon, ngayTraDuKien};
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách phiếu mượn: " + ex.getMessage());
        }
        returnDialog.setVisible(true);
    }
    

    private void traSach(String maPhieuMuon, String maSach, int soLuongMuon, String tenTaiKhoan) {
        try (Connection conn = DriverManager.getConnection(BookController.connUrl)) {
            
            // Kiểm tra tình trạng phiếu mượn
            String checkSql = "SELECT TinhTrang FROM PhieuMuon WHERE MaPhieuMuon = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, maPhieuMuon);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    String tinhTrang = rs.getString("TinhTrang");
                    if ("Đã trả".equals(tinhTrang)) {
                        JOptionPane.showMessageDialog(returnDialog, "Phiếu mượn này đã được trả trước đó.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(returnDialog, "Không tìm thấy phiếu mượn.");
                    return;
                }
            }

            // Cập nhật tình trạng phiếu mượn và ngày trả thực tế
            String updateSql = "UPDATE PhieuMuon SET TinhTrang = N'Đã trả', NgayTraThucTe = GETDATE() WHERE MaPhieuMuon = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, maPhieuMuon);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(returnDialog, "Trả sách thành công!");
                    capNhatSoLuongSach(maSach, soLuongMuon); // Tăng số lượng sách trong bảng sách
                    hienThiPhieuMuon(tenTaiKhoan); // Cập nhật lại danh sách phiếu mượn sau khi trả sách
                } else {
                    JOptionPane.showMessageDialog(returnDialog, "Trả sách thất bại.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(returnDialog, "Lỗi khi trả sách: " + ex.getMessage());
        }
    }

    private void capNhatSoLuongSach(String maSach, int thayDoiSoLuong) {
        try (Connection conn = DriverManager.getConnection(BookController.connUrl);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Sach SET SoLuong = SoLuong + ? WHERE MaSach = ?")) {
            stmt.setInt(1, thayDoiSoLuong);
            stmt.setString(2, maSach);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật số lượng sách: " + ex.getMessage());
        }
    }



    private void xemThongBao(String tenTaiKhoan) {
        String maDocGia = getMaDocGiaFromTenTaiKhoan(tenTaiKhoan);

        if (maDocGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã độc giả cho tài khoản này.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(BookController.connUrl)) {
            String sql = "SELECT NoiDung, NgayGui FROM ThongBao WHERE MaDocGia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maDocGia);
            ResultSet rs = preparedStatement.executeQuery();

            StringBuilder thongBaoBuilder = new StringBuilder();
            while (rs.next()) {
                String noiDung = rs.getString("NoiDung");
                String ngayGui = rs.getString("NgayGui");
                thongBaoBuilder.append("Ngày gửi: ").append(ngayGui).append("\n");
                thongBaoBuilder.append("Nội dung: ").append(noiDung).append("\n\n");
            }

            if (thongBaoBuilder.length() == 0) {
                JOptionPane.showMessageDialog(this, "Không có thông báo nào cho tài khoản này.");
            } else {
                JTextArea textArea = new JTextArea(thongBaoBuilder.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(this, scrollPane, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xem thông báo: " + e.getMessage());
        }
    }

    private String getMaDocGiaFromTenTaiKhoan(String tenTaiKhoan) {
        String maDocGia = "";
        try (Connection conn = DriverManager.getConnection(BookController.connUrl)) {
            String sql = "SELECT MaDocGia FROM DocGia d INNER JOIN dangky dk ON d.role = dk.role WHERE dk.username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tenTaiKhoan);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                maDocGia = rs.getString("MaDocGia");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin độc giả từ tên tài khoản: " + e.getMessage());
        }
        return maDocGia;
    }

}
