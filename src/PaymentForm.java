

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class PaymentForm extends JFrame {
    private JTextField nameField;
    private JTextField memberId;
    private JTextField paymentAmount;
    public PaymentForm() {
        setTitle("Payment Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel formPanel = createPaymentPanel();
        getContentPane().add(formPanel);


        // Set the frame visible
        setVisible(true);
    }

    public JPanel createPaymentPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10, 10, 10, 10);

        formPanel.setPreferredSize(new Dimension(400, 300));
        formPanel.setBackground(new Color(44, 62, 80)); // Dark Blue

        JLabel[] labels = {
                new JLabel("Name:"),
                new JLabel("Member Id:"),
                new JLabel("Amount to pay:"),
        };

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        }

        nameField = createFormattedTextField();
        memberId = createFormattedTextField();
        paymentAmount = createFormattedTextField();

        JButton submitButton = new JButton("Pay");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitButton.setBackground(new Color(34, 167, 240));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calculateButton.setBackground(new Color(183, 134, 248));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton returnButton = new JButton("Return to Main");
        returnButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        returnButton.setBackground(new Color(192, 57, 43));
        returnButton.setForeground(Color.WHITE);
        returnButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to return to the main frame
                dispose(); // Close the current frame
                new mainWindow(); // Open the main frame
            }
        });

        gridBag.gridy = 0;
        addToFormPanel(formPanel, labels[0], nameField, gridBag, 0, 0);

        gridBag.gridy = 1;
        addToFormPanel(formPanel, labels[1], memberId, gridBag, 0, 1);

        gridBag.gridy = 2;
        addToFormPanel(formPanel, new JLabel(), calculateButton, gridBag, 0, 2);

        gridBag.gridy = 3;
        addToFormPanel(formPanel, labels[2], paymentAmount, gridBag, 0, 3);

        gridBag.gridy = 4;
        addToFormPanel(formPanel, new JLabel(), submitButton, gridBag, 0, 4);

        gridBag.gridy = 5;
        addToFormPanel(formPanel, new JLabel(), returnButton, gridBag, 0, 5);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberName = nameField.getText();
                paymentAmount.setText(calculatePaymentAmount(memberName));
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberName = nameField.getText();
                String membershipId = memberId.getText();
                String calculatedpaymentAmount = paymentAmount.getText();
                if (memberName.isEmpty() || membershipId.isEmpty() || calculatedpaymentAmount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled");
                } else {
                    try {
                        insertPaymentData(memberName, calculatedpaymentAmount);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        return formPanel;
    }
    private void addToFormPanel(JPanel formPanel, JLabel label, JComponent component,
                                GridBagConstraints gridBag, int x, int y) {
        addToFormPanel(formPanel, label, component, gridBag, x, y, 1, 1);
    }

    private void addToFormPanel(JPanel formPanel, JLabel label, JComponent component,
                                GridBagConstraints gridBag, int x, int y, int width, int height) {
        gridBag.gridx = x;
        gridBag.gridy = y;
        gridBag.gridwidth = width;
        gridBag.gridheight = height;
        formPanel.add(label, gridBag);

        gridBag.gridx = x + width;
        gridBag.gridwidth = 1;
        formPanel.add(component, gridBag);
    }
    private JTextField createFormattedTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBackground(new Color(108, 122, 137));
        textField.setForeground(Color.WHITE);
        textField.setPreferredSize(new Dimension(200, 40));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }
    private static String calculatePaymentAmount(String memberName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String selectQuery = "SELECT period FROM members WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, memberName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int membershipPeriod = resultSet.getInt("period");
                        // Calculate the payment amount (assuming $5 per day)
                        BigDecimal paymentAmount = new BigDecimal(membershipPeriod * 5);
                        return paymentAmount.toString();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private static int getMemberId(String memberName) throws SQLException {
        int memberId = -1; // Default value if member is not found

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
        String selectQuery = "SELECT member_id FROM members WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, memberName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    memberId = resultSet.getInt("member_id");
                }
            }
        }

        return memberId;
    }

    private static void insertPaymentData(String memberName, String paymentAmount) throws SQLException {
        // Assuming you have a method to retrieve the member_id based on the member's name
        int memberId = getMemberId(memberName);

        // Assuming you have a method to get the current date and time
        String currentDateTime = getCurrentDateTime();
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
        // Insert data into the payments table
        String insertQuery = "INSERT INTO payments (payment_date,member_name, member_id, payment_amount, payment_status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, currentDateTime);
            preparedStatement.setString(2, memberName);
            preparedStatement.setInt(3, memberId);
            preparedStatement.setString(4, calculatePaymentAmount(memberName));
            preparedStatement.setString(5, "Paid"); // Assuming an initial status of "Paid"
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCurrentDateTime() {
        // Helper method to get the current date and time as a string
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }



}
