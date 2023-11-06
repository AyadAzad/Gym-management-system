import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MemberManager {

    private static DefaultTableModel membersTableModel;

    public static JPanel createMembersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.GREEN);
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
        membersTable.setBackground(Color.yellow);

        membersTable.setFont(new Font("ARAIL", Font.BOLD, 16));
        JScrollPane membersScrollPane = new JScrollPane(membersTable);
        membersTable.getTableHeader().setFont(new Font("ARAIL", Font.BOLD, 16));
        // Create buttons for removing, and editing members
        JButton removeMemberButton = new JButton("Remove Member");
        removeMemberButton.setFont(new Font("ARAIL", Font.BOLD, 16));
        JButton editMemberButton = new JButton("Edit Member");
        editMemberButton.setFont(new Font("ARAIL", Font.BOLD, 16));
        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(removeMemberButton);
        buttonPanel.add(editMemberButton);

        // Creating a panel for the member form
        JPanel formPanel = createMemberFormPanel();

        // Adding components to the main panel
        panel.add(membersScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(formPanel, BorderLayout.EAST);


        // Implementing action listeners for removeMemberButton and editMemberButton
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

        // to edit member
        editMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Implementing logic to open the edit member form
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

        formPanel.setPreferredSize(new Dimension(400,300));
//        A purple background
        formPanel.setBackground(new Color(150,128,255));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200,40));
        nameLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        nameField.setFont(new Font("ARAIL", Font.BOLD, 16));



        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200,40));
        ageLabel.setHorizontalAlignment(JLabel.RIGHT);
        ageLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        ageField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField();
        contactField.setPreferredSize(new Dimension(200,40));
        contactLabel.setHorizontalAlignment(JLabel.RIGHT);
        contactLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        contactField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel periodLabel = new JLabel("Period(days):");
        JTextField periodField = new JTextField();
        periodField.setPreferredSize(new Dimension(200,40));
        periodLabel.setHorizontalAlignment(JLabel.RIGHT);
        periodLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        periodField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel coachLabel = new JLabel("Coach:");
        JComboBox<String> coachComboBox = new JComboBox<>(getCoachNames());
        coachLabel.setHorizontalAlignment(JLabel.RIGHT);
        coachLabel.setFont(new Font("ARAIL", Font.BOLD, 16));
        coachComboBox.setFont(new Font("ARAIL", Font.BOLD, 16));

        JButton addButton = new JButton("Add Member");
        addButton.setFont(new Font("ARAIL", Font.BOLD, 16));
        addButton.setBackground(new Color(0,200,20));
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


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String contact = contactField.getText();
                String period = periodField.getText();
                String selectedCoach = (String) coachComboBox.getSelectedItem();

                // to Add member to the table
                addMemberToTable(name, age, contact, period, selectedCoach);

                // to Clear form fields
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
        // coach naems, we'll use DB later on here
        return new String[]{"Ayad", "Ahmed", "Akam"};
    }

}

