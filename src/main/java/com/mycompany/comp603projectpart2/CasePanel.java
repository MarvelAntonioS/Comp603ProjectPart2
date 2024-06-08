
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CasePanel extends JPanel {
    private JButton[] caseButtons;

    public CasePanel(ActionListener caseButtonListener) 
    {
        setLayout(new GridLayout(5, 5, 10, 10));
        setBackground(Color.DARK_GRAY);

        caseButtons = new JButton[26];//Array of all cases
        for (int i = 0; i < caseButtons.length; i++) 
        {
            setCase(caseButtonListener, i);//call method to set case button
        }
    }
    
    private void setCase(ActionListener caseButtonListener, int index)
    {
        //initialises the current case
        caseButtons[index] = new JButton(String.valueOf(index + 1));
        caseButtons[index].setFont(new Font("Arial", Font.BOLD, 16));
        caseButtons[index].setBackground(Color.ORANGE);
        caseButtons[index].setForeground(Color.BLACK);
        caseButtons[index].addActionListener(caseButtonListener);
        add(caseButtons[index]);
    }

    public void disableButton(int index) 
    {
        //disable case button when it has been pressed
        caseButtons[index].setEnabled(false);
    }
    
    public void disableAllButtons()
    {
        for (JButton button : caseButtons)
        {
            button.setEnabled(false);
        }
    }
    
    public void enableAllButtons()
    {
        for (JButton button : caseButtons)
        {
            button.setEnabled(true);
        }
    }
}

