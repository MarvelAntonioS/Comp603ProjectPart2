
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MoneyPanel extends JPanel {
    
    public JLabel[] moneyLabels;
    public Map<Integer, JLabel> moneyLabelMap;

    public MoneyPanel(Integer[] moneyValues)
    {
        setLayout(new GridLayout(moneyValues.length / 2, 2, 5, 5));
        setBackground(Color.BLACK);

        moneyLabels = new JLabel[moneyValues.length];
        moneyLabelMap = new HashMap<>();

        for (int i = 0; i < moneyValues.length; i++) 
        {
            setMoneyValues(moneyValues, i);
        }
    }
    
    private void setMoneyValues(Integer[] moneyValues, int i)
    {
        moneyLabels[i] = new JLabel("$" + moneyValues[i]);
        moneyLabels[i].setFont(new Font("Arial", Font.BOLD, 16));
        moneyLabels[i].setForeground(Color.WHITE);
        add(moneyLabels[i]);
        moneyLabelMap.put(moneyValues[i], moneyLabels[i]);    
    }

    public void markOffMoney(int moneyValue) 
    {
        JLabel label = moneyLabelMap.get(moneyValue);
        if (label != null) 
        {
            label.setForeground(Color.RED);
        }
    }
}

