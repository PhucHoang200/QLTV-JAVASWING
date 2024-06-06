package com.librarymanagement.views;

import com.librarymanagement.controllers.ThongKeController;
import com.librarymanagement.models.PhieuMuon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThongKeFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public ThongKeFrame() {
        setTitle("Thống Kê Phiếu Mượn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Nhập tháng và năm từ người dùng
        String yearInput = JOptionPane.showInputDialog(this, "Nhập năm:");
        int year = Integer.parseInt(yearInput);
        String monthInput = JOptionPane.showInputDialog(this, "Nhập tháng:");
        int month = Integer.parseInt(monthInput);

        ThongKeController controller = new ThongKeController();
        List<PhieuMuon> data = controller.getPhieuMuonData(year, month);

        // Hiển thị thông tin thống kê
        int totalBorrowed = controller.getTotalBorrowed(year, month);
        int totalBooks = controller.getTotalBooks();
        int totalReaders = controller.getTotalReaders();

        JLabel labelStatistics = new JLabel(String.format("Tổng phiếu mượn: %d, Tổng sách: %d, Tổng độc giả: %d", totalBorrowed, totalBooks, totalReaders));
        JPanel panelStats = new JPanel();
        panelStats.add(labelStatistics);
        add(panelStats, BorderLayout.NORTH);

        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int total = data.stream().mapToInt(PhieuMuon::getSoLuongMuon).sum();
        dataset.addValue(total, "Phiếu Mượn", String.format("%02d/%d", month, year));

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart(
            "Số Lượng Mượn Theo Tháng",
            "Tháng/Năm",
            "Số Lượng",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(780, 500));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

       // setVisible(true);
    }

  /*  public static void main(String[] args) {
        new ThongKeFrame();
    } */
}
