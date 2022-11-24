import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUIWaitingArea {
    static String frameTitle = "Digital Marketplace";
    static String errorTitle = frameTitle + " - Error";
    JPanel screens;
    JPanel loginScreen;
    JPanel createAccScreen;

    public void loginScreen() {
        loginScreen = new JPanel();
        loginScreen.setLayout(new BoxLayout(loginScreen, BoxLayout.Y_AXIS));
        loginScreen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        JLabel logo;
        try {
            BufferedImage logoImage;
            logoImage = ImageIO.read(new File("Digital Marketplace logo.png"));
            logo = new JLabel(new ImageIcon(logoImage));
            logoPanel.add(logo);
        } catch (Exception e) {
            logoPanel.add(Box.createVerticalStrut(136));
            logo = new JLabel("Could not load image");
            logoPanel.add(logo);
            logoPanel.add(Box.createVerticalStrut(136));
        }
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel tagline = new JLabel("Why is this its own software, not a website?");
        tagline.setFont(new Font("Calibri", Font.PLAIN, 24));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(tagline);
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel loginFieldPanel = new JPanel();
        loginFieldPanel.setPreferredSize(new Dimension(350, 75));
        loginFieldPanel.setMaximumSize(loginFieldPanel.getPreferredSize());
        loginFieldPanel.setLayout(new BoxLayout(loginFieldPanel, BoxLayout.Y_AXIS));
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username/Email address: ");
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        usernamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginFieldPanel.add(usernamePanel);
        loginFieldPanel.add(passwordPanel);
        loginFieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setPreferredSize(new Dimension(300, 75));
        loginButtonPanel.setMaximumSize(loginFieldPanel.getPreferredSize());
        loginButtonPanel.setLayout(new BoxLayout(loginButtonPanel, BoxLayout.Y_AXIS));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton createAccButton = new JButton("Create an account");
        createAccButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButtonPanel.add(loginButton);
        loginButtonPanel.add(Box.createVerticalStrut(20));
        loginButtonPanel.add(createAccButton);
        loginButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel copyleftLabel = new JLabel("Copyleft 2022");
        copyleftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginScreen.add(logoPanel);
        loginScreen.add(Box.createVerticalStrut(50));
        loginScreen.add(loginFieldPanel);
        loginScreen.add(loginButtonPanel);
        loginScreen.add(copyleftLabel);

        createAccButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) (screens.getLayout());
                cardLayout.show(screens, "createAccScreen");
            }
        });
    }

    public void createAccScreen() {
        createAccScreen = new JPanel();
        createAccScreen.setLayout(new BoxLayout(createAccScreen, BoxLayout.Y_AXIS));

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel createTitle = new JLabel("Create an account");
        createTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        createTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username/Email address:");
        JTextField usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        usernamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel usernameReminder = new JLabel("No spaces or underscores");
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel passwordReminder = new JLabel("No spaces or underscores");
        JPanel rePassPanel = new JPanel();
        JLabel rePassLabel = new JLabel("Reenter password:");
        JTextField rePassField = new JPasswordField(15);
        rePassPanel.add(rePassLabel);
        rePassPanel.add(rePassField);
        rePassPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel accTypeLabel = new JLabel("Account type:");
        JPanel accTypePanel = new JPanel();
        ButtonGroup accTypeGroup = new ButtonGroup();
        JRadioButton customerSelect = new JRadioButton("Customer");
        JRadioButton sellerSelect = new JRadioButton("Seller");
        accTypeGroup.add(customerSelect);
        accTypeGroup.add(sellerSelect);
        accTypePanel.add(customerSelect);
        accTypePanel.add(sellerSelect);
        JButton createAccButton = new JButton("Create account");

        createAccScreen.add(Box.createHorizontalStrut(50));
        createAccScreen.add(Box.createVerticalStrut(50));
        createAccScreen.add(backButton);
        createAccScreen.add(Box.createVerticalStrut(10));
        createAccScreen.add(createTitle);
        createAccScreen.add(usernamePanel);
        createAccScreen.add(usernameReminder);
        createAccScreen.add(passwordPanel);
        createAccScreen.add(passwordReminder);
        createAccScreen.add(rePassPanel);
        createAccScreen.add(accTypeLabel);
        createAccScreen.add(accTypePanel);
        createAccScreen.add(createAccButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) (screens.getLayout());
                cardLayout.show(screens, "loginScreen");
            }
        });
    }

    public void putScreensTogether(Container contentPane) {
        screens = new JPanel(new CardLayout());
        loginScreen();
        screens.add(loginScreen, "loginScreen");
        createAccScreen();
        screens.add(createAccScreen, "createAccScreen");
        contentPane.add(screens);
    }
    public static void displayGUI() {
        JFrame frame = new JFrame(frameTitle);
        frame.setSize(960, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Digital Marketplace icon.png").getImage());

        GUIWaitingArea GUI = new GUIWaitingArea();
        GUI.putScreensTogether(frame.getContentPane());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Program's obtainment and setting of look and feel " +
                            "associated with the operating system failed.",
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE);
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayGUI();
            }
        });
    }
}

