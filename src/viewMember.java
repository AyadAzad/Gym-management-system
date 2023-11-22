import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class viewMember extends JFrame {

    protected static DefaultTableModel membersTableModel;
    protected static JTable membersTable;

    public viewMember() {
        setTitle("View Member Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel formPanel = createMembersPanel();
        JPanel buttonPanel = createButtonPanel(); // Add the button panel
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set the frame visible
        setVisible(true);
        fetchData();
    }

    public static JPanel createMembersPanel() {
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setOpaque(true);
        formPanel.setBackground(new Color(250, 250, 250)); // Light Gray

        // Creating a table model for members, later on we'll use Database for this purpose
        membersTableModel = new DefaultTableModel();
        membersTableModel.addColumn("Member ID");
        membersTableModel.addColumn("Name");
        membersTableModel.addColumn("Age");
        membersTableModel.addColumn("Contact Number");
        membersTableModel.addColumn("Period");
        membersTableModel.addColumn("Coach");

        membersTable = new JTable(membersTableModel);
        membersTable.setOpaque(true);
        membersTable.setBackground(new Color(255, 255, 255)); // White
        membersTable.setForeground(new Color(33, 33, 33)); // Dark Gray

        membersTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        membersTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        membersTable.getTableHeader().setBackground(new Color(34, 167, 240)); // Blue
        membersTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        membersTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane membersScrollPane = new JScrollPane(membersTable);
        membersScrollPane.getViewport().setBackground(new Color(250, 250, 250)); // Light Gray

        formPanel.add(membersScrollPane, BorderLayout.CENTER);

        return formPanel;
    }

    protected static void fetchData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "SELECT * FROM members";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int memberId = resultSet.getInt("member_id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        String contact = resultSet.getString("contact_number");
                        int period = resultSet.getInt("period");
                        String coach = resultSet.getString("coach");
                        membersTableModel.addRow(new Object[]{memberId, name, age, contact, period, coach});
                        // Add data to the table model
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JPanel createButtonPanel() {
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
