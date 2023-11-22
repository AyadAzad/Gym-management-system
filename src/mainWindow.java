import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends JFrame {

    public mainWindow() {
        setTitle("Gym Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel containerPanel = new JPanel(new BorderLayout());
        add(containerPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 20, 20));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBackground(new Color(32, 47, 90)); // Deep Blue

        // Create buttons with text
        JButton addMemberButton = createStyledButton("Add Member", new Color(46, 134, 193)); // Blue
        JButton removeMemberButton = createStyledButton("Remove Member", new Color(242, 121, 63)); // Orange
        JButton viewMembersButton = createStyledButton("View Members", new Color(76, 189, 111)); // Green
        JButton addCoachButton = createStyledButton("Add Coach", new Color(193, 89, 46)); // Red-Orange
        JButton removeCoachButton = createStyledButton("Remove Coach", new Color(121, 63, 242)); // Purple
        JButton viewCoachesButton = createStyledButton("View Coaches", new Color(219, 200, 57)); // Yellow
        JButton paymentButton = createStyledButton("Payment", new Color(78, 121, 193)); // Blue

        // Add buttons to the button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        buttonPanel.add(addMemberButton);
        buttonPanel.add(removeMemberButton);
        buttonPanel.add(viewMembersButton);
        buttonPanel.add(addCoachButton);
        buttonPanel.add(removeCoachButton);
        buttonPanel.add(viewCoachesButton);
        buttonPanel.add(paymentButton);

        // ActionListeners
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new addMember();
            }
        });
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new viewMember();
            }
        });
        removeMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new removeMember();
            }
        });

        // Improved hover effect for all buttons
        styleButton(addMemberButton);
        styleButton(removeMemberButton);
        styleButton(viewMembersButton);
        styleButton(addCoachButton);
        styleButton(removeCoachButton);
        styleButton(viewCoachesButton);
        styleButton(paymentButton);
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 22));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 16));
            }


        });
    }


}
