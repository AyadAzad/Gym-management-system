import javax.swing.*;

public class mainWindow {
    public mainWindow() {
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
