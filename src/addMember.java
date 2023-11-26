import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addMember extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField contactField;
    private JTextField periodField;
    private JComboBox<String> coachComboBox;

    public addMember() {
        setTitle("Add Member Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel formPanel = createMemberFormPanel();
        getContentPane().add(formPanel);

        // Set the frame visible
        setVisible(true);
    }

    private JPanel createMemberFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10, 10, 10, 10);

        formPanel.setPreferredSize(new Dimension(400, 300));
        formPanel.setBackground(new Color(44, 62, 80)); // Dark Blue

        JLabel[] labels = {
                new JLabel("Name:"),
                new JLabel("Age:"),
                new JLabel("Contact Number:"),
                new JLabel("Period(days):"),
                new JLabel("Coach:")
        };

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        }

        nameField = createFormattedTextField();
        ageField = createFormattedTextField();
        contactField = createFormattedTextField();
        periodField = createFormattedTextField();
        coachComboBox = new JComboBox<>();

        List<String> coachNames = getCoachNames();
        for (String coachName : coachNames) {
            coachComboBox.addItem(coachName);
        }

        coachComboBox.setPreferredSize(new Dimension(250, 40)); // Adjust width and height
        coachComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        coachComboBox.setBackground(new Color(108, 122, 137));
        coachComboBox.setForeground(Color.WHITE);
        coachComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));

        JButton addButton = new JButton("Add Member");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addButton.setBackground(new Color(34, 167, 240));
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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
        addToFormPanel(formPanel, labels[1], ageField, gridBag, 0, 1);

        gridBag.gridy = 2;
        addToFormPanel(formPanel, labels[2], contactField, gridBag, 0, 2);

        gridBag.gridy = 3;
        addToFormPanel(formPanel, labels[3], periodField, gridBag, 0, 3);

        gridBag.gridy = 4;
        addToFormPanel(formPanel, labels[4], coachComboBox, gridBag, 0, 4);

        gridBag.gridy = 5;
        addToFormPanel(formPanel, new JLabel(), addButton, gridBag, 0, 5);

        gridBag.gridy = 6;
        addToFormPanel(formPanel, new JLabel(), returnButton, gridBag, 0, 6);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addMemberToTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return formPanel;
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

    private java.sql.Date calculateEndDate(int period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DAY_OF_MONTH, period);
        return new java.sql.Date(calendar.getTimeInMillis());
    }
    private void addMemberToTable() throws SQLException {
        String name = nameField.getText();
        String age = ageField.getText();
        String contact = contactField.getText();
        String period = periodField.getText();
        String selectedCoach = (String) coachComboBox.getSelectedItem();

        if (name.isEmpty() || age.isEmpty() || contact.isEmpty() || period.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled");
        } else {
            addMemberToTable(name, age, contact, period, selectedCoach);
            JOptionPane.showMessageDialog(null, "member added successfully");
            nameField.setText("");
            ageField.setText("");
            contactField.setText("");
            periodField.setText("");
        }


    }

    private static int getCoachId(String coachName) throws SQLException {
        int coachId = -1; // Default value if member is not found

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
        String selectQuery = "SELECT coach_id FROM coaches WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, coachName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coachId = resultSet.getInt("coach_id");
                }
            }
        }

        return coachId;
    }
    private void addMemberToTable(String name, String age, String contact, String period, String coach) throws SQLException {
        int coachId = getCoachId(coach);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "INSERT INTO members (name, age, contact_number, period, coach_name, coach_id, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Integer.parseInt(age));
                preparedStatement.setString(3, contact);
                preparedStatement.setInt(4, Integer.parseInt(period));
                preparedStatement.setString(5, coach);
                preparedStatement.setInt(6, coachId);

                // Calculate end_date based on current date + period
                java.sql.Date endDate = calculateEndDate(Integer.parseInt(period));
                preparedStatement.setDate(7, endDate);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> getCoachNames() {
        ArrayList<String> coachNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345")) {
            String query = "SELECT name FROM coaches";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String coachName = resultSet.getString("name");
                        coachNames.add(coachName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coachNames;
    }
}
