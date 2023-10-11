import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gym Management Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10,10,10,10);

        panel.setBackground(new Color(81,128,131));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(255,255,255));
        usernameLabel.setFont(new Font("ARAIL", Font.BOLD, 16));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(255,255,255));
        passwordLabel.setFont(new Font("ARAIL", Font.BOLD, 16));

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200,40));
        usernameField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200,40));
        passwordField.setFont(new Font("ARAIL", Font.BOLD, 16));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("ARAIL", Font.BOLD, 16));
        loginButton.setBackground(new Color(0,190,0));



        gridBag.gridx=0;
        gridBag.gridy=0;
        panel.add(usernameLabel, gridBag);


        gridBag.gridy=1;
        panel.add(passwordLabel, gridBag);

        gridBag.gridx=1;
        gridBag.gridy=0;
        panel.add(usernameField, gridBag);

        gridBag.gridx=1;
        gridBag.gridy=1;
        panel.add(passwordField, gridBag);

        gridBag.gridy=3;
        gridBag.gridwidth=2;
        panel.add(loginButton, gridBag);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check the username and password (you can add your authentication logic here)
                if (username.equals("admin") && password.equals("123")) {
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
