package com.librarymanagement.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_1; // Declare this panel at the class level

    /**
     * Launch the application.
     */
  /*  public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainView frame = new MainView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(60, 60, 1398, 596);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 128));
        panel.setBounds(0, 0, 330, 568);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(128, 255, 255));
        panel_2.setBounds(0, 0, 330, 143);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\HOANG PHUC\\Downloads\\Books-2-icon (1).png"));
        lblNewLabel.setBounds(0, 0, 110, 143);
        panel_2.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ THƯ VIỆN");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(110, 0, 220, 143);
        panel_2.add(lblNewLabel_1);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        panel_3.setBounds(22, 168, 283, 36);
        panel.add(panel_3);
        panel_3.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Quản lý sách");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(21, 10, 249, 16);
        panel_3.add(lblNewLabel_2);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        panel_3_1.setBounds(22, 214, 283, 36);
        panel.add(panel_3_1);
        panel_3_1.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("Quản lý độc giả");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBackground(new Color(240, 240, 240));
        lblNewLabel_3.setBounds(10, 10, 249, 16);
        panel_3_1.add(lblNewLabel_3);

        JPanel panel_3_2 = new JPanel();
        panel_3_2.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        panel_3_2.setBounds(22, 260, 283, 36);
        panel.add(panel_3_2);
        panel_3_2.setLayout(null);

        JLabel lblNewLabel_4 = new JLabel("Quản lý mượn/trả");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(10, 10, 249, 16);
        panel_3_2.add(lblNewLabel_4);

        JPanel panel_4 = new JPanel();
        panel_4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showThongKeFrame();
        	}
        });
        panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        panel_4.setBounds(22, 306, 283, 36);
        panel.add(panel_4);
        panel_4.setLayout(null);

        JLabel lblNewLabel_6 = new JLabel("Thống kê");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6.setBounds(10, 10, 249, 16);
        panel_4.add(lblNewLabel_6);

        JPanel panel_5 = new JPanel();
        panel_5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        		LoginView loginView = new LoginView();
        		loginView.setVisible(true);
        	}
        });
        panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        panel_5.setBounds(22, 352, 283, 36);
        panel.add(panel_5);
        panel_5.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Đăng xuất");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_7.setBounds(10, 10, 249, 16);
        panel_5.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Xin chào admin");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setForeground(new Color(0, 0, 0));
        lblNewLabel_8.setBackground(new Color(255, 255, 255));
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_8.setBounds(10, 409, 320, 30);
        panel.add(lblNewLabel_8);

        panel_1 = new JPanel(); // Initialize this panel here
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(328, 0, 1058, 559);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // Add mouse listener to the "Quản lý sách" label to open SachFrame
        panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSachFrame();
            }
        });
        
        panel_3_1.addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseClicked(MouseEvent e) {
                 showDocGiaFrame();
             }
        });;
        
        panel_3_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showPhieuMuonFrame();
        	}
        });
    }

    // Method to show the SachFrame in panel_1
    private void showSachFrame() {
        panel_1.removeAll();
        SachFrame sachFrame = new SachFrame();
        sachFrame.setBounds(0, 0, panel_1.getWidth(), panel_1.getHeight());
        //sachFrame.setVisible(true);
        panel_1.setLayout(new BorderLayout());
        panel_1.add(sachFrame.getContentPane(), BorderLayout.CENTER);
        panel_1.revalidate();
    }
    
    private void showDocGiaFrame() {
    	panel_1.removeAll();
    	DocGiaFrame docgiaFrame = new DocGiaFrame();
    	docgiaFrame.setBounds(0, 0, panel_1.getWidth(), panel_1.getHeight());
    	panel_1.setLayout(new BorderLayout());
    	panel_1.add(docgiaFrame.getContentPane(), BorderLayout.CENTER);
    	panel_1.revalidate();
    }
    
    private void showPhieuMuonFrame() {
    	panel_1.removeAll();
    	PhieuMuonFrame phieumuonFrame = new PhieuMuonFrame();
    	phieumuonFrame.setBounds(0, 0, panel_1.getWidth(), panel_1.getHeight());
    	panel_1.setLayout(new BorderLayout());
    	panel_1.add(phieumuonFrame.getContentPane(), BorderLayout.CENTER);
    	panel_1.revalidate();
    }
    
    private void showThongKeFrame() {
    	panel_1.removeAll();
    	ThongKeFrame thongKeFrame = new ThongKeFrame();
    	thongKeFrame.setBounds(0, 0, panel_1.getWidth(), panel_1.getHeight());
    	panel_1.setLayout(new BorderLayout());
    	panel_1.add(thongKeFrame.getContentPane(), BorderLayout.CENTER);
    	panel_1.revalidate();
    }
}
