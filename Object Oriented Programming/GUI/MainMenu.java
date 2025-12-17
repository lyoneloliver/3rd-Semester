import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton btnLogin;
    private JButton btnImage;

    public MainMenu() {
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnLogin = new JButton("Open Login");
        btnImage = new JButton("Open Image Viewer");

        setLayout(new GridLayout(2, 1));
        add(btnLogin);
        add(btnImage);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
            }
        });

        btnImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ImageViewer().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
