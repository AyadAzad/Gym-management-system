import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class removeMember extends viewMember {

    private JButton removeButton;

    public removeMember() {
        super();
        setTitle("Remove Member Form");

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
        fetchData();
    }

    protected void removeSelectedMembers() {
        int[] selectedRows = membersTable.getSelectedRows();

        if (selectedRows.length > 0) {
            int option = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to remove the selected members?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRowIndex = selectedRows[i];
                    int memberId = (int) membersTableModel.getValueAt(selectedRowIndex, 0);

                    // Remove from the table
                    membersTableModel.removeRow(selectedRowIndex);

                    // Remove from the database
                    removeMemberFromDatabase(memberId);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select members to remove.");
        }
    }

    private void removeMemberFromDatabase(int memberId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management", "root", "Ayad12345");
            String query = "DELETE FROM members WHERE member_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, memberId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
