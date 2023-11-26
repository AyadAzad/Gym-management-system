import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class viewCoach extends JFrame {
    protected static DefaultTableModel coachesTableModel;
    protected static JTable coachesTable;

    public viewCoach() {
        setTitle("View Coach Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(createCoachesPanel(), BorderLayout.CENTER);
        add(createReturnButton(), BorderLayout.SOUTH);
        setVisible(true);
        fetchCoachesData();
    }

    public static JPanel createCoachesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Table model for coaches
        coachesTableModel = new DefaultTableModel();
        coachesTableModel.addColumn("Coach ID");
        coachesTableModel.addColumn("Name");
        coachesTableModel.addColumn("Age");
        coachesTableModel.addColumn("Email");
        coachesTableModel.addColumn("Contact NO");

        coachesTable = new JTable(coachesTableModel);
        JScrollPane coachesScrollPane = new JScrollPane(coachesTable);

        coachesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        coachesTable.setRowHeight(30);
        coachesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        coachesTable.setSelectionBackground(new Color(173, 216, 230));
        coachesTable.setBackground(new Color(240, 248, 255));

        panel.add(coachesScrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createReturnButton() {
        JPanel buttonPanel = new JPanel();
        JButton returnButton = new JButton("Return to Main");

        returnButton.setFont(new Font("Arial", Font.BOLD, 16));
        returnButton.setBackground(new Color(0, 102, 204)); // Blue
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to navigate back to the main window or dispose of this window
                dispose(); // This will close the current window
                new mainWindow();
            }
        });
        buttonPanel.add(returnButton);
        return buttonPanel;
    }

    private static int getNewCoachID() {
        // Implement logic to generate a new unique coach ID
        return coachesTableModel.getRowCount() + 1;
    }

    protected static void fetchCoachesData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "SELECT * FROM coaches";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int memberId = resultSet.getInt("coach_id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        String email = resultSet.getString("email");
                        int contactNO = resultSet.getInt("contactNO");
                        coachesTableModel.addRow(new Object[]{memberId, name, age, email, contactNO});
                        // Add data to the table model
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
