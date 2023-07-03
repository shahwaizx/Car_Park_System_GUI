package graphicsPanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import personPackage.*;

public class userPanel extends JFrame implements ActionListener {

    private JLabel usernameLabel, signUpLabel, picLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JButton signInButton;
    private JButton signUpButton;
    private Font myFont = new Font("Segoe UI", Font.PLAIN, 14);
    private User signedUser;
    private ImageIcon user = new ImageIcon("user.png");


    public User getSignedUser() {
        return signedUser;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public userPanel() {
        super("USER LOGIN WINDOW");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        usernameLabel = new JLabel("USERNAME:");
        usernameTextField = new JTextField();
        usernameLabel.setBounds(100, 35, 80, 20);
        usernameTextField.setBounds(180, 35, 100, 20);

        passwordLabel = new JLabel("PASSWORD:");
        passwordTextField = new JPasswordField();
        passwordLabel.setBounds(100, 75, 100, 20);
        passwordTextField.setBounds(180, 75, 100, 20);
        signInButton = new JButton("SIGN IN");

        signInButton.setBounds(160, 115, 100, 20);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signUpButton = new JButton("SIGN UP");

        signUpButton.addActionListener(this);
        signUpButton.setBounds(220, 155, 100, 20);
        signUpButton.setFocusable(false);
        signUpLabel = new JLabel("Don't have an account?");
        signUpLabel.setBounds(80, 155, 150, 20);

        picLabel = new JLabel(user);
        picLabel.setBounds(10, 20, 72, 72);


        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(signInButton);
        add(signUpButton);
        add(signUpLabel);
        add(picLabel);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = usernameTextField.getText();
        char[] pass = passwordTextField.getPassword();
        String password = String.copyValueOf(pass);
        if (e.getSource() == signInButton) {
            if (str.isEmpty() || password.isEmpty()) {
                String soundPath = "error.wav";
                try {
                    File soundFile = new File(soundPath);
                    AudioInputStream ai = AudioSystem.getAudioInputStream(soundFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(ai);
                    clip.start();
                }
                catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(new JFrame(), "Empty field entry");
            } else {
                boolean check = false;
                ArrayList<Person> temp = new ArrayList<>();
                personFileReadWrite.LoadArrayListPerson("user");
                temp = personFileReadWrite.getArrayList();
                if (temp == null) {
                    temp = new ArrayList<>();
                }
                for (Person p : temp) {
                    if (str.equals(p.getUsername()) && password.equals(p.getPassword())) {

                        this.setSignedUser((User) p);
                        this.writeToFile(getSignedUser());

                        check = true;
                        break;
                    }
                }
                if (check) {

                    new carParkPanel();
                    dispose();
                } else {
                    String soundPath = "error.wav";
                    try {
                        File soundFile = new File(soundPath);
                        AudioInputStream ai = AudioSystem.getAudioInputStream(soundFile);
                        Clip clip = AudioSystem.getClip();
                        clip.open(ai);
                        clip.start();
                    }
                    catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong Credentials");
                }

            }
        }
        if (e.getSource() == signUpButton) {
            new signUp();
        }
    }

    public void writeToFile(User user) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("SignedUser.ser"));
            objectOutputStream.writeObject(user);

        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println("Signed user File error");
        }
    }

}


