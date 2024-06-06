package com.librarymanagement.controllers;

import com.librarymanagement.models.PhieuMuon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PhieuMuonController {
    private static final String connUrl = "jdbc:sqlserver://LAPTOP-VI7NDRKV:1433;" +
            "user=sa;password=123456789;databaseName=QLTVTEST;encrypt=false";

    public void checkConnection(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        String sql = "SELECT MaPhieuMuon, MaDocGia, MaSach, SoLuongMuon, NgayMuon, NgayTraDuKien, NgayTraThucTe, TinhTrang FROM PhieuMuon";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("MaPhieuMuon");
                row[1] = rs.getString("MaDocGia");
                row[2] = rs.getString("MaSach");
                row[3] = rs.getInt("SoLuongMuon");
                row[4] = rs.getDate("NgayMuon");
                row[5] = rs.getDate("NgayTraDuKien");
                row[6] = rs.getDate("NgayTraThucTe");
                row[7] = rs.getString("TinhTrang");
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void addPhieuMuon(PhieuMuon phieuMuon) {
        String sql = "INSERT INTO PhieuMuon (MaDocGia, MaSach, SoLuongMuon, NgayMuon, NgayTraDuKien) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, phieuMuon.getMaDocGia());
            statement.setString(2, phieuMuon.getMaSach());
            statement.setInt(3, phieuMuon.getSoLuongMuon());
            statement.setDate(4, new Date(phieuMuon.getNgayMuon().getTime()));
            statement.setDate(5, new Date(phieuMuon.getNgayTraDuKien().getTime()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm phiếu mượn: " + e.getMessage());
        }
    }

    public void updateSoLuongSach(String maSach, int soLuongMuon) {
        String sql = "UPDATE Sach SET SoLuong = SoLuong - ? WHERE MaSach = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, soLuongMuon);
            statement.setString(2, maSach);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Số lượng sách đã được cập nhật!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng sách: " + e.getMessage());
        }
    }

    public int getSoLuongCon(String maSach) {
        String sql = "SELECT SoLuong FROM Sach WHERE MaSach = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, maSach);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoLuong");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy số lượng sách: " + e.getMessage());
        }
        return 0;
    }

    public boolean isPhieuMuonDaTra(String maPhieuMuon) {
        String sqlCheckTinhTrang = "SELECT TinhTrang FROM PhieuMuon WHERE MaPhieuMuon = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement checkStatement = conn.prepareStatement(sqlCheckTinhTrang)) {
            checkStatement.setString(1, maPhieuMuon);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                String tinhTrang = resultSet.getString("TinhTrang");
                return "Đã trả".equals(tinhTrang);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra tình trạng phiếu mượn: " + e.getMessage());
        }
        return false;
    }

    public boolean updatePhieuMuon(PhieuMuon phieuMuon) {
        String sqlUpdate = "UPDATE PhieuMuon SET MaDocGia = ?, MaSach = ?, SoLuongMuon = ?, NgayMuon = ?, NgayTraDuKien = ? WHERE MaPhieuMuon = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate)) {
            // Cập nhật phiếu mượn
            updateStatement.setString(1, phieuMuon.getMaDocGia());
            updateStatement.setString(2, phieuMuon.getMaSach());
            updateStatement.setInt(3, phieuMuon.getSoLuongMuon());
            updateStatement.setDate(4, new Date(phieuMuon.getNgayMuon().getTime()));
            updateStatement.setDate(5, new Date(phieuMuon.getNgayTraDuKien().getTime()));
            updateStatement.setString(6, phieuMuon.getMaPhieuMuon());

            int rowsUpdated = updateStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
        }
        return false;
    }



    public void updateTraSach(String maPhieuMuon, Date ngayTraThucTe, String tinhTrang, String maSach, int soLuongMuon) {
        String sqlUpdatePhieuMuon = "UPDATE PhieuMuon SET NgayTraThucTe = ?, TinhTrang = ? WHERE MaPhieuMuon = ?";
        String sqlUpdateSach = "UPDATE Sach SET SoLuong = SoLuong + ? WHERE MaSach = ?";

        try (Connection conn = DriverManager.getConnection(connUrl)) {
            // Bắt đầu giao dịch
            conn.setAutoCommit(false);

            try (PreparedStatement statementUpdatePhieuMuon = conn.prepareStatement(sqlUpdatePhieuMuon);
                 PreparedStatement statementUpdateSach = conn.prepareStatement(sqlUpdateSach)) {
                
                // Cập nhật phiếu mượn
                statementUpdatePhieuMuon.setDate(1, ngayTraThucTe);
                statementUpdatePhieuMuon.setString(2, tinhTrang);
                statementUpdatePhieuMuon.setString(3, maPhieuMuon);

                int rowsUpdatedPhieuMuon = statementUpdatePhieuMuon.executeUpdate();

                // Cập nhật số lượng sách
                statementUpdateSach.setInt(1, soLuongMuon);
                statementUpdateSach.setString(2, maSach);

                int rowsUpdatedSach = statementUpdateSach.executeUpdate();

                // Nếu cả hai cập nhật đều thành công, commit giao dịch
                if (rowsUpdatedPhieuMuon > 0 && rowsUpdatedSach > 0) {
                    conn.commit();
                    JOptionPane.showMessageDialog(null, "Thông tin trả sách đã được cập nhật!");
                } else {
                    // Nếu có lỗi, rollback giao dịch
                    conn.rollback();
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin trả sách.");
                }
            } catch (SQLException e) {
                // Nếu có lỗi, rollback giao dịch
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin trả sách: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); // Đặt lại chế độ auto-commit
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }




    public void deletePhieuMuon(String maPhieuMuon) {
        String sql = "DELETE FROM PhieuMuon WHERE MaPhieuMuon = ?";

        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, maPhieuMuon);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa phiếu mượn: " + e.getMessage());
        }
    }

    public void guiThongBao(String maDocGia, String thongBao) {
        if (!maDocGia.isEmpty()) {
            try (Connection connection = DriverManager.getConnection(connUrl)) {
                String sql = "INSERT INTO ThongBao (MaDocGia, NoiDung) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, maDocGia);
                preparedStatement.setString(2, thongBao);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thông báo đã được gửi!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi gửi thông báo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy mã độc giả.");
        }
    }


 /*   public String getMaDocGiaFromTenTaiKhoan(String tenTaiKhoan) {
        String maDocGia = "";
        try (Connection conn = DriverManager.getConnection(connUrl)) {
            String sql = "SELECT MaDocGia FROM DocGia WHERE TenDocGia = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tenTaiKhoan);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                maDocGia = rs.getString("MaDocGia");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin độc giả từ tên tài khoản: " + e.getMessage());
        }
        return maDocGia;
    }  */

    public PhieuMuon getPhieuMuonById(String maPhieuMuon) {
        String sql = "SELECT * FROM PhieuMuon WHERE MaPhieuMuon = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, maPhieuMuon);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMaPhieuMuon(rs.getString("MaPhieuMuon"));
                phieuMuon.setMaDocGia(rs.getString("MaDocGia"));
                phieuMuon.setMaSach(rs.getString("MaSach"));
                phieuMuon.setSoLuongMuon(rs.getInt("SoLuongMuon"));
                phieuMuon.setNgayMuon(rs.getDate("NgayMuon"));
                phieuMuon.setNgayTraDuKien(rs.getDate("NgayTraDuKien"));
                phieuMuon.setNgayTraThucTe(rs.getDate("NgayTraThucTe"));
                phieuMuon.setTinhTrang(rs.getString("TinhTrang"));
                return phieuMuon;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy phiếu mượn: " + e.getMessage());
        }
        return null;
    }
}
