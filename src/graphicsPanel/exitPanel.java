package graphicsPanel;

import Vehicle.*;
import personPackage.*;
import Levels.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.*;

public class exitPanel extends JFrame implements ActionListener {
    levelWise L1 = new levelWise(15);
    levelWise L2 = new levelWise(10);
    levelWise L3 = new levelWise(5);
    private JLabel usernameLabel, passwordLabel, levelLabel, slotLabel;
    private JTextField usernameTextfield, levelTextfield, slotTextfield;
    private JPasswordField passwordTextField;
    private JButton exitCarButton;

    public exitPanel() {
        super("EXIT PANEL");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        usernameLabel = new JLabel("USERNAME:");
        usernameTextfield = new JTextField();
        usernameLabel.setBounds(100, 80, 80, 20);
        usernameTextfield.setBounds(180, 80, 100, 20);

        passwordLabel = new JLabel("PASSWORD:");
        passwordTextField = new JPasswordField();
        passwordLabel.setBounds(100, 120, 100, 20);
        passwordTextField.setBounds(180, 120, 100, 20);

        levelLabel = new JLabel("LEVEL:");
        levelLabel.setBounds(100, 160, 100, 20);
        levelTextfield = new JTextField();
        levelTextfield.setBounds(180, 160, 100, 20);

        slotLabel = new JLabel("SLOT#:");
        slotLabel.setBounds(100, 200, 100, 20);
        slotTextfield = new JTextField();
        slotTextfield.setBounds(180, 200, 100, 20);


        exitCarButton = new JButton("EXIT CAR");
        exitCarButton.setBounds(150, 250, 100, 20);
        exitCarButton.addActionListener(this);
        exitCarButton.setFocusable(false);

        add(usernameLabel);
        add(usernameTextfield);
        add(passwordLabel);
        add(passwordTextField);
        add(levelLabel);
        add(levelTextfield);
        add(slotLabel);
        add(slotTextfield);
        add(exitCarButton);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String str = usernameTextfield.getText();
        char[] pass = passwordTextField.getPassword();
        String password = String.valueOf(pass);
        String level = levelTextfield.getText();

        if (e.getSource() == exitCarButton) {
            if (str.equals(null) || password.equals(null) || level.equals(null) || slotTextfield.getText().isEmpty()) {
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
                JOptionPane.showMessageDialog(new JFrame(), "Text Fields are empty");
            } else {
                if (level.equals("1")) {
                    L1.slots = LevelFileReadWrite.LoadArray(L1.slots, "level1");
                    if (L1.slots == null) {
                        L1.slots = new User[L1.getCapacity()];
                    }
                    int index = -1;
                    Integer slotno = Integer.parseInt(slotTextfield.getText());
                    for (int i = 0; i < L1.getCapacity(); i++) {
                        if (L1.slots[i] != null && L1.slots[i].getUsername().equals(str) && L1.slots[i].getPassword().equals(password) && L1.slots[i].SLOTNO() == slotno) {
                            JOptionPane.showMessageDialog(new JFrame(), "Vehicle Found");
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
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
                        JOptionPane.showMessageDialog(new JFrame(), "NO Vehicle Found");
                    } else {
                        if (slotno > -1 && slotno < 15) {
                            Vehicle showVehicle = L1.slots[index].returnVehicleObject();
                            String n = L1.slots[index].getName();
                            String number = showVehicle.getNumberPlate();
                            int slotNumber = L1.slots[index].SLOTNO();
                            double totalTime = (System.currentTimeMillis() / 1000.0) - showVehicle.getStartTime();
                            double bill;
                            bill = L1.exitVehicle(number);
                            DecimalFormat dF = new DecimalFormat("0.00");
                            String formattedBill = dF.format(bill);
                            LevelFileReadWrite.writeToFile(L1, "level1");
                            JOptionPane.showMessageDialog(new JFrame(), "NAME: " + n + "\n" + "NUM-PLATE: " + number + "\n" + " SLOT#: " + slotTextfield.getText() + "\n" + "Bill: " + formattedBill + " PKR");
                            dispose();
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
                            JOptionPane.showMessageDialog(new JFrame(), "Select slots from 0-14");
                        }


                    }


                } else if (level.equals("2")) {
                    L2.slots = LevelFileReadWrite.LoadArray(L2.slots, "level2");
                    if (L2.slots == null) {
                        L2.slots = new User[L2.getCapacity()];
                    }
                    int index = -1;
                    Integer slotno = Integer.parseInt(slotTextfield.getText());
                    for (int i = 0; i < L2.getCapacity(); i++) {
                        if (L2.slots[i] != null && L2.slots[i].getUsername().equals(str) && L2.slots[i].getPassword().equals(password) && L2.slots[i].SLOTNO() == slotno) {
                            JOptionPane.showMessageDialog(new JFrame(), "Vehicle Found");
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
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
                        JOptionPane.showMessageDialog(new JFrame(), "NO Vehicle Found");
                    } else {
                        if (slotno > -1 && slotno < 10) {
                            Vehicle showVehicle = L2.slots[index].returnVehicleObject();
                            String n = L2.slots[index].getName();
                            String number = showVehicle.getNumberPlate();
                            int slotNumber = L2.slots[index].SLOTNO();
                            double totalTime = (System.currentTimeMillis() / 1000.0) - showVehicle.getStartTime();
                            double bill;
                            bill = L2.exitVehicle(number);
                            DecimalFormat dF = new DecimalFormat("0.00");
                            String formattedBill = dF.format(bill);
                            LevelFileReadWrite.writeToFile(L2, "level2");
                            JOptionPane.showMessageDialog(new JFrame(), "NAME: " + n + "\n" + "NUM-PLATE: " + number + "\n" + " SLOT#: " + slotTextfield.getText() + "\n" + "Bill: " + formattedBill + " PKR");
                            dispose();
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
                            JOptionPane.showMessageDialog(new JFrame(), "Select slots from 0-9");
                        }

                    }

                } else if (level.equals("3")) {
                    L3.slots = LevelFileReadWrite.LoadArray(L3.slots, "level3");
                    if (L3.slots == null) {
                        L3.slots = new User[L3.getCapacity()];
                    }
                    int index = -1;
                    Integer slotno = Integer.parseInt(slotTextfield.getText());
                    for (int i = 0; i < L3.getCapacity(); i++) {
                        if (L3.slots[i] != null && L3.slots[i].getUsername().equals(str) && L3.slots[i].getPassword().equals(password) && L3.slots[i].SLOTNO() == slotno) {
                            JOptionPane.showMessageDialog(new JFrame(), "Vehicle Found");
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
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
                        JOptionPane.showMessageDialog(new JFrame(), "NO Vehicle Found");
                    } else {
                        if (slotno > -1 && slotno < 10) {
                            Vehicle showVehicle = L3.slots[index].returnVehicleObject();
                            String n = L3.slots[index].getName();
                            String number = showVehicle.getNumberPlate();
                            int slotNumber = L3.slots[index].SLOTNO();
                            double totalTime = (System.currentTimeMillis() / 1000.0) - showVehicle.getStartTime();
                            double bill;
                            bill = L3.exitVehicle(number);
                            DecimalFormat dF = new DecimalFormat("0.00");
                            String formattedBill = dF.format(bill);
                            LevelFileReadWrite.writeToFile(L3, "level3");
                            JOptionPane.showMessageDialog(new JFrame(), "NAME: " + n + "\n" + "NUM-PLATE: " + number + "\n" + " SLOT#: " + slotTextfield.getText() + "\n" + "Bill: " + formattedBill + " PKR");
                            dispose();
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
                            JOptionPane.showMessageDialog(new JFrame(), "Select slots from 0-4");


                        }
                    }


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
                    JOptionPane.showMessageDialog(new JFrame(), "Please select Levels from 1 to 3");
                }
            }
        }
    }
}


