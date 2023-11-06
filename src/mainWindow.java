import javax.swing.*;
import java.awt.*;

public class mainWindow {
    public mainWindow() {
        JFrame mainFrame = new JFrame("Gym Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        // Create a JTabbedPane to switch between different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for different functionalities (members, coaches, payment)
        JPanel membersPanel = MemberManager.createMembersPanel();
        JPanel coachesPanel = CoachManager.createCoachesPanel();
        JPanel paymentPanel = paymentManager.createPaymentPanel();

        // Add panels to the tabbed pane
        tabbedPane.addTab("Members", membersPanel);
        tabbedPane.addTab("Coaches", coachesPanel);
        tabbedPane.addTab("Payment", paymentPanel);
        tabbedPane.setOpaque(true);
        tabbedPane.setFont(new Font("ARAIL", Font.BOLD, 16));

        mainFrame.add(tabbedPane);
        mainFrame.setVisible(true);
    }
}
