package com.librarymanagement.models;

import java.util.Date;

public class Sach {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private Date namXuatBan;
    private String soLuong;
    private String tinhTrang;

    public Sach() {
	}

	public Sach(String maSach, String tenSach, String tacGia, String theLoai, Date namXuatBan, String soLuong,
			String tinhTrang) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.theLoai = theLoai;
		this.namXuatBan = namXuatBan;
		this.soLuong = soLuong;
		this.tinhTrang = tinhTrang;
	}

	// Getters and setters for each field
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public Date getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(Date namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
