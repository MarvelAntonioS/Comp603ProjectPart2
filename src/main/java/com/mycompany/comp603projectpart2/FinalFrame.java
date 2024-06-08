
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class FinalFrame extends JFrame {
    private Case chosenCase;
    private Case lastCase;
    private JButton chosenCaseButton;
    private JButton lastCaseButton;

    public FinalFrame(Case chosenCase, Case lastCase) 
    {
        this.chosenCase = chosenCase;
        this.lastCase = lastCase;

        setTitle("Final Round");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color of content pane to black
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Create the label with padding
        JLabel instructionLabel = new JLabel("CHOOSE YOUR FINAL CASE", JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 36));
        instructionLabel.setForeground(Color.ORANGE); // Set text color to orange for visibility
        instructionLabel.setBorder(new EmptyBorder(20, 0, 20, 0)); // Add padding

        add(instructionLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for more control
        buttonPanel.setBackground(Color.BLACK); // Set background color of button panel to black

        // Initialize buttons
        chosenCaseButton = new JButton("Your case: " + chosenCase.getCaseNumber());
        lastCaseButton = new JButton("Last case: " + lastCase.getCaseNumber());

        // Set button properties
        chosenCaseButton.setBackground(Color.ORANGE);
        chosenCaseButton.setForeground(Color.BLACK);
        chosenCaseButton.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        chosenCaseButton.setVerticalAlignment(SwingConstants.CENTER); // Center text vertically
        lastCaseButton.setBackground(Color.ORANGE);
        lastCaseButton.setForeground(Color.BLACK);
        lastCaseButton.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        lastCaseButton.setVerticalAlignment(SwingConstants.CENTER); // Center text vertically

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(200, 100);
        chosenCaseButton.setPreferredSize(buttonSize);
        lastCaseButton.setPreferredSize(buttonSize);

        // Add action listeners to buttons
        chosenCaseButton.addActionListener(new CaseButtonListener(chosenCase.getMoney()));
        lastCaseButton.addActionListener(new CaseButtonListener(lastCase.getMoney()));

        // Add buttons to the panel with constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 50, 10, 50); // Add padding between buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH; // Make the button fill the cell
        gbc.weightx = 1.0; // Allow horizontal expansion
        gbc.weighty = 1.0; // Allow vertical expansion
        buttonPanel.add(chosenCaseButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(lastCaseButton, gbc);

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class CaseButtonListener implements ActionListener {
        private int money;

        public CaseButtonListener(int money) 
        {
            this.money = money;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(FinalFrame.this, "Congratulations! You have won $" + money);
            dispose();
        }
    }
}
