package com.librarymanagement.views;

import javax.swing.*;

import com.librarymanagement.models.UserDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    public static void main(String[] args) {
        try {
            LoginView frame = new LoginView();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginView() {
        getContentPane().setBackground(new Color(0, 128, 192));
        userDAO = new UserDAO();
        setLocationRelativeTo(null);

        setTitle("Đăng nhập");
        setBounds(100, 100, 909, 366);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblNewLabel.setBounds(10, 10, 829, 50);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(64, 101, 177, 55);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(64, 171, 177, 55);
        getContentPane().add(lblNewLabel_2);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setBounds(264, 101, 360, 55);
        getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(264, 171, 360, 55);
        getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Đăng nhập");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 128, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(264, 248, 150, 50);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                // Kiểm tra thông tin đăng nhập và mật khẩu từ cơ sở dữ liệu
                if (userDAO.checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");

                    // Phân quyền người dùng và chuyển hướng tương ứng
                    String role = userDAO.getUserRole(username);
                    if ("admin".equalsIgnoreCase(role)) {
                        MainView mainView = new MainView();
                        mainView.setVisible(true);
                    } else {
                        DocGiaMainView docgiamainView = new DocGiaMainView(username); // Truyền tên tài khoản vào DocGiaMainView
                        docgiamainView.setVisible(true);
                    }

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập không thành công!");
                }
            }
        });
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Đăng ký");
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(255, 128, 64));
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setBounds(474, 248, 150, 50);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
                registerFrame.setVisible(true);
            }
        });
        getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\HOANG PHUC\\Downloads\\icon1.png"));
        lblNewLabel_3.setBounds(623, 78, 216, 234);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("X");
        lblNewLabel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(851, 0, 58, 34);
        getContentPane().add(lblNewLabel_4);

        setUndecorated(true);
    }
}
