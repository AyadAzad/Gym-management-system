import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gym Management Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty space
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check the username and password (you can add your authentication logic here)
                if (username.equals("admin") && password.equals("password")) {
                    // Successful login, open the main application window
                    frame.dispose(); // Close the login window
                    openMainWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void openMainWindow() {
        JFrame mainFrame = new JFrame("Gym Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);

        // Create a JTabbedPane to switch between different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for different functionalities (members, coaches, payment)
        JPanel membersPanel = MemberManager.createMembersPanel(); // Call static method
        JPanel coachesPanel = CoachManager.createCoachesPanel(); // Call static method
        JPanel paymentPanel = paymentManager.createPaymentPanel(); // Call static method

        // Add panels to the tabbed pane
        tabbedPane.addTab("Members", membersPanel);
        tabbedPane.addTab("Coaches", coachesPanel);
        tabbedPane.addTab("Payment", paymentPanel);

        mainFrame.add(tabbedPane);
        mainFrame.setVisible(true);
    }
}
