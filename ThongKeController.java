package com.librarymanagement.controllers;

import com.librarymanagement.models.PhieuMuon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeController {
    private static final String connUrl = "jdbc:sqlserver://LAPTOP-VI7NDRKV:1433;" +
            "user=sa;password=123456789;databaseName=QLTVTEST;encrypt=false";

    public List<PhieuMuon> getPhieuMuonData(int year, int month) {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT MaPhieuMuon, MaDocGia, MaSach, SoLuongMuon, MONTH(NgayMuon) as Month, YEAR(NgayMuon) as Year FROM PhieuMuon WHERE YEAR(NgayMuon) = ? AND MONTH(NgayMuon) = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PhieuMuon pm = new PhieuMuon();
                    pm.setMaPhieuMuon(rs.getString("MaPhieuMuon"));
                    pm.setMaDocGia(rs.getString("MaDocGia"));
                    pm.setMaSach(rs.getString("MaSach"));
                    pm.setSoLuongMuon(rs.getInt("SoLuongMuon"));
                    pm.setMonth(rs.getInt("Month"));
                    pm.setYear(rs.getInt("Year"));
                    list.add(pm);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalBorrowed(int year, int month) {
        String sql = "SELECT SUM(SoLuongMuon) AS Total FROM PhieuMuon WHERE YEAR(NgayMuon) = ? AND MONTH(NgayMuon) = ?";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalBooks() {
        String sql = "SELECT COUNT(DISTINCT MaSach) AS TotalBooks FROM PhieuMuon";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("TotalBooks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalReaders() {
        String sql = "SELECT COUNT(DISTINCT MaDocGia) AS TotalReaders FROM PhieuMuon";
        try (Connection conn = DriverManager.getConnection(connUrl);
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("TotalReaders");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
