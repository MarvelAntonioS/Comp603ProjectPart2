
package com.mycompany.comp603projectpart2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CaseSet {
    public Case[] cases;
    public Case[] originalCases;
    public Case firstChosenCase;
    public Integer[] moneyValues;
    public Integer[] caseNumbers;
    public int numOfCases = 26;

    public CaseSet() 
    {
        ArrayInitializer initializer = new ArrayInitializer();
        this.moneyValues = initializer.loadMoneyValues("money_values.txt");
        this.caseNumbers = initializer.loadCaseNumbers("case_numbers.txt");

        this.cases = new Case[26];
        initialiseCases();
        this.originalCases = Arrays.copyOf(cases, cases.length);
    }

    public Case[] getCases() {
        return this.cases;
    }

    public Case[] getOriginalCases() {
        return this.originalCases;
    }

    public final void initialiseCases() 
    {
        List<Integer> caseList = Arrays.asList(caseNumbers);
        Collections.shuffle(caseList); // shuffle randomly resorts list
        Integer[] shuffledCases = caseList.toArray(new Integer[0]); // back to Integer array so we can initialise cases

        List<Integer> moneyList = Arrays.asList(moneyValues);
        Collections.shuffle(moneyList);
        Integer[] shuffledMoney = moneyList.toArray(new Integer[0]);

        for (int i = 0; i < cases.length; i++) {
            this.cases[i] = new Case(shuffledCases[i], shuffledMoney[i]);
        }
    }

    public void removeCase(int removeCaseNum) 
    {
        //find and remove case from the case array
        for (int i = 0; i < cases.length; i++) {
            if (cases[i] != null && cases[i].getCaseNumber() == removeCaseNum) {
                cases[i] = null;
                numOfCases--;
                break;
            }
        }
    }

    public Case getCase(int index) 
    {
        if (index > 0 && index <= cases.length) {
            return cases[index - 1];
        } else {
            return null; // Handle invalid case number
        }
    }
}
