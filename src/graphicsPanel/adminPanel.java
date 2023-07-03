package graphicsPanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import personPackage.*;

public class adminPanel extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private Font myFont = new Font("Segoe UI", Font.PLAIN, 14);

    public adminPanel() {
        super("ADMIN LOGIN WINDOW");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        usernameLabel = new JLabel("USERNAME:");
        usernameTextField = new JTextField();
        usernameLabel.setBounds(100, 80, 80, 20);
        usernameTextField.setBounds(180, 80, 100, 20);

        passwordLabel = new JLabel("PASSWORD:");
        passwordTextField = new JPasswordField();
        passwordLabel.setBounds(100, 120, 100, 20);
        passwordTextField.setBounds(180, 120, 100, 20);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 240, 100, 20);
        loginButton.addActionListener(this);

        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(loginButton);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = usernameTextField.getText();
        char[] pass = passwordTextField.getPassword();
        String password = String.valueOf(pass);
        ArrayList<Person> temp = new ArrayList<>();
        personFileReadWrite.LoadArrayListPerson("admin");
        temp = personFileReadWrite.getArrayList();
        if (str.isEmpty() || password.isEmpty()) {
            String soundPath = "error.wav";
            try {
                File soundFile = new File(soundPath);
                AudioInputStream ai = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(ai);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Empty Field Entry");
        } else {
            for (Person p : temp) {
                if (str.equals(p.getUsername()) && password.equals(p.getPassword())) {
                    new adminFunctions();
                    dispose();
                    break;
                } else {
                    String soundPath = "error.wav";
                    try {
                        File soundFile = new File(soundPath);
                        AudioInputStream ai = AudioSystem.getAudioInputStream(soundFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(ai);
                        clip.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong Credentials");
                }
            }
        }
    }
}
