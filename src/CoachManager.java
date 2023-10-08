import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoachManager {

    private static DefaultTableModel coachesTableModel;
    private static JTable coachesTable;

    public static JPanel createCoachesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a table model for coaches
        coachesTableModel = new DefaultTableModel();
        coachesTableModel.addColumn("Coach ID");
        coachesTableModel.addColumn("Name");

        coachesTable = new JTable(coachesTableModel);
        JScrollPane coachesScrollPane = new JScrollPane(coachesTable);

        // Create buttons for adding, removing, and editing coaches
        JButton addCoachButton = new JButton("Add Coach");
        JButton removeCoachButton = new JButton("Remove Coach");
        JButton editCoachButton = new JButton("Edit Coach");

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCoachButton);
        buttonPanel.add(removeCoachButton);
        buttonPanel.add(editCoachButton);

        // Create a panel for the coach form
        JPanel formPanel = createCoachFormPanel();

        // Add components to the main panel
        panel.add(coachesScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(formPanel, BorderLayout.EAST);

        // Add an action listener for the addCoachButton to open the coach form
        addCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddCoachForm();
            }
        });

        // Implement action listeners for removeCoachButton and editCoachButton
        removeCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = coachesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    coachesTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a coach to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = coachesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String currentName = (String) coachesTableModel.getValueAt(selectedRow, 1);
                    editCoach(selectedRow, currentName);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a coach to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private static JPanel createCoachFormPanel() {
        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200,40));
        JButton addButton = new JButton("Add Coach");


        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(new JLabel()); // Empty space
        formPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCoach(nameField.getText());
                nameField.setText(""); // Clear the name field after adding
            }
        });

        return formPanel;
    }

    private static void addCoach(String name) {
        // Add coach to the table model
        coachesTableModel.addRow(new Object[]{getNewCoachID(), name});
    }

    private static void editCoach(int rowIndex, String currentName) {
        // Create a JFrame for editing coaches
        JFrame editCoachFrame = new JFrame("Edit Coach");
        editCoachFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editCoachFrame.setSize(400, 200);

        // Create text fields and labels for coach details
        JTextField nameField = new JTextField(currentName, 20);
        JButton saveButton = new JButton("Save Changes");

        // Create a panel to hold the form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel()); // Empty space
        formPanel.add(saveButton);

        // Add an action listener for the "Save Changes" button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the updated coach details from the input field
                String updatedName = nameField.getText();

                // Update the coach's details in the table model
                coachesTableModel.setValueAt(updatedName, rowIndex, 1);

                // Close the edit coach window
                editCoachFrame.dispose();
            }
        });

        editCoachFrame.add(formPanel);
        editCoachFrame.setVisible(true);
    }

    // A mock method to generate a new coach ID (replace with your actual logic)
    private static int getNewCoachID() {
        // Implement logic to generate a new unique coach ID
        return coachesTableModel.getRowCount() + 1;
    }

    private static void openAddCoachForm() {
        // Implement logic to open the add coach form here (if needed)
    }
}
