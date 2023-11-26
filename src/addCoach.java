import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addCoach extends JFrame {
    private static JLabel coachNameLabel;
    private static JLabel coachAgeLabel;
    private static JLabel coachEmailLabel;
    private static JLabel coachContactNoLabel;
    protected static JTextField coachNameField;
    private static JTextField coachAgeField;
    private static JTextField coachEmailField;
    private static JTextField coachContactNoField;

    public addCoach() {
        setTitle("Add Coach Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(createCoachFormPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private static void addCoachs(String name, String age, String email, String contactNO) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "INSERT INTO coaches (name, age, email, contactNO) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Integer.parseInt(age));
                preparedStatement.setString(3, email);
                preparedStatement.setInt(4, Integer.parseInt(contactNO));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JPanel createCoachFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10, 10, 10, 10);
        formPanel.setBackground(new Color(52, 73, 94));

        coachNameLabel = new JLabel("Name:");
        coachNameField = new JTextField();
        setupFormField(coachNameLabel, coachNameField);

        coachAgeLabel = new JLabel("Age:");
        coachAgeField = new JTextField();
        setupFormField(coachAgeLabel, coachAgeField);

        coachEmailLabel = new JLabel("Email:");
        coachEmailField = new JTextField();
        setupFormField(coachEmailLabel, coachEmailField);

        coachContactNoLabel = new JLabel("Contact No:");
        coachContactNoField = new JTextField();
        setupFormField(coachContactNoLabel, coachContactNoField);

        JButton addButton = new JButton("Add Coach");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(41, 128, 185)); // Blue
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coachName = coachNameField.getText();
                String coachAge = coachAgeField.getText();
                String email = coachEmailField.getText();
                String contactNo = coachContactNoField.getText();
                if (coachName.isEmpty() || coachAge.isEmpty() ||
                        email.isEmpty() || contactNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled");
                } else {
                    addCoachs(coachName, coachAge, email, contactNo);
                    JOptionPane.showMessageDialog(null, "Coach added successfully!");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(192, 57, 43)); // Dark red
        backButton.setForeground(Color.white);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to navigate back to the main window or dispose of this window
                dispose(); // This will close the current window
                new mainWindow();
            }
        });

        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(coachNameLabel, gridBag);

        gridBag.gridy = 1;
        formPanel.add(coachAgeLabel, gridBag);

        gridBag.gridy = 2;
        formPanel.add(coachEmailLabel, gridBag);

        gridBag.gridy = 3;
        formPanel.add(coachContactNoLabel, gridBag);

        gridBag.gridx = 1;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;
        formPanel.add(coachNameField, gridBag);

        gridBag.gridy = 1;
        formPanel.add(coachAgeField, gridBag);

        gridBag.gridy = 2;
        formPanel.add(coachEmailField, gridBag);

        gridBag.gridy = 3;
        formPanel.add(coachContactNoField, gridBag);

        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        formPanel.add(addButton, gridBag);

        gridBag.gridx = 2;
        formPanel.add(backButton, gridBag);

        return formPanel;
    }

    private static void setupFormField(JLabel label, JTextField field) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setPreferredSize(new Dimension(200, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }
}
