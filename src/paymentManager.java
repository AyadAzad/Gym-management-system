import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class paymentManager extends JFrame{
private static DefaultTableModel paymentTableModel;
    protected static JTable paymentTable;
public paymentManager(){
    setTitle("Payment Manager");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 500);
    setLocationRelativeTo(null);

    JPanel formPanel = createpaymentTable();
    JPanel btnPanel = createButtonPanel();
    getContentPane().add(formPanel);
    getContentPane().add(btnPanel, BorderLayout.SOUTH);

    // Set the frame visible
    setVisible(true);
    loadPaymentHistoryData();
}
public JPanel createpaymentTable(){

    JPanel formPanel = new JPanel(new BorderLayout());
    formPanel.setOpaque(true);
    formPanel.setBackground(new Color(250, 250, 250)); // Light Gray

    // Creating a table model for members, later on we'll use Database for this purpose
    paymentTableModel = new DefaultTableModel();
    paymentTableModel.addColumn("member name");
    paymentTableModel.addColumn("payment amount");
    paymentTableModel.addColumn("payment date");
    paymentTableModel.addColumn("status");

    paymentTable = new JTable(paymentTableModel);
    paymentTable.setOpaque(true);
    paymentTable.setBackground(new Color(255, 255, 255)); // White
    paymentTable.setForeground(new Color(33, 33, 33)); // Dark Gray

    paymentTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    paymentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
    paymentTable.getTableHeader().setBackground(new Color(34, 167, 240)); // Blue
    paymentTable.getTableHeader().setForeground(Color.WHITE);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    paymentTable.setDefaultRenderer(Object.class, centerRenderer);

    JScrollPane membersScrollPane = new JScrollPane(paymentTable);
    membersScrollPane.getViewport().setBackground(new Color(250, 250, 250)); // Light Gray

    formPanel.add(membersScrollPane, BorderLayout.CENTER);

    return formPanel;
}


    private void loadPaymentHistoryData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String selectQuery = "SELECT payment_date, member_name, payment_amount, payment_status FROM payments";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String paymentDate = resultSet.getString("payment_date");
                    String memberName = resultSet.getString("member_name");
                    BigDecimal paymentAmount = resultSet.getBigDecimal("payment_amount");
                    String status = resultSet.getString("payment_status");

                    // Add the data to the table model
                    Object[] rowData = {paymentDate, memberName, paymentAmount, status};
                    paymentTableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton returnButton = new JButton("Return to Main");
        returnButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        returnButton.setBackground(new Color(46, 204, 113)); // Green
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new mainWindow();
            }
        });

        buttonPanel.add(returnButton);

        return buttonPanel;
    }

}
