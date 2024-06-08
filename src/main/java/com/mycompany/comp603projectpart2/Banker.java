
package com.mycompany.comp603projectpart2;

import java.util.Arrays;

public class Banker 
{
    private int offer;
    private Case[] bankCases;
    private Case playerCase;
    
    public int makeOffer(Case[] cases, Case myCase)
    {
        playerCase = myCase;
        bankCases = Arrays.copyOf(cases, 26);
        calculateOffer();
        return offer;
    }
    
    private void calculateOffer()
    {
        long total = 0;
        long square = 0;
        
        for(int i=0; i<bankCases.length; i++)
        {
            if(bankCases[i] != null)
            {
                total++;
                square += Math.pow(bankCases[i].getMoney(),2);
            }
        }
        total++;
        square += Math.pow(playerCase.getMoney(),2);
        
        long mean = (square / total);
        offer = (int)Math.sqrt(mean); //returns the RMS value of all the current money in the cases
        
    }
    
}