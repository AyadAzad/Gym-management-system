import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class addMember extends JFrame{
    public static JTextField nameField;

    public addMember() {
        setTitle("Add Member Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel formPanel = createMemberFormPanel();
        getContentPane().add(formPanel);

        // Set the frame visible
        setVisible(true);
    }
    public static JPanel createMemberFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10, 10, 10, 10);

        formPanel.setPreferredSize(new Dimension(400, 300));

        formPanel.setBackground(new Color(0, 0, 0));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 40));
        nameLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        nameField.setFont(new Font("ARAIL", Font.BOLD, 16));


        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(Color.WHITE);
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200, 40));
        ageLabel.setHorizontalAlignment(JLabel.RIGHT);
        ageLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        ageField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setForeground(Color.WHITE);
        JTextField contactField = new JTextField();
        contactField.setPreferredSize(new Dimension(200, 40));
        contactLabel.setHorizontalAlignment(JLabel.RIGHT);
        contactLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        contactField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel periodLabel = new JLabel("Period(days):");
        periodLabel.setForeground(Color.WHITE);
        JTextField periodField = new JTextField();
        periodField.setPreferredSize(new Dimension(200, 40));
        periodLabel.setHorizontalAlignment(JLabel.RIGHT);
        periodLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        periodField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel coachLabel = new JLabel("Coach:");
        coachLabel.setForeground(Color.WHITE);
        JComboBox<String> coachComboBox = new JComboBox<String>();
        List<String> coachNames = getCoachNames();
        for (String coachName: coachNames){
            coachComboBox.addItem(coachName);
        }
        coachLabel.setHorizontalAlignment(JLabel.RIGHT);
        coachLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        coachComboBox.setFont(new Font("ARAIL", Font.BOLD, 16));

        JButton addButton = new JButton("Add Member");
        addButton.setFont(new Font("ARAIL", Font.BOLD, 16));
        addButton.setBackground(new Color(0, 200, 20));
        addButton.setBorder(new LineBorder(Color.BLACK, 2, true));

        // grid style for the fields
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        formPanel.add(nameLabel, gridBag);

        gridBag.gridy = 1;
        formPanel.add(ageLabel, gridBag);

        gridBag.gridy = 2;
        formPanel.add(contactLabel, gridBag);

        gridBag.gridy = 3;
        formPanel.add(periodLabel, gridBag);

        gridBag.gridy = 4;
        formPanel.add(coachLabel, gridBag);

        gridBag.gridx = 1;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2; // Span two columns
        formPanel.add(nameField, gridBag);

        gridBag.gridy = 1;
        formPanel.add(ageField, gridBag);

        gridBag.gridy = 2;
        formPanel.add(contactField, gridBag);

        gridBag.gridy = 3;
        formPanel.add(periodField, gridBag);

        gridBag.gridy = 4;
        formPanel.add(coachComboBox, gridBag);

        gridBag.gridy = 5;
        gridBag.gridwidth = 3; // Span three columns
        formPanel.add(addButton, gridBag);
        // to fetch data


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String contact = contactField.getText();
                String period = periodField.getText();
                String selectedCoach = (String) coachComboBox.getSelectedItem();

                // to Add member to the table
                if (name.isEmpty() | age.isEmpty() | contact.isEmpty() | period.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "all fields must be filled");
                } else {
                    addMemberToTable(name, age, contact, period, selectedCoach);


                }

            }
        });

        return formPanel;
    }

    private static void addMemberToTable(String name, String age, String contact, String period, String coach) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "INSERT INTO members (name, age, contact_number, period, coach) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Integer.parseInt(age));
                preparedStatement.setString(3, contact);
                preparedStatement.setInt(4, Integer.parseInt(period));
                preparedStatement.setString(5, coach);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static List<String> getCoachNames() {
        // coach naems, we'll use DB later on here
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
