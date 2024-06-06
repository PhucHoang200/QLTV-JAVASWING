package com.librarymanagement.views;

import com.librarymanagement.controllers.PhieuMuonController;
import com.librarymanagement.models.PhieuMuon;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PhieuMuonFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textFieldMaPhieuMuon;
    private JTextField textFieldMaSach;
    private JTextField textFieldMaDocGia;
    private JTable table;
    private JDateChooser dateChooser;
    private JDateChooser dateChooser_1;
    private JSpinField spinField;
    
    public PhieuMuonFrame() {
        PhieuMuonController controller = new PhieuMuonController();
        setTitle("Quản lý mượn trả sách");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 514);
        getContentPane().setLayout(null);

        JLabel lblMaPhieuMuon = new JLabel("Mã phiếu mượn:");
        lblMaPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaPhieuMuon.setBounds(10, 20, 100, 25);
        getContentPane().add(lblMaPhieuMuon);

        textFieldMaPhieuMuon = new JTextField();
        textFieldMaPhieuMuon.setEditable(false);
        textFieldMaPhieuMuon.setBounds(151, 10, 150, 29);
        getContentPane().add(textFieldMaPhieuMuon);

        JLabel lblMaSach = new JLabel("Mã sách:");
        lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaSach.setBounds(10, 60, 100, 25);
        getContentPane().add(lblMaSach);

        textFieldMaSach = new JTextField();
        textFieldMaSach.setBounds(151, 49, 150, 36);
        getContentPane().add(textFieldMaSach);

        JLabel lblMaDocGia = new JLabel("Mã độc giả:");
        lblMaDocGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaDocGia.setBounds(10, 100, 100, 25);
        getContentPane().add(lblMaDocGia);

        textFieldMaDocGia = new JTextField();
        textFieldMaDocGia.setBounds(151, 95, 150, 36);
        getContentPane().add(textFieldMaDocGia);

        JLabel lblNgayMuon = new JLabel("Ngày mượn:");
        lblNgayMuon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNgayMuon.setBounds(10, 158, 100, 25);
        getContentPane().add(lblNgayMuon);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(151, 147, 150, 36);
        getContentPane().add(dateChooser);

        JLabel lblNgayTra = new JLabel("Ngày trả dự kiến:");
        lblNgayTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNgayTra.setBounds(10, 204, 118, 25);
        getContentPane().add(lblNgayTra);

        dateChooser_1 = new JDateChooser();
        dateChooser_1.setBounds(151, 193, 150, 36);
        getContentPane().add(dateChooser_1);

        JLabel lblNewLabel = new JLabel("Số lượng:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(605, 226, 90, 20);
        getContentPane().add(lblNewLabel);

        spinField = new JSpinField();
        spinField.setBounds(740, 217, 134, 29);
        getContentPane().add(spinField);

        JButton btnThemPhieuMuon = new JButton("Thêm");
        btnThemPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnThemPhieuMuon.setBounds(906, 261, 134, 25);
        getContentPane().add(btnThemPhieuMuon);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 260, 864, 196);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
            },
            new String[] {
                "Mã phiếu mượn", "Mã độc giả", "Mã sách", "Số lượng mượn", "Ngày mượn", "Ngày trả dự kiến", "Ngày trả thực tế", "Tình trạng"
            }
        ));
        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String maPhieuMuon = table.getValueAt(selectedRow, 0).toString();
                    String maDocGia = table.getValueAt(selectedRow, 1).toString();
                    String maSach = table.getValueAt(selectedRow, 2).toString();
                    String soLuongMuon = table.getValueAt(selectedRow, 3).toString();
                    String ngayMuon = table.getValueAt(selectedRow, 4).toString();
                    String ngayTraDuKien = table.getValueAt(selectedRow, 5).toString();
                   

                    textFieldMaPhieuMuon.setText(maPhieuMuon);
                    textFieldMaDocGia.setText(maDocGia);
                    textFieldMaSach.setText(maSach);
                    spinField.setValue(Integer.parseInt(soLuongMuon));

                    try {
                        java.util.Date dateMuon = new SimpleDateFormat("yyyy-MM-dd").parse(ngayMuon);
                        dateChooser.setDate(dateMuon);
                        java.util.Date dateTra = new SimpleDateFormat("yyyy-MM-dd").parse(ngayTraDuKien);
                        dateChooser_1.setDate(dateTra);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSua.setBounds(906, 296, 134, 25);
        getContentPane().add(btnSua);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnXoa.setBounds(906, 331, 134, 25);
        getContentPane().add(btnXoa);

        btnThemPhieuMuon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.Date ngayMuon = dateChooser.getDate();
                java.util.Date ngayTraDuKien = dateChooser_1.getDate();

                if (ngayMuon == null || ngayTraDuKien == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày mượn và ngày trả.");
                    return;
                }

                String maSach = textFieldMaSach.getText();
                int soLuongMuon = (int) spinField.getValue();

                // Kiểm tra số lượng mượn tối đa
                if (soLuongMuon > 5) {
                    JOptionPane.showMessageDialog(null, "Số lượng mượn tối đa là 5.");
                    return;
                }

                int soLuongCon = controller.getSoLuongCon(maSach); // Lấy số lượng còn lại từ cơ sở dữ liệu

                if (soLuongMuon > soLuongCon) {
                    JOptionPane.showMessageDialog(null, "Số lượng mượn vượt quá số lượng sách còn lại.");
                    return;
                }

                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMaDocGia(textFieldMaDocGia.getText());
                phieuMuon.setMaSach(maSach);
                phieuMuon.setSoLuongMuon(soLuongMuon);
                phieuMuon.setNgayMuon(new java.sql.Date(ngayMuon.getTime()));
                phieuMuon.setNgayTraDuKien(new java.sql.Date(ngayTraDuKien.getTime()));
                phieuMuon.setTinhTrang("Chưa trả", "Chưa trả");

                controller.addPhieuMuon(phieuMuon);
                controller.updateSoLuongSach(maSach, soLuongMuon); // Cập nhật số lượng sách
                controller.checkConnection(table);
            }
        });


        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String maPhieuMuon = table.getValueAt(selectedRow, 0).toString();
                    String tinhTrang = table.getValueAt(selectedRow, 7).toString(); // Giả sử cột 7 là Tình trạng

                    // Kiểm tra tình trạng phiếu mượn
                    if (controller.isPhieuMuonDaTra(maPhieuMuon)) {
                        JOptionPane.showMessageDialog(null, "Phiếu mượn đã trả không thể sửa.");
                        return;
                    }

                    java.util.Date ngayMuon = dateChooser.getDate();
                    java.util.Date ngayTraDuKien = dateChooser_1.getDate();

                    if (ngayMuon == null || ngayTraDuKien == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày mượn và ngày trả.");
                        return;
                    }

                    int soLuongMuon = (int) spinField.getValue();
                    if (soLuongMuon > 5) {
                        JOptionPane.showMessageDialog(null, "Số lượng mượn tối đa là 5.");
                        return;
                    }

                    PhieuMuon phieuMuon = new PhieuMuon();
                    phieuMuon.setMaPhieuMuon(textFieldMaPhieuMuon.getText());
                    phieuMuon.setMaDocGia(textFieldMaDocGia.getText());
                    phieuMuon.setMaSach(textFieldMaSach.getText());
                    phieuMuon.setSoLuongMuon(soLuongMuon);
                    phieuMuon.setNgayMuon(new java.sql.Date(ngayMuon.getTime()));
                    phieuMuon.setNgayTraDuKien(new java.sql.Date(ngayTraDuKien.getTime()));
                    phieuMuon.setTinhTrang(tinhTrang); // Không thay đổi tình trạng

                    if (controller.updatePhieuMuon(phieuMuon)) {
                        controller.checkConnection(table);
                        JOptionPane.showMessageDialog(null, "Cập nhật phiếu mượn thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật phiếu mượn thất bại.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn để sửa.");
                }
            }
        });



        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maPhieuMuon = textFieldMaPhieuMuon.getText();
                controller.deletePhieuMuon(maPhieuMuon);
                controller.checkConnection(table);
            }
        });

        JButton btnGuiThongBao = new JButton("Gửi thông báo");
        btnGuiThongBao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGuiThongBao.setBounds(906, 370, 134, 25);
        getContentPane().add(btnGuiThongBao);

        btnGuiThongBao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // table là JTable chứa danh sách phiếu mượn
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn để gửi thông báo.");
                    return;
                }

                String maDocGia = table.getValueAt(selectedRow, 1).toString(); // Giả sử mã độc giả ở cột thứ 2 (index 1)

                JTextField tenTaiKhoanField = new JTextField(maDocGia);
                tenTaiKhoanField.setEditable(false);
                JTextArea textArea = new JTextArea(5, 20);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Mã độc giả:"));
                panel.add(tenTaiKhoanField);
                panel.add(new JLabel("Nhập nội dung thông báo:"));
                panel.add(scrollPane);

                int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin thông báo", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String thongBao = textArea.getText();
                    if (!thongBao.isEmpty()) {
                        controller.guiThongBao(maDocGia, thongBao); // Gửi thông báo đến mã độc giả đã chọn
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung thông báo.");
                    }
                }
            }
        });

        

        
        JButton btnTraSach = new JButton("Trả sách");
        btnTraSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnTraSach.setBounds(906, 405, 134, 25);
        getContentPane().add(btnTraSach);
        
        btnTraSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traSach();
            }
        });

        
        controller.checkConnection(table);
    }
    private void traSach() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maPhieuMuon = table.getValueAt(selectedRow, 0).toString();
            String maSach = table.getValueAt(selectedRow, 2).toString(); // Giả sử cột 2 là MaSach
            int soLuongMuon = Integer.parseInt(table.getValueAt(selectedRow, 3).toString()); // Giả sử cột 3 là SoLuongMuon
            String tinhTrang = table.getValueAt(selectedRow, 7).toString(); // Giả sử cột 7 là Tình trạng

            if ("Chưa trả".equals(tinhTrang)) {
                JDialog dialog = new JDialog(this, "Trả sách", true);
                dialog.getContentPane().setLayout(new GridLayout(3, 2));
                dialog.setSize(300, 150);

                JLabel label = new JLabel("Ngày trả thực tế:");
                JDateChooser dateChooser = new JDateChooser();
                JButton btnOK = new JButton("OK");
               

                dialog.getContentPane().add(label);
                dialog.getContentPane().add(dateChooser);
                dialog.getContentPane().add(btnOK);

                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        java.util.Date ngayTraThucTe = dateChooser.getDate();
                        if (ngayTraThucTe == null) {
                            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày trả.");
                            return;
                        }
                        PhieuMuonController controller = new PhieuMuonController();
                        controller.updateTraSach(maPhieuMuon, new java.sql.Date(ngayTraThucTe.getTime()), "Đã trả", maSach, soLuongMuon);
                        controller.checkConnection(table); // Cập nhật lại bảng sau khi trả sách
                        dialog.dispose();
                    }
                });

                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Sách này đã được trả.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu mượn để trả sách.");
        }
    }


    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PhieuMuonFrame frame = new PhieuMuonFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
