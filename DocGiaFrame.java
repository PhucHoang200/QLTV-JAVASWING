package com.librarymanagement.views;

import com.librarymanagement.controllers.DocGiaController;
import com.librarymanagement.models.DocGia;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class DocGiaFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JDateChooser dateChooser;
    private JTextField textField_4;
    private JTextField textField_5;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private ButtonGroup buttonGroup;
    private JTable table;
    private DocGiaController controller;
    private JTextField searchField;
    private JButton searchButton;
    private JTextField textField_3;

    public static void main(String[] args) {
        try {
            DocGiaFrame frame = new DocGiaFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DocGiaFrame() {
        setTitle("Quản lý độc giả");
        setBounds(100, 100, 1080, 546);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controller = new DocGiaController();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);

        JLabel lblNewLabel = new JLabel("Quản lý độc giả");
        lblNewLabel.setForeground(new Color(128, 128, 0));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblNewLabel.setBounds(10, 10, 1056, 43);
        contentPanel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 329, 852, 167);
        contentPanel.add(scrollPane);

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
        	},
        	new String[] {
        		"M\u00E3 \u0111\u1ED9c gi\u1EA3", "H\u1ECD t\u00EAn", "Email", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "\u0110\u1ECBa ch\u1EC9", "S\u0110T", "Vai tr\u00F2"
        	}
        ));
        scrollPane.setViewportView(table);

        // Thêm MouseListener vào JTable
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ hàng được chọn
                    String maDocGia = table.getValueAt(selectedRow, 0).toString();
                    String hoTen = table.getValueAt(selectedRow, 1).toString();
                    String email = table.getValueAt(selectedRow, 2).toString();
                    String ngaySinh = table.getValueAt(selectedRow, 3).toString();
                    String gioiTinh = table.getValueAt(selectedRow, 4).toString();
                    String diaChi = table.getValueAt(selectedRow, 5).toString();
                    String sdt = table.getValueAt(selectedRow, 6).toString();
                    String role = table.getValueAt(selectedRow, 7).toString();
                    
                    // Điền dữ liệu vào các JTextField
                    textField.setText(maDocGia);
                    textField_1.setText(hoTen);
                    textField_2.setText(email);
                    dateChooser.setDate(Date.valueOf(ngaySinh));
                    if (gioiTinh.equals("Nam")) {
                        rdbtnNewRadioButton.setSelected(true);
                    } else {
                        rdbtnNewRadioButton_1.setSelected(true);
                    }
                    textField_3.setText(role);
                    textField_4.setText(diaChi);
                    textField_5.setText(sdt);
                }
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Mã độc giả");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 132, 78, 33);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Họ tên");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(10, 176, 59, 33);
        contentPanel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Email");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2.setBounds(10, 220, 51, 33);
        contentPanel.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Ngày sinh");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_3.setBounds(10, 264, 78, 33);
        contentPanel.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Giới tính");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_4.setBounds(363, 132, 78, 33);
        contentPanel.add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Địa chỉ");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_5.setBounds(363, 176, 59, 33);
        contentPanel.add(lblNewLabel_1_5);

        JLabel lblNewLabel_1_6 = new JLabel("SĐT");
        lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_6.setBounds(363, 220, 78, 33);
        contentPanel.add(lblNewLabel_1_6);

        textField = new JTextField();
        textField.setBounds(98, 134, 176, 33);
        textField.setEditable(false);
        contentPanel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(98, 178, 176, 33);
        contentPanel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(98, 220, 176, 33);
        contentPanel.add(textField_2);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(98, 266, 176, 33);
        contentPanel.add(dateChooser);

        rdbtnNewRadioButton = new JRadioButton("Nam");
        rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnNewRadioButton.setBounds(445, 132, 78, 33);
        contentPanel.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Nữ");
        rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnNewRadioButton_1.setBounds(546, 132, 103, 33);
        contentPanel.add(rdbtnNewRadioButton_1);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnNewRadioButton);
        buttonGroup.add(rdbtnNewRadioButton_1);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(445, 178, 176, 33);
        contentPanel.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(445, 222, 176, 33);
        contentPanel.add(textField_5);

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSearch.setBounds(580, 63, 78, 33);
        contentPanel.add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(683, 63, 210, 33);
        contentPanel.add(searchField);

        searchButton = new JButton("Tìm kiếm");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchButton.setBounds(923, 63, 115, 33);
        contentPanel.add(searchButton);

        // Add action listener for search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                if (!keyword.trim().isEmpty()) {
                    controller.searchDocGia(keyword, table);
                } else {
                    JOptionPane.showMessageDialog(null, "Nhập thông tin để tìm kiếm.");
                }
            }
        });

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DocGia docGia = new DocGia();
                    docGia.setMaDocGia(controller.generateMaDocGia());
                    docGia.setTenDocGia(textField_1.getText());
                    String email = textField_2.getText();
                    if (!controller.isValidEmail(email)) {
                        throw new IllegalArgumentException("Invalid email format");
                    }
                    docGia.setEmail(email);
                    docGia.setNgaySinh(new Date(dateChooser.getDate().getTime()));
                    docGia.setGioiTinh(rdbtnNewRadioButton.isSelected() ? "Nam" : "Nữ");
                    docGia.setDiaChi(textField_4.getText());
                    docGia.setSdt(textField_5.getText());
                    docGia.setRole(textField_3.getText());
                    controller.addDocGia(docGia);
                    DocGiaController.checkConnection(table);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(923, 150, 115, 33);
        contentPanel.add(btnNewButton);

        JButton btnSa = new JButton("Sửa");
        btnSa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DocGia docGia = new DocGia();
                    docGia.setMaDocGia(textField.getText());
                    docGia.setTenDocGia(textField_1.getText());
                    String email = textField_2.getText();
                    if (!controller.isValidEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Invalid email format.");
                        return;
                    }
                    docGia.setEmail(email);
                    docGia.setNgaySinh(new Date(dateChooser.getDate().getTime()));
                    docGia.setGioiTinh(rdbtnNewRadioButton.isSelected() ? "Nam" : "Nữ");
                    docGia.setDiaChi(textField_4.getText());
                    docGia.setSdt(textField_5.getText());
                    docGia.setRole(textField_3.getText());
                    controller.updateDocGia(selectedRow, docGia);
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một hàng để sửa.");
                }
            }
        });
        btnSa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSa.setBounds(923, 193, 117, 33);
        contentPanel.add(btnSa);

        JButton btnXa = new JButton("Xóa");
        btnXa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    controller.deleteDocGia(selectedRow, table);
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một hàng để xóa.");
                }
            }
        });
        btnXa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnXa.setBounds(923, 236, 115, 33);
        contentPanel.add(btnXa);

        JButton btnLu = new JButton("Load");
        btnLu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DocGiaController.checkConnection(table);
            }
        });
        btnLu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLu.setBounds(923, 107, 115, 33);
        contentPanel.add(btnLu);
        
        JLabel lblNewLabel_2 = new JLabel("Vai trò");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(361, 264, 71, 33);
        contentPanel.add(lblNewLabel_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(445, 273, 176, 33);
        contentPanel.add(textField_3);
    }
}
