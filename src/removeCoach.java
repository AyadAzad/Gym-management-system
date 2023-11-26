import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class removeCoach extends viewCoach{

    private JButton removeButton;

    public removeCoach() {
        super();
        setTitle("Remove Coach Form");

        // Create and add the remove button
        removeButton = new JButton("Remove Selected Members");
        removeButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        removeButton.setBackground(new Color(255, 0, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedMembers();
            }
        });

        JPanel formPanel = (JPanel) getContentPane().getComponent(0); // Get the first component, which is the formPanel
        formPanel.add(removeButton, BorderLayout.SOUTH);

        getContentPane().add(formPanel);
    }

    protected void removeSelectedMembers() {
        int[] selectedRows = coachesTable.getSelectedRows();

        if (selectedRows.length > 0) {
            int option = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to remove the selected coaches?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRowIndex = selectedRows[i];
                    int coachId = (int) coachesTableModel.getValueAt(selectedRowIndex, 0);

                    // Remove from the table
                    coachesTableModel.removeRow(selectedRowIndex);

                    // Remove from the database
                    removeMemberFromDatabase(coachId);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select coaches to remove.");
        }
    }

    private void removeMemberFromDatabase(int coachId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "DELETE FROM coaches WHERE coach_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, coachId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Coach removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to remove coach. Please try again.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
