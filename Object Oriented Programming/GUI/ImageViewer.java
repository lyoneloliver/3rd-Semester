import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageViewer extends JFrame {
    private JLabel labelImage;
    private JButton btnOpenImage;

    public ImageViewer() {
        setTitle("Image Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        labelImage = new JLabel();
        labelImage.setHorizontalAlignment(JLabel.CENTER);
        labelImage.setVerticalAlignment(JLabel.CENTER);

        btnOpenImage = new JButton("Open Image");
        btnOpenImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                selectAndDisplayImage();
            }
        });

        add(btnOpenImage, BorderLayout.NORTH);
        add(new JScrollPane(labelImage), BorderLayout.CENTER);
    }

    private void selectAndDisplayImage() {
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));
        int userChoice = imageChooser.showOpenDialog(this);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File chosenFile = imageChooser.getSelectedFile();
            ImageIcon imageToDisplay = new ImageIcon(chosenFile.getAbsolutePath());
            labelImage.setIcon(imageToDisplay);
        }
    }
}
