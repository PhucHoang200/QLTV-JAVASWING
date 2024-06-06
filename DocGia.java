package com.librarymanagement.models;

import java.util.Date;

public class DocGia {
    private String maDocGia; // Change the data type to int
    private String tenDocGia;
    private String email; // Change the variable name to 'email'
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String role;

    public DocGia() {
	}
    

	public DocGia(String maDocGia, String tenDocGia, String email, Date ngaySinh, String gioiTinh, String diaChi,
			String sdt, String role) {
		this.maDocGia = maDocGia;
		this.tenDocGia = tenDocGia;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.setRole(role);
	}


	// Getters and setters
    public String getMaDocGia() { return maDocGia; }
    public void setMaDocGia(String maDocGia) { this.maDocGia = maDocGia; } // Change the parameter type to int
    public String getTenDocGia() { return tenDocGia; }
    public void setTenDocGia(String tenDocGia) { this.tenDocGia = tenDocGia; }
    public String getEmail() { return email; } // Change the method name to getEmail()
    public void setEmail(String email) { this.email = email; } // Change the parameter name to 'email'
    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
}
