import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loginPage extends JFrame{
    private BufferedImage backgroundImage;

    public loginPage(){
        JFrame frame = new JFrame("Gym Management Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        try {
            backgroundImage = ImageIO.read(new File("image/gym_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(10,10,10,10);

//        panel.setBackground(new Color(81,128,131));

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
                    mainWindow main_window = new mainWindow();


                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(panel);
        frame.setVisible(true);

    }


    public void BackgroundImageFrame() {
        try {
            backgroundImage = ImageIO.read(new File("C:/Users/Ayad/OneDrive/Desktop/gym_background.jpeg")); // Change this to your image path
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        });
    }
}
