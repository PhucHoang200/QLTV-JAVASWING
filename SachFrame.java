package com.librarymanagement.views;

import com.librarymanagement.controllers.SachController;
import com.librarymanagement.models.Sach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;


public class SachFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JComboBox<String> comboBox;
    private JDateChooser dateChooser;
    private JTextField textField_3;
    private static JTable table;
    private SachController controller;
	private JTextField textField_4;

    public static void main(String[] args) {
        try {
        	SachFrame sachframe = new SachFrame();
            sachframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sachframe.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public SachFrame() {
    	setBackground(new Color(240, 240, 240));
        controller = new SachController();
        setTitle("Quản lý sách");
        setBounds(100, 100, 1080, 542);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);

        JLabel lblNewLabel = new JLabel("Quản lý sách");
        lblNewLabel.setForeground(new Color(128, 128, 0));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblNewLabel.setBounds(10, 10, 1054, 43);
        contentPanel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 335, 920, 155);
        contentPanel.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setModel(new DefaultTableModel(
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
        	new String[] {
        		"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "Th\u1EC3 lo\u1EA1i", "N\u0103m xu\u1EA5t b\u1EA3n", "S\u1ED1 l\u01B0\u1EE3ng"
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
                    String maSach = table.getValueAt(selectedRow, 0).toString();
                    String tenSach = table.getValueAt(selectedRow, 1).toString();
                    String tacGia = table.getValueAt(selectedRow, 2).toString();
                    String theLoai = table.getValueAt(selectedRow, 3).toString();
                    String namXuatBan = table.getValueAt(selectedRow, 4).toString();
                    String soLuong = table.getValueAt(selectedRow, 5).toString();

                    // Điền dữ liệu vào các JTextField
                    textField.setText(maSach);
                    textField_1.setText(tenSach);
                    textField_2.setText(tacGia);
                    comboBox.setSelectedItem(theLoai);
                    try {
                        // Chuyển đổi chuỗi thành đối tượng Date với định dạng ngày tháng năm
                    	java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(namXuatBan);
                        dateChooser.setDate(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    textField_3.setText(soLuong);
                }
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Mã sách");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(21, 134, 59, 33);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Tên sách");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(21, 195, 59, 33);
        contentPanel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Tác giả");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2.setBounds(21, 272, 51, 33);
        contentPanel.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Thể loại");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_3.setBounds(352, 134, 59, 33);
        contentPanel.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Năm xuất bản");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_4.setBounds(352, 195, 96, 33);
        contentPanel.add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Số lượng");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_5.setBounds(352, 272, 73, 33);
        contentPanel.add(lblNewLabel_1_5);

        textField = new JTextField();
        textField.setBounds(97, 133, 194, 40);
        textField.setEditable(false); // Set to non-editable
        contentPanel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(97, 194, 194, 40);
        contentPanel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(97, 262, 194, 43);
        contentPanel.add(textField_2);

        String[] genres = {"Tiểu thuyết", "Truyện thiếu nhi", "Kinh điển", "Tâm lý", "Văn học"};
        comboBox = new JComboBox<>(genres);
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Tiểu thuyết", "Truyện thiếu nhi", "Kinh điển", "Tâm lý", "Văn học"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(482, 130, 201, 40);
        contentPanel.add(comboBox);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(482, 195, 201, 46);
        contentPanel.add(dateChooser);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(482, 269, 201, 43);
        contentPanel.add(textField_3);

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Sach sach = new Sach();
                    sach.setTenSach(textField_1.getText());
                    sach.setTacGia(textField_2.getText());
                    sach.setTheLoai((String) comboBox.getSelectedItem());
                    sach.setNamXuatBan(new Date(dateChooser.getDate().getTime()));
                    sach.setSoLuong(textField_3.getText());
                    controller.addSach(sach);

                    // Cập nhật bảng sau khi thêm mới
                    SachController.checkConnection(table);
                } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng: " + ex.getMessage());
                        }
                    }
                });
                btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                btnNewButton.setBounds(933, 151, 103, 33);
                contentPanel.add(btnNewButton);

                JButton btnSaCh = new JButton("Sửa");
                btnSaCh.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            Sach sach = new Sach();
                            sach.setMaSach(textField.getText()); // Thêm dòng này để gán giá trị cho maSach
                            sach.setTenSach(textField_1.getText());
                            sach.setTacGia(textField_2.getText());
                            sach.setTheLoai((String) comboBox.getSelectedItem());
                            sach.setNamXuatBan(new Date(dateChooser.getDate().getTime()));
                            sach.setSoLuong(textField_3.getText());
                            controller.updateSach(selectedRow, sach);
                        } else {
                            JOptionPane.showMessageDialog(null, "Chọn một hàng để sửa.");
                        }
                    }
                });

                btnSaCh.setFont(new Font("Tahoma", Font.PLAIN, 14));
                btnSaCh.setBounds(933, 195, 103, 33);
                contentPanel.add(btnSaCh);

                JButton btnXa = new JButton("Xóa");
                btnXa.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            controller.deleteSach(selectedRow, table);
                        } else {
                            JOptionPane.showMessageDialog(null, "Chọn một hàng để xóa.");
                        }
                    }
                });
                btnXa.setFont(new Font("Tahoma", Font.PLAIN, 14));
                btnXa.setBounds(933, 238, 103, 33);
                contentPanel.add(btnXa);

                JButton btnNewButton_3 = new JButton("Load");
                btnNewButton_3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SachController.checkConnection(table);
                    }
                });
                btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
                btnNewButton_3.setBounds(933, 108, 103, 33);
                contentPanel.add(btnNewButton_3);
                
                JLabel lblNewLabel_2 = new JLabel("Tìm kiếm");
                lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel_2.setBounds(487, 62, 96, 33);
                contentPanel.add(lblNewLabel_2);
                
                textField_4 = new JTextField();
                textField_4.setBounds(607, 63, 307, 34);
                contentPanel.add(textField_4);
                textField_4.setColumns(10);
                
                JButton btnNewButton_1 = new JButton("Tìm kiếm");
                btnNewButton_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		String keyword = textField_4.getText();
                        if (!keyword.trim().isEmpty()) {
                            controller.searchSach(keyword, table);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nhập thông tin để tìm kiếm.");
                        }
                	}
                });
                btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
                btnNewButton_1.setBounds(933, 64, 103, 34);
                contentPanel.add(btnNewButton_1);

            }

	
        }
