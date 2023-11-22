import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class viewMember extends JFrame{

    protected static DefaultTableModel membersTableModel;
    public viewMember() {
        setTitle("View Member Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel formPanel = createMembersPanel();
        getContentPane().add(formPanel);

        // Set the frame visible
        setVisible(true);
        fetchData();
    }

    public static JPanel createMembersPanel() {
        JPanel formpanel = new JPanel(new BorderLayout());
        formpanel.setOpaque(true);
        // Creating a table model for members, later on we'll use Database for this purpose
        membersTableModel = new DefaultTableModel();
        membersTableModel.addColumn("Member ID");
        membersTableModel.addColumn("Name");
        membersTableModel.addColumn("Age");
        membersTableModel.addColumn("Contact Number");
        membersTableModel.addColumn("Period");
        membersTableModel.addColumn("Coach");


        JTable membersTable = new JTable(membersTableModel);
        membersTable.setOpaque(true);
        membersTable.setBackground(Color.black);
        membersTable.setForeground(Color.white);

        membersTable.setFont(new Font("ARAIL", Font.BOLD, 16));
        JScrollPane membersScrollPane = new JScrollPane(membersTable);
        membersTable.getTableHeader().setFont(new Font("ARAIL", Font.BOLD, 16));

        formpanel.add(membersScrollPane, BorderLayout.CENTER);

        return formpanel;

    }
    private static void fetchData() {
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

}
