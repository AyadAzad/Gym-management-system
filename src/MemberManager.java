import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberManager {

    private static DefaultTableModel membersTableModel;

    public static JPanel createMembersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a table model for members
        membersTableModel = new DefaultTableModel();
        membersTableModel.addColumn("Member ID");
        membersTableModel.addColumn("Name");
        membersTableModel.addColumn("Age");
        membersTableModel.addColumn("Contact Number");
        membersTableModel.addColumn("Period");
        membersTableModel.addColumn("Coach");

        JTable membersTable = new JTable(membersTableModel);
        JScrollPane membersScrollPane = new JScrollPane(membersTable);

        // Create buttons for adding, removing, and editing members
        JButton addMemberButton = new JButton("Add Member");
        JButton removeMemberButton = new JButton("Remove Member");
        JButton editMemberButton = new JButton("Edit Member");

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addMemberButton);
        buttonPanel.add(removeMemberButton);
        buttonPanel.add(editMemberButton);

        // Create a panel for the member form
        JPanel formPanel = createMemberFormPanel();

        // Add components to the main panel
        panel.add(membersScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(formPanel, BorderLayout.EAST);

        // Add an action listener for the addMemberButton to open the member form
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddMemberForm();
            }
        });

        // Implement action listeners for removeMemberButton and editMemberButton
        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    membersTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a member to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Implement logic to open the edit member form
                    // You can pass the selected member's details to the edit form
                } else {
                    JOptionPane.showMessageDialog(null, "Select a member to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private static JPanel createMemberFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10,10,10,10);

        formPanel.setPreferredSize(new Dimension(500,300));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200,40));



        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200,40));
        ageLabel.setHorizontalAlignment(JLabel.RIGHT);

        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField();
        contactField.setPreferredSize(new Dimension(200,40));
        contactLabel.setHorizontalAlignment(JLabel.RIGHT);

        JLabel periodLabel = new JLabel("Period:");
        JTextField periodField = new JTextField();
        periodField.setPreferredSize(new Dimension(200,40));
        periodLabel.setHorizontalAlignment(JLabel.RIGHT);

        JLabel coachLabel = new JLabel("Coach:");
        JComboBox<String> coachComboBox = new JComboBox<>(getCoachNames());
        coachLabel.setHorizontalAlignment(JLabel.RIGHT);

        JButton addButton = new JButton("Add Member");



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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String contact = contactField.getText();
                String period = periodField.getText();
                String selectedCoach = (String) coachComboBox.getSelectedItem();

                // Add member to the table
                addMemberToTable(name, age, contact, period, selectedCoach);

                // Clear form fields
                clearMemberFormFields(nameField, ageField, contactField, periodField);
            }
        });

        return formPanel;
    }

    private static void addMemberToTable(String name, String age, String contact, String period, String coach) {
        int memberId = membersTableModel.getRowCount() + 1;
        membersTableModel.addRow(new Object[]{memberId, name, age, contact, period, coach});
    }

    private static void clearMemberFormFields(JTextField nameField, JTextField ageField, JTextField contactField, JTextField periodField) {
        nameField.setText("");
        ageField.setText("");
        contactField.setText("");
        periodField.setText("");
    }

    private static String[] getCoachNames() {
        // Mocked coach names; replace with your actual data source
        return new String[]{"Coach 1", "Coach 2", "Coach 3"};
    }

    private static void openAddMemberForm() {
        // Implement logic to open the add member form here (if needed)
    }
}
