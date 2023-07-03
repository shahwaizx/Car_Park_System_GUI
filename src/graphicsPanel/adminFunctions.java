package graphicsPanel;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Vehicle.*;

import Levels.*;

import personPackage.*;

public class adminFunctions extends JFrame implements ActionListener {
    levelWise L1 = new levelWise(15);
    levelWise L2 = new levelWise(10);
    levelWise L3 = new levelWise(5);

    private JPanel panel;
    private JLabel picLabel, levelLabel, slotLabel, separateLabel, categoryLabel;
    private JTextField slotTextfield;
    private String[] levels = {"...", "Level 1", "Level 2", "Level 3"};
    private String[] category = {"...", "SUV", "Car", "Bike"};
    private JComboBox<String> levelBox = new JComboBox<>(levels);
    private JComboBox<String> categoryBox = new JComboBox<>(category);
    private ImageIcon admin = new ImageIcon("admin.png");
    private DefaultTableModel model;
    private JButton exitVehicle, changeButton, changePassword;

    public adminFunctions() {
        super("ADMIN FUNCTIONS");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        panel.setPreferredSize(new Dimension(160, getHeight()));
        panel.setBackground(Color.GRAY);

        picLabel = new JLabel(admin);
        picLabel.setPreferredSize(new Dimension(72, 72));

        levelLabel = new JLabel("View Level");
        levelLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        levelLabel.setPreferredSize(new Dimension(100, 20));

        levelBox.addActionListener(this);
        levelBox.setPreferredSize(new Dimension(100, 20));
        model = new DefaultTableModel();

        slotLabel = new JLabel("Slot to Exit:");
        slotLabel.setPreferredSize(new Dimension(100, 20));
        slotLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        slotTextfield = new JTextField();
        slotTextfield.setPreferredSize(new Dimension(100, 20));

        exitVehicle = new JButton("Exit");
        exitVehicle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exitVehicle.setPreferredSize(new Dimension(80, 20));
        exitVehicle.addActionListener(this);
        exitVehicle.setFocusable(false);

        separateLabel = new JLabel("--------------");
        separateLabel.setPreferredSize(new Dimension(90, 20));
        separateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        changeButton = new JButton("Change Rates");
        changeButton.setPreferredSize(new Dimension(120, 20));
        changeButton.addActionListener(this);
        changeButton.setFocusable(false);

        changePassword = new JButton("Change Pass");
        changePassword.setPreferredSize(new Dimension(120, 20));
        changePassword.addActionListener(this);
        changePassword.setFocusable(false);

        panel.add(picLabel);
        panel.add(levelLabel);
        panel.add(levelBox);
        panel.add(slotLabel);
        panel.add(slotTextfield);
        panel.add(exitVehicle);
        panel.add(separateLabel);
        panel.add(changeButton);
        panel.add(changePassword);

        add(panel, BorderLayout.WEST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedLevel = (String) levelBox.getSelectedItem();

        if (selectedLevel.equals("Level 1")) {

            model.setRowCount(0);
            model.setColumnCount(0);

            L1.slots = LevelFileReadWrite.LoadArray(L1.slots, "level1");
            if (L1.slots == null) {
                L1.slots = new User[L1.getCapacity()];
            }
            String[][] rows = getslots1();

            String[] coloumns = {"Slots", "Name", "PhoneNo", "CNIC", "Vehicle's Name", "NumberPlate", "Category"};
            model.setColumnIdentifiers(coloumns);

            for (String[] row : rows) {
                model.addRow(row);
            }
            JTable table = new JTable(model);

            JScrollPane pane = new JScrollPane(table);

            add(pane, BorderLayout.CENTER);
            revalidate();


        }
        if (selectedLevel.equals("Level 2")) {

            model.setRowCount(0);
            model.setColumnCount(0);

            L2.slots = LevelFileReadWrite.LoadArray(L2.slots, "level2");
            if (L2.slots == null) {
                L2.slots = new User[L2.getCapacity()];
            }
            String[][] rows = getslots2();

            String[] coloumns = {"Slots", "Name", "PhoneNo", "CNIC", "Vehicle's Name", "NumberPlate", "Category"};
            model.setColumnIdentifiers(coloumns);

            for (String[] row : rows) {
                model.addRow(row);
            }
            JTable table = new JTable(model);

            JScrollPane pane = new JScrollPane(table);

            add(pane, BorderLayout.CENTER);
            revalidate();

        }
        if (selectedLevel.equals("Level 3")) {

            model.setRowCount(0);
            model.setColumnCount(0);

            L3.slots = LevelFileReadWrite.LoadArray(L3.slots, "level3");
            if (L3.slots == null) {
                L3.slots = new User[L3.getCapacity()];
            }
            String[][] rows = getslots3();

            String[] coloumns = {"Slots", "Name", "PhoneNo", "CNIC", "Vehicle's Name", "NumberPlate", "Category"};
            model.setColumnIdentifiers(coloumns);

            for (String[] row : rows) {
                model.addRow(row);
            }
            JTable table = new JTable(model);

            JScrollPane pane = new JScrollPane(table);

            add(pane, BorderLayout.CENTER);
            revalidate();

        }
        if (e.getSource() == exitVehicle) {
            if (slotTextfield.getText().isEmpty()) {
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
                int slotNumber = Integer.parseInt(slotTextfield.getText());
                if (selectedLevel.equals("Level 1")) {
                    L1.slots = LevelFileReadWrite.LoadArray(L1.slots, "level1");
                    if (L1.slots == null) {
                        L1.slots = new User[L1.getCapacity()];
                    }

                    if (slotNumber >= 0 && slotNumber < L1.getCapacity()) {
                        if (L1.alreadyParked(slotNumber)) {
                            L1.EXITVEHICLEADMIN(slotNumber);
                            LevelFileReadWrite.writeToFile(L1, "level1");
                            JOptionPane.showMessageDialog(new JFrame(), "VEHICLE REMOVED");
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
                            JOptionPane.showMessageDialog(new JFrame(), "Slot is already Empty");
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
                        JOptionPane.showMessageDialog(new JFrame(), "Please Select slot from 0-14");
                    }
                }
                if (selectedLevel.equals("Level 2")) {
                    L2.slots = LevelFileReadWrite.LoadArray(L2.slots, "level2");
                    if (L2.slots == null) {
                        L2.slots = new User[L2.getCapacity()];
                    }
                    if (slotNumber >= 0 && slotNumber < L2.getCapacity()) {
                        if (L2.alreadyParked(slotNumber)) {
                            L2.EXITVEHICLEADMIN(slotNumber);
                            LevelFileReadWrite.writeToFile(L2, "level2");
                            JOptionPane.showMessageDialog(new JFrame(), "VEHICLE REMOVED");
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
                            JOptionPane.showMessageDialog(new JFrame(), "Slot is already Empty");
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
                        JOptionPane.showMessageDialog(new JFrame(), "Please Select slot from 0-9");
                    }
                }
                if (selectedLevel.equals("Level 3")) {
                    L3.slots = LevelFileReadWrite.LoadArray(L3.slots, "level3");
                    if (L3.slots == null) {
                        L3.slots = new User[L3.getCapacity()];
                    }
                    if (slotNumber >= 0 && slotNumber < L3.getCapacity()) {
                        if (L3.alreadyParked(slotNumber)) {
                            L3.EXITVEHICLEADMIN(slotNumber);
                            LevelFileReadWrite.writeToFile(L3, "level3");
                            JOptionPane.showMessageDialog(new JFrame(), "VEHICLE REMOVED");
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
                            JOptionPane.showMessageDialog(new JFrame(), "Slot is already Empty");
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
                        JOptionPane.showMessageDialog(new JFrame(), "Please Select slot from 0-4");
                    }
                }
            }
        }
        if (e.getSource() == changeButton) {
            new changeRatesPanel();
        }
        if (e.getSource() == changePassword) {
            String newPass = JOptionPane.showInputDialog(new JFrame(), "Enter new password: ");
            if (newPass.isEmpty()) {
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
                ArrayList<Person> adminTemp = new ArrayList<>();
                personFileReadWrite.LoadArrayListPerson("admin");
                adminTemp = personFileReadWrite.getArrayList();
                for (Person p : adminTemp) {
                    p.setPassword(newPass);
                }
                personFileReadWrite.writeToFile(adminTemp, "admin");
                JOptionPane.showMessageDialog(new JFrame(),"Password Updated");
            }

        }
    }
    public String[][] getslots1() {
        String[][] slotList = new String[L1.slots.length][];
        for (int i = 0; i < L1.getCapacity(); i++) {
            String[] s = new String[7];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L1.slots[i] == null) {
                s[1] = "---";
                s[2] = "---";
                s[3] = "---";
                s[4] = "---";
                s[5] = "---";
                s[6] = "---";
            } else {
                s[1] = L1.slots[i].getName();
                s[2] = L1.slots[i].getPhoneNo();
                s[3] = L1.slots[i].getCNIC();
                s[4] = L1.slots[i].returnVehicleObject().getCarName();
                s[5] = L1.slots[i].returnVehicleObject().getNumberPlate();
                if (L1.slots[i].returnVehicleObject() instanceof SUV) {
                    s[6] = "SUV";
                } else if (L1.slots[i].returnVehicleObject() instanceof Car) {
                    s[6] = "Car";
                } else if (L1.slots[i].returnVehicleObject() instanceof Bike) {
                    s[6] = "Bike";
                }
            }
            slotList[i] = s;
        }
        return slotList;
    }
    public String[][] getslots2() {
        String[][] slotList = new String[L2.slots.length][];
        for (int i = 0; i < L2.getCapacity(); i++) {
            String[] s = new String[7];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L2.slots[i] == null) {
                s[1] = "---";
                s[2] = "---";
                s[3] = "---";
                s[4] = "---";
                s[5] = "---";
                s[6] = "---";
            } else {
                s[1] = L2.slots[i].getName();
                s[2] = L2.slots[i].getPhoneNo();
                s[3] = L2.slots[i].getCNIC();
                s[4] = L2.slots[i].returnVehicleObject().getCarName();
                s[5] = L2.slots[i].returnVehicleObject().getNumberPlate();
                if (L2.slots[i].returnVehicleObject() instanceof SUV) {
                    s[6] = "SUV";
                } else if (L2.slots[i].returnVehicleObject() instanceof Car) {
                    s[6] = "Car";
                } else if (L2.slots[i].returnVehicleObject() instanceof Bike) {
                    s[6] = "Bike";
                }
            }
            slotList[i] = s;
        }
        return slotList;
    }
    public String[][] getslots3() {
        String[][] slotList = new String[L3.slots.length][];
        for (int i = 0; i < L3.getCapacity(); i++) {
            String[] s = new String[7];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L3.slots[i] == null) {
                s[1] = "---";
                s[2] = "---";
                s[3] = "---";
                s[4] = "---";
                s[5] = "---";
                s[6] = "---";
            } else {
                s[1] = L3.slots[i].getName();
                s[2] = L3.slots[i].getPhoneNo();
                s[3] = L3.slots[i].getCNIC();
                s[4] = L3.slots[i].returnVehicleObject().getCarName();
                s[5] = L3.slots[i].returnVehicleObject().getNumberPlate();
                if (L3.slots[i].returnVehicleObject() instanceof SUV) {
                    s[6] = "SUV";
                } else if (L3.slots[i].returnVehicleObject() instanceof Car) {
                    s[6] = "Car";
                } else if (L3.slots[i].returnVehicleObject() instanceof Bike) {
                    s[6] = "Bike";
                }
            }
            slotList[i] = s;
        }
        return slotList;
    }
}
