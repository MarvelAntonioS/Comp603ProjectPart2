
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BankerPanel extends JPanel {
    
    private JLabel offerLabel;
    private JButton dealButton;
    private JButton noDealButton;
    private int currentOffer;

    public BankerPanel(ActionListener dealButtonListener, ActionListener noDealButtonListener) 
    {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBackground(Color.BLACK);

        initializeOfferLabel();
        initializeDealButton(dealButtonListener);
        initializeNoDealButton(noDealButtonListener);
    }
    
    private void initializeOfferLabel()
    {
        offerLabel = new JLabel("Banker's Offer: $0", SwingConstants.CENTER);
        offerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        offerLabel.setForeground(Color.WHITE);
        add(offerLabel);   
    }
    
    private void initializeDealButton(ActionListener dealButtonListener)
    {
        dealButton = new JButton("Deal");
        dealButton.setFont(new Font("Arial", Font.BOLD, 16));
        dealButton.setBackground(Color.GREEN);
        dealButton.setForeground(Color.BLACK);
        dealButton.setEnabled(false);
        dealButton.addActionListener(dealButtonListener);
        add(dealButton);    
    }
    
    private void initializeNoDealButton(ActionListener noDealButtonListener)
    {
        noDealButton = new JButton("No Deal");
        noDealButton.setFont(new Font("Arial", Font.BOLD, 16));
        noDealButton.setBackground(Color.RED);
        noDealButton.setForeground(Color.BLACK);
        noDealButton.setEnabled(false);
        noDealButton.addActionListener(noDealButtonListener);
        add(noDealButton);
    }

    public void updateOfferLabel(int offer) 
    {
        currentOffer = offer;
        offerLabel.setText("Banker's Offer: $" + offer);
    }

    public void enableButtons() 
    {
        dealButton.setEnabled(true);
        noDealButton.setEnabled(true);
    }

    public void disableButtons()
    {
        dealButton.setEnabled(false);
        noDealButton.setEnabled(false);
    }

    public int getCurrentOffer()
    {
        return currentOffer;
    }
}

