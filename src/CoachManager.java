//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class CoachManager {
//    private static JLabel coach_nameLabel;
//    private static JLabel coach_ageLabel;
//    private static JLabel coach_emailLabel;
//    private static JLabel coach_contactNoLabel;
//    protected static JTextField coach_nameField;
//    private static JTextField coach_ageField;
//    private static JTextField coach_emailField;
//    private static JTextField coach_contactNoField;
//
//    private static DefaultTableModel coachesTableModel;
//    private static JTable coachesTable;
//
//    public static JPanel createCoachesPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//
//        //table model for coaches
//        coachesTableModel = new DefaultTableModel();
//        coachesTableModel.addColumn("Coach ID");
//        coachesTableModel.addColumn("Name");
//        coachesTableModel.addColumn("Age");
//        coachesTableModel.addColumn("Email");
//        coachesTableModel.addColumn("Contact NO");
//
//        coachesTable = new JTable(coachesTableModel);
//        JScrollPane coachesScrollPane = new JScrollPane(coachesTable);
//
//        // buttons for  removing, and editing coaches
//        JButton removeCoachButton = new JButton("Remove Coach");
//        JButton editCoachButton = new JButton("Edit Coach");
//        // design
//        coachesTable.getTableHeader().setFont(new Font("ARAIL", Font.BOLD, 16));
//        removeCoachButton.setFont(new Font("ARAIL", Font.BOLD, 16));
//        editCoachButton.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coachesTable.setOpaque(true);
//        coachesTable.setFont(new Font("ARAIL", Font.BOLD, 16));
//        // Add buttons to a panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(removeCoachButton);
//        buttonPanel.add(editCoachButton);
//
//        // Create a panel for the coach form
//        JPanel formPanel = createCoachFormPanel();
//
//        // Add components to the main panel
//        panel.add(coachesScrollPane, BorderLayout.CENTER);
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//        panel.add(formPanel, BorderLayout.EAST);
//
//
//        removeCoachButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = coachesTable.getSelectedRow();
//                if (selectedRow >= 0) {
//                    coachesTableModel.removeRow(selectedRow);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Select a coach to remove.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//            // to edit coaches
//        editCoachButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = coachesTable.getSelectedRow();
//                if (selectedRow >= 0) {
//                    String currentName = (String) coachesTableModel.getValueAt(selectedRow, 1);
//                    editCoach(selectedRow, currentName);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Select a coach to edit.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//
//        return panel;
//    }
//    private static void fetchCoachesData() {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root","Ayad12345");
//            String query = "SELECT * FROM coaches";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int memberId = resultSet.getInt("coach_id");
//                        String name = resultSet.getString("name");
//                        int age = resultSet.getInt("age");
//                        String email = resultSet.getString("email");
//                        int contactNO = resultSet.getInt("contactNO");
//                        coachesTableModel.addRow(new Object[]{memberId,name, age, email, contactNO});
//                        // Add data to the table model
//
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    // to add data
//    private static void addCoach(String name, String age, String email, String contactNO) {
//        // Add coach to the table model
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root","Ayad12345");
//            String query = "INSERT INTO coaches (name, age, email, contactNO) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, name);
//                preparedStatement.setInt(2, Integer.parseInt(age));
//                preparedStatement.setString(3, email);
//                preparedStatement.setInt(4, Integer.parseInt(contactNO));
//                preparedStatement.executeUpdate();
//
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private static JPanel createCoachFormPanel() {
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gridBag = new GridBagConstraints();
//        gridBag.fill = GridBagConstraints.HORIZONTAL;
//        gridBag.insets = new Insets(10,10,10,10);
//
//        coach_nameLabel = new JLabel("Name:");
//        coach_nameField = new JTextField();
//        coach_nameLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_nameField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_nameField.setPreferredSize(new Dimension(200,40));
//
//        coach_ageLabel = new JLabel("Age:");
//        coach_ageLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_ageField = new JTextField();
//        coach_ageField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_ageField.setPreferredSize(new Dimension(200,40));
//
//        coach_emailLabel = new JLabel("Email:");
//        coach_emailLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_emailField = new JTextField();
//        coach_emailField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_emailField.setPreferredSize(new Dimension(200,40));
//
//        coach_contactNoLabel = new JLabel("ContactNO:");
//        coach_contactNoLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_contactNoField = new JTextField();
//        coach_contactNoField.setPreferredSize(new Dimension(200,40));
//        coach_contactNoField.setFont(new Font("ARAIL", Font.BOLD, 16));
//
//
//        JButton addButton = new JButton("Add Coach");
//        addButton.setFont(new Font("ARAIL", Font.BOLD, 16));
//
//
//
//        gridBag.gridx = 0;
//        gridBag.gridy = 0;
//        formPanel.add(coach_nameLabel, gridBag);
//
//        gridBag.gridy = 1;
//        formPanel.add(coach_ageLabel, gridBag);
//
//        gridBag.gridy = 2;
//        formPanel.add(coach_emailLabel, gridBag);
//
//        gridBag.gridy = 3;
//        formPanel.add(coach_contactNoLabel, gridBag);
//
//
//        gridBag.gridx = 1;
//        gridBag.gridy = 0;
//        gridBag.gridwidth = 2; // Span two columns
//        formPanel.add(coach_nameField, gridBag);
//
//        gridBag.gridy = 1;
//        formPanel.add(coach_ageField, gridBag);
//
//        gridBag.gridy = 2;
//        formPanel.add(coach_emailField, gridBag);
//
//        gridBag.gridy = 3;
//        formPanel.add(coach_contactNoField, gridBag);
//
//        gridBag.gridy = 4;
//        gridBag.gridwidth = 3; // Span three columns
//        formPanel.add(addButton, gridBag);
//
//        fetchCoachesData();
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String coach_name = coach_nameField.getText();
//                String coach_age = coach_ageField.getText();
//                String email = coach_emailField.getText();
//                String contactNo = coach_contactNoField.getText();
//               if (coach_name.isEmpty() | coach_age.isEmpty() |
//               email.isEmpty() | contactNo.isEmpty()){
//                   JOptionPane.showMessageDialog(null, "all fields must be filled");
//               }
//               else{
//                   addCoach(coach_name, coach_age, email, contactNo);
//
//               }
//            }
//        });
//        return formPanel;
//    }
//
//
//
//
//
//
//    private static void editCoach(int rowIndex, String currentName) {
//        //JFrame for editing coaches
//        JFrame editCoachFrame = new JFrame("Edit Coach");
//        editCoachFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        editCoachFrame.setSize(600, 600);
//
//
//        //text fields and labels for coach details
//
//        JButton saveButton = new JButton("Save Changes");
//
//        //panel to hold the form components
//        JPanel edit_coach_formPanel = new JPanel();
//        edit_coach_formPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gridBag = new GridBagConstraints();
//        gridBag.fill = GridBagConstraints.HORIZONTAL;
//        gridBag.insets = new Insets(10,10,10,10);
//
//        coach_nameLabel = new JLabel("Name:");
//        coach_nameField = new JTextField();
//        coach_nameLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_nameField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_nameField.setPreferredSize(new Dimension(200,40));
//
//        coach_ageLabel = new JLabel("Age:");
//        coach_ageLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_ageField = new JTextField();
//        coach_ageField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_ageField.setPreferredSize(new Dimension(200,40));
//
//        coach_emailLabel = new JLabel("Email:");
//        coach_emailLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_emailField = new JTextField();
//        coach_emailField.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_emailField.setPreferredSize(new Dimension(200,40));
//
//        coach_contactNoLabel = new JLabel("ContactNO:");
//        coach_contactNoLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
//        coach_contactNoField = new JTextField();
//        coach_contactNoField.setPreferredSize(new Dimension(200,40));
//        coach_contactNoField.setFont(new Font("ARAIL", Font.BOLD, 16));
//
//
//        JButton addButton = new JButton("Add Coach");
//        addButton.setFont(new Font("ARAIL", Font.BOLD, 16));
//
//
//
//        gridBag.gridx = 0;
//        gridBag.gridy = 0;
//        edit_coach_formPanel.add(coach_nameLabel, gridBag);
//
//        gridBag.gridy = 1;
//        edit_coach_formPanel.add(coach_ageLabel, gridBag);
//
//        gridBag.gridy = 2;
//        edit_coach_formPanel.add(coach_emailLabel, gridBag);
//
//        gridBag.gridy = 3;
//        edit_coach_formPanel.add(coach_contactNoLabel, gridBag);
//
//
//        gridBag.gridx = 1;
//        gridBag.gridy = 0;
//        gridBag.gridwidth = 2; // Span two columns
//        edit_coach_formPanel.add(coach_nameField, gridBag);
//
//        gridBag.gridy = 1;
//        edit_coach_formPanel.add(coach_ageField, gridBag);
//
//        gridBag.gridy = 2;
//        edit_coach_formPanel.add(coach_emailField, gridBag);
//
//        gridBag.gridy = 3;
//        edit_coach_formPanel.add(coach_contactNoField, gridBag);
//
//        gridBag.gridy = 4;
//        gridBag.gridwidth = 3; // Span three columns
//        edit_coach_formPanel.add(saveButton, gridBag);
//
//        // an action listener for the "Save Changes" button
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Get the updated coach details from the input field
//                 String updatedName = coach_nameField.getText();
//                 String updatedAge = coach_ageField.getText();
//                 String updatedEmail = coach_emailField.getText();
//                 String updatedContactNo = coach_contactNoField.getText();
//
//                // Update the coach's details in the table model
//                coachesTableModel.setValueAt(updatedName, rowIndex, 1);
//                coachesTableModel.setValueAt(updatedAge, rowIndex, 2);
//                coachesTableModel.setValueAt(updatedEmail, rowIndex, 3);
//                coachesTableModel.setValueAt(updatedContactNo, rowIndex, 4);
//
//                // Close the edit coach window
//                editCoachFrame.dispose();
//            }
//        });
//
//        editCoachFrame.add(edit_coach_formPanel);
//        editCoachFrame.setVisible(true);
//    }
//
//    // A mock method to generate a new coach ID
//
//    private static int getNewCoachID() {
//        // Implement logic to generate a new unique coach ID
//        return coachesTableModel.getRowCount() + 1;
//    }
//
//    public static class viewMember extends JFrame {
//
//        protected static DefaultTableModel membersTableModel;
//
//        public viewMember() {
//            setTitle("View Member Form");
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setSize(800, 600);
//            setLocationRelativeTo(null); // Center the frame on the screen
//
//            try {
//                UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
//            } catch (UnsupportedLookAndFeelException e) {
//                e.printStackTrace();
//            }
//
//            JPanel formPanel = createMembersPanel();
//            getContentPane().add(formPanel);
//
//            // Set the frame visible
//            setVisible(true);
//            fetchData();
//        }
//
//        public static JPanel createMembersPanel() {
//            JPanel formPanel = new JPanel(new BorderLayout());
//            formPanel.setOpaque(true);
//
//            // Creating a table model for members, later on we'll use Database for this purpose
//            membersTableModel = new DefaultTableModel();
//            membersTableModel.addColumn("Member ID");
//            membersTableModel.addColumn("Name");
//            membersTableModel.addColumn("Age");
//            membersTableModel.addColumn("Contact Number");
//            membersTableModel.addColumn("Period");
//            membersTableModel.addColumn("Coach");
//
//            JTable membersTable = new JTable(membersTableModel);
//            membersTable.setOpaque(true);
//            membersTable.setBackground(Color.BLACK);
//            membersTable.setForeground(Color.WHITE);
//
//            membersTable.setFont(new Font("Arial", Font.PLAIN, 14));
//            membersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
//            membersTable.getTableHeader().setBackground(new Color(34, 167, 240));
//            membersTable.getTableHeader().setForeground(Color.WHITE);
//
//            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//            membersTable.setDefaultRenderer(Object.class, centerRenderer);
//
//            JScrollPane membersScrollPane = new JScrollPane(membersTable);
//            membersScrollPane.getViewport().setBackground(Color.BLACK);
//
//            formPanel.add(membersScrollPane, BorderLayout.CENTER);
//
//            return formPanel;
//        }
//
//        private static void fetchData() {
//            try {
//                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
//                String query = "SELECT * FROM members";
//                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                        while (resultSet.next()) {
//                            int memberId = resultSet.getInt("member_id");
//                            String name = resultSet.getString("name");
//                            int age = resultSet.getInt("age");
//                            String contact = resultSet.getString("contact_number");
//                            int period = resultSet.getInt("period");
//                            String coach = resultSet.getString("coach");
//                            membersTableModel.addRow(new Object[]{memberId, name, age, contact, period, coach});
//                            // Add data to the table model
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
