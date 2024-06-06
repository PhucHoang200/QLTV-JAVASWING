package com.librarymanagement.models;

import java.util.Date;

public class PhieuMuon {
    private String maPhieuMuon;
    private String maDocGia;
    private String maSach;
    private int soLuongMuon;
    private Date ngayMuon;
    private Date ngayTraDuKien;
    private Date ngayTraThucTe;
    private String tinhTrang;

    public PhieuMuon() {
	}

	public PhieuMuon(String maPhieuMuon, String maDocGia, String maSach, int soLuongMuon, Date ngayMuon,
			Date ngayTraDuKien, Date ngayTraThucTe, String tinhTrang) {
		this.maPhieuMuon = maPhieuMuon;
		this.maDocGia = maDocGia;
		this.maSach = maSach;
		this.soLuongMuon = soLuongMuon;
		this.ngayMuon = ngayMuon;
		this.ngayTraDuKien = ngayTraDuKien;
		this.ngayTraThucTe = ngayTraThucTe;
		this.tinhTrang = tinhTrang;
	}

	// Getter and Setter methods

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(Date ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public String getTinhTrang() {
    	return tinhTrang;
    }
	public void setTinhTrang(String string, String tinhTrang) {
		this.tinhTrang = tinhTrang;
		
	}

	public Date getNgayTraThucTe() {
		return ngayTraThucTe;
	}

	public void setNgayTraThucTe(Date ngayTraThucTe) {
		this.ngayTraThucTe = ngayTraThucTe;
	}

	public void setTinhTrang(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getYear() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMonth(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setYear(int int1) {
		// TODO Auto-generated method stub
		
	}


	
}
