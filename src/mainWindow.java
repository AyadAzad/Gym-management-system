import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends JFrame{
    public mainWindow() {
        setTitle("Gym Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        // Create a container panel with BorderLayout
        JPanel containerPanel = new JPanel(new BorderLayout());
        add(containerPanel);

        // Create a panel for the buttons with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);

        ImageIcon addMember = new ImageIcon("image/add.png");
        // Create buttons for each section
        JButton addMemberButton = new JButton(addMember);
        JButton removeMemberButton = new JButton("Remove Member");
        JButton viewMembersButton = new JButton("View Members");
        JButton addCoachButton = new JButton("Add Coach");
        JButton removeCoachButton = new JButton("Remove Coach");
        JButton viewCoachesButton = new JButton("View Coaches");
        JButton paymentButton = new JButton("Payment");

        addMemberButton.setBorderPainted(false);
        addMemberButton.setContentAreaFilled(false);

        // Apply common styling
        Font buttonFont = new Font("Arial", Font.PLAIN, 22);
        addMemberButton.setFont(buttonFont);
        removeMemberButton.setFont(buttonFont);
        viewMembersButton.setFont(buttonFont);
        addCoachButton.setFont(buttonFont);
        removeCoachButton.setFont(buttonFont);
        viewCoachesButton.setFont(buttonFont);
        paymentButton.setFont(buttonFont);

        addMemberButton.setBorderPainted(false);
        removeMemberButton.setBorderPainted(false);
        viewMembersButton.setBorderPainted(false);
        addCoachButton.setBorderPainted(false);
        removeCoachButton.setBorderPainted(false);
        viewCoachesButton.setBorderPainted(false);
        paymentButton.setBorderPainted(false);

        addMemberButton.setBackground(Color.GREEN);
        removeMemberButton.setBackground(Color.GREEN);
        viewMembersButton.setBackground(Color.GREEN);
        addCoachButton.setBackground(Color.GREEN);
        removeCoachButton.setBackground(Color.GREEN);
        viewCoachesButton.setBackground(Color.GREEN);
        paymentButton.setBackground(Color.GREEN);





        // Add buttons to the button panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonPanel.setBorder(new EmptyBorder(50,50,50,50));
        buttonPanel.add(addMemberButton, gbc);
        buttonPanel.add(removeMemberButton, gbc);
        buttonPanel.add(viewMembersButton, gbc);
        buttonPanel.add(addCoachButton, gbc);
        buttonPanel.add(removeCoachButton, gbc);
        buttonPanel.add(viewCoachesButton, gbc);
        buttonPanel.add(paymentButton, gbc);


        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember add_member = new addMember();

            }
        });
        addMemberButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMemberButton.setBackground(Color.CYAN); // Change background color on mouse hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMemberButton.setBackground(Color.GREEN); // Change background color when mouse exits
            }
        });
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMember show_members = new viewMember();
            }
        });
    }
}
