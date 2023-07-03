package graphicsPanel;

import Vehicle.*;
import personPackage.*;
import Levels.*;

import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class carParkPanel extends JFrame implements ActionListener {
    private User signedUser;
    levelWise L1 = new levelWise(15);
    levelWise L2 = new levelWise(10);
    levelWise L3 = new levelWise(5);
    private Vehicle v;

    private JLabel carNameLabel, numPlateLabel, categoryLabel, levelsLabel, slotLabel;

    private JTextField carNameTextfield, numPlateTextfield, slotTextfield;
    private JButton confirmButton;
    private JPanel panel;

    private String[] levels = {"...", "Level 1", "Level 2", "Level 3"};
    private String[] category = {"...", "SUV", "Car", "Bike"};

    private JComboBox<String> comboBox = new JComboBox<>(levels);
    private JComboBox<String> categoryBox = new JComboBox<>(category);

    private DefaultTableModel model;

    public carParkPanel() {
        super("CAR PARKING PANEL");
        loadSignedUser();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        panel.setPreferredSize(new Dimension(160, getHeight()));
        panel.setBackground(Color.GRAY);


        carNameLabel = new JLabel("Car Name:");
        carNameLabel.setPreferredSize(new Dimension(100, 20));
        carNameTextfield = new JTextField();
        carNameTextfield.setPreferredSize(new Dimension(100, 20));

        numPlateLabel = new JLabel("Num-Plate:");
        numPlateLabel.setPreferredSize(new Dimension(100, 20));

        numPlateTextfield = new JTextField();
        numPlateTextfield.setPreferredSize(new Dimension(100, 20));

        levelsLabel = new JLabel("Levels:");
        levelsLabel.setPreferredSize(new Dimension(100, 20));

        comboBox.addActionListener(this);
        comboBox.setPreferredSize(new Dimension(100, 20));

        slotLabel = new JLabel("Slot#:");
        slotLabel.setPreferredSize(new Dimension(100, 20));

        slotTextfield = new JTextField();
        slotTextfield.setPreferredSize(new Dimension(100, 20));

        categoryLabel = new JLabel("Category:");
        categoryLabel.setPreferredSize(new Dimension(100, 20));

        categoryBox.addActionListener(this);
        categoryBox.setPreferredSize(new Dimension(100, 20));

        confirmButton = new JButton("CONFIRM");
        confirmButton.setPreferredSize(new Dimension(100, 20));
        confirmButton.setFocusable(false);
        confirmButton.addActionListener(this);

        model = new DefaultTableModel();

        panel.add(carNameLabel);
        panel.add(carNameTextfield);
        panel.add(numPlateLabel);
        panel.add(numPlateTextfield);
        panel.add(levelsLabel);
        panel.add(comboBox);
        panel.add(slotLabel);
        panel.add(slotTextfield);
        panel.add(categoryLabel);
        panel.add(categoryBox);
        panel.add(confirmButton);

        add(panel, BorderLayout.WEST);
        setVisible(true);
    }

    public User getSignedUser() {
        return signedUser;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public void loadSignedUser() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("SignedUser.ser"));
            this.signedUser = (User) objectInputStream.readObject();
        } catch (EOFException ex) {
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedLevel = (String) comboBox.getSelectedItem();
        String categorySelect = (String) categoryBox.getSelectedItem();

        if (selectedLevel.equals("Level 1")) {

            model.setRowCount(0);
            model.setColumnCount(0);

            L1.slots = LevelFileReadWrite.LoadArray(L1.slots, "level1");
            if (L1.slots == null) {
                L1.slots = new User[L1.getCapacity()];
            }
            String[][] rows = getslots1();

            String[] columns = {"Slots", "Status"};

            model.setColumnIdentifiers(columns);

            for (String[] row : rows) {
                model.addRow(row);
            }


            JTable table = new JTable(model);

            JScrollPane pane = new JScrollPane(table);

            add(pane, BorderLayout.CENTER);
            revalidate();


        }
        if (selectedLevel.equals("Level 2")) {

            model.setColumnCount(0);
            model.setRowCount(0);


            L2.slots = LevelFileReadWrite.LoadArray(L2.slots, "level2");
            if (L2.slots == null) {
                L2.slots = new User[L2.getCapacity()];
            }

            String[][] rows = getslots2();

            String[] columns = {"Slots", "Status"};

            model.setColumnIdentifiers(columns);

            for (String[] row : rows) {
                model.addRow(row);
            }


            JTable table1 = new JTable(model);

            JScrollPane pane = new JScrollPane(table1);

            add(pane, BorderLayout.CENTER);
            revalidate();


        }
        if (selectedLevel.equals("Level 3")) {

            model.setColumnCount(0);
            model.setRowCount(0);

            L3.slots = LevelFileReadWrite.LoadArray(L3.slots, "level3");
            if (L3.slots == null) {
                L3.slots = new User[L3.getCapacity()];
            }

            String[][] rows = getslots3();

            String[] columns = {"Slots", "Status"};

            model.setColumnIdentifiers(columns);

            for (String[] row : rows) {
                model.addRow(row);
            }


            JTable table2 = new JTable(model);

            JScrollPane pane = new JScrollPane(table2);

            add(pane, BorderLayout.CENTER);
            revalidate();

        }
        if (e.getSource() == confirmButton) {
            String vehicle = carNameTextfield.getText();
            String numberPlate = numPlateTextfield.getText();
            int slotNumber = 0;
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
                slotNumber = Integer.parseInt(slotTextfield.getText());
                if (vehicle.isEmpty() || numberPlate.isEmpty() || categorySelect.equals("...") || selectedLevel.equals("...")) {
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

                    if (categorySelect.equals("SUV")) {
                        double time = System.currentTimeMillis() / 1000.0;
                        v = new SUV(vehicle, numberPlate, time, slotNumber);
                    }
                    if (categorySelect.equals("Car")) {
                        double time = System.currentTimeMillis() / 1000.0;
                        v = new Car(vehicle, numberPlate, time, slotNumber);
                    }
                    if (categorySelect.equals("Bike")) {
                        double time = System.currentTimeMillis() / 1000.0;
                        v = new Bike(vehicle, numberPlate, time, slotNumber);

                    }
                    if (selectedLevel.equals("Level 1")) {
                        if (slotNumber >= 0 && slotNumber < L1.getCapacity()) {
                            if (!L1.alreadyParked(slotNumber)) {
                                User us = new User(getSignedUser().getName(), getSignedUser().getPhoneNo(), getSignedUser().getUsername(), getSignedUser().getPassword(), getSignedUser().getCNIC(), v);
                                L1.parkVehicle(us);
                                LevelFileReadWrite.writeToFile(L1, "level1");
                                JOptionPane.showMessageDialog(new JFrame(),"VEHICLE PARKED SUCCESSFULLY");
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
                                JOptionPane.showMessageDialog(new JFrame(), "Slot is occupied");
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
                        if (slotNumber >= 0 && slotNumber < L2.getCapacity()) {
                            if (!L2.alreadyParked(slotNumber)) {
                                User us = new User(getSignedUser().getName(), getSignedUser().getPhoneNo(), getSignedUser().getUsername(), getSignedUser().getPassword(), getSignedUser().getCNIC(), v);
                                L2.parkVehicle(us);
                                LevelFileReadWrite.writeToFile(L2, "level2");
                                JOptionPane.showMessageDialog(new JFrame(),"VEHICLE PARKED SUCCESSFULLY");
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
                                JOptionPane.showMessageDialog(new JFrame(), "Slot is occupied");
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
                        if (slotNumber >= 0 && slotNumber < L3.getCapacity()) {
                            if (!L3.alreadyParked(slotNumber)) {
                                User us = new User(getSignedUser().getName(), getSignedUser().getPhoneNo(), getSignedUser().getUsername(), getSignedUser().getPassword(), getSignedUser().getCNIC(), v);
                                L3.parkVehicle(us);
                                LevelFileReadWrite.writeToFile(L3, "level3");
                                JOptionPane.showMessageDialog(new JFrame(),"VEHICLE PARKED SUCCESSFULLY");
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
                                JOptionPane.showMessageDialog(new JFrame(), "Slot is occupied");
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


        }
    }

    public String[][] getslots1() {
        String[][] slotList = new String[L1.slots.length][];
        for (int i = 0; i < L1.getCapacity(); i++) {
            String[] s = new String[2];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L1.slots[i] == null) {
                s[1] = "Available";
            } else {
                s[1] = "Occupied";
            }
            slotList[i] = s;
        }
        return slotList;
    }

    public String[][] getslots2() {
        String[][] slotList = new String[L2.slots.length][];
        for (int i = 0; i < L2.getCapacity(); i++) {
            String[] s = new String[2];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L2.slots[i] == null) {
                s[1] = "Available";
            } else {
                s[1] = "Occupied";
            }
            slotList[i] = s;
        }
        return slotList;
    }

    public String[][] getslots3() {
        String[][] slotList = new String[L3.slots.length][];
        for (int i = 0; i < L3.getCapacity(); i++) {
            String[] s = new String[2];
            s[0] = Integer.toString(i);
            System.out.println(s[0]);
            if (L3.slots[i] == null) {
                s[1] = "Available";
            } else {
                s[1] = "Occupied";
            }
            slotList[i] = s;
        }
        return slotList;
    }

}
