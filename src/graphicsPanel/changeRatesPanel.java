package graphicsPanel;

import Vehicle.*;

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

import javax.swing.*;
import java.awt.event.ActionListener;

public class changeRatesPanel extends JFrame implements ActionListener {
    private JLabel categoryLabel;
    private JLabel newRateBill;
    private JTextField newRateField;
    private JButton confirm;
    private String[] category = {"...", "SUV", "Car", "Bike"};
    private JComboBox<String> categoryBox = new JComboBox<>(category);

    public changeRatesPanel() {
        super("CHANGE RATES");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(160, 15, 100, 20);
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        categoryBox.addActionListener(this);
        categoryBox.setBounds(140, 45, 100, 20);

        newRateBill = new JLabel("New Rate:");
        newRateBill.setBounds(155, 75, 100, 20);
        newRateBill.setFont(new Font("Segoe UI", Font.BOLD, 14));

        newRateField = new JTextField();
        newRateField.setBounds(140, 100, 100, 20);

        confirm = new JButton("Confirm ");
        confirm.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirm.setBounds(280, 130, 100, 20);
        confirm.addActionListener(this);
        confirm.setFocusable(false);

        add(categoryLabel);
        add(categoryBox);
        add(newRateBill);
        add(newRateField);
        add(confirm);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCategory = (String) categoryBox.getSelectedItem();
        if (e.getSource() == confirm) {
            Double newRate = Double.parseDouble(newRateField.getText());
            if (selectedCategory.equals("...") || newRateField.getText().isEmpty()) {
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
                if (selectedCategory.equals("SUV")) {
                    billRates.setSuvBill(newRate);
                    JOptionPane.showMessageDialog(new JFrame(), "Rates Updated");
                    dispose();
                }
                if (selectedCategory.equals("Car")) {
                    billRates.setCarBill(newRate);
                    JOptionPane.showMessageDialog(new JFrame(), "Rates Updated");
                    dispose();
                }
                if (selectedCategory.equals("Bike")) {
                    billRates.setBikeBill(newRate);
                    JOptionPane.showMessageDialog(new JFrame(), "Rates Updated");
                    dispose();
                }

            }
        }


    }
}
