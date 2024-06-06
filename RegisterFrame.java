package com.librarymanagement.views;

import javax.swing.*;
import com.librarymanagement.models.User;
import com.librarymanagement.models.UserDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField phoneField;

    private UserDAO userDAO;

    public static void main(String[] args) {
        try {
            RegisterFrame frame = new RegisterFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegisterFrame() {
        userDAO = new UserDAO();

        setTitle("ĐĂNG KÝ TÀI KHOẢN");
        setBounds(100, 100, 600, 606);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(37, 111, 148, 30);
        getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameField.setBounds(199, 102, 200, 48);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(37, 193, 106, 30);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(199, 175, 200, 48);
        getContentPane().add(passwordField);

        JLabel lblFullName = new JLabel("Họ tên:");
        lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblFullName.setBounds(37, 265, 95, 30);
        getContentPane().add(lblFullName);

        fullNameField = new JTextField();
        fullNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        fullNameField.setBounds(199, 247, 200, 48);
        getContentPane().add(fullNameField);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEmail.setBounds(37, 334, 79, 30);
        getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailField.setBounds(199, 325, 200, 48);
        getContentPane().add(emailField);

        JLabel lblPhone = new JLabel("Số điện thoại:");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPhone.setBounds(37, 410, 125, 43);
        getContentPane().add(lblPhone);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        phoneField.setBounds(199, 407, 200, 48);
        getContentPane().add(phoneField);

        JButton registerButton = new JButton("ĐĂNG KÝ");
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        registerButton.setBounds(214, 493, 148, 49);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Kiểm tra nếu một trong các trường thông tin là trống
                if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                // Kiểm tra định dạng email
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ!");
                    return;
                }

                // Kiểm tra số điện thoại
                if (!phone.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số!");
                    return;
                }

                // Kiểm tra tên đăng nhập đã tồn tại chưa
                if (userDAO.checkUsernameExist(username)) {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!");
                    return;
                }

                // Tạo đối tượng User và thực hiện đăng ký
                User user = new User(username, password, fullName, email, phone, "user"); // Set role as 'user'
                if (userDAO.register(user)) {
                    JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                    dispose(); // Đóng JFrame đăng ký nếu đăng ký thành công
                    // Chuyển về JFrame login
                    LoginView login = new LoginView();
                    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    login.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng ký không thành công!");
                }
            }
        });
        getContentPane().add(registerButton);
        
        JLabel lblNewLabel = new JLabel("ĐĂNG KÝ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(10, 10, 576, 45);
        getContentPane().add(lblNewLabel);
    }
}
