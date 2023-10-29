import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentManager {

    public static JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a form for making payments
        JLabel nameLabel = new JLabel("Member Name:");
        JTextField nameField = new JTextField(20);
        JLabel membershipIdLabel = new JLabel("Membership ID:");
        JTextField membershipIdField = new JTextField(10);
        JLabel amountLabel = new JLabel("Payment Amount:");
        JTextField amountField = new JTextField(10);
        JButton submitPaymentButton = new JButton("Submit Payment");

        // Add form components to a panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(membershipIdLabel);
        formPanel.add(membershipIdField);
        formPanel.add(amountLabel);
        formPanel.add(amountField);
        formPanel.add(new JLabel()); // Empty space
        formPanel.add(submitPaymentButton);

        // Create a table model for displaying payment history
        DefaultTableModel paymentTableModel = new DefaultTableModel();
        paymentTableModel.addColumn("Payment Date");
        paymentTableModel.addColumn("Member Name");
        paymentTableModel.addColumn("Payment Amount");

        JTable paymentTable = new JTable(paymentTableModel);
        JScrollPane paymentScrollPane = new JScrollPane(paymentTable);

        // Add the payment form and payment history table to the main panel
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(paymentScrollPane, BorderLayout.CENTER);

            // action listener for the submitPaymentButton to process payments
        submitPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberName = nameField.getText();
                String membershipId = membershipIdField.getText();
                String paymentAmount = amountField.getText();

                // Update the payment history table with the payment information
                paymentTableModel.addRow(new Object[]{getCurrentDateTime(), memberName, paymentAmount});

                // Clear the input fields after processing the payment
                nameField.setText("");
                membershipIdField.setText("");
                amountField.setText("");
            }
        });

        return panel;
    }

    private static String getCurrentDateTime() {
        // Helper method to get the current date and time as a string
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
