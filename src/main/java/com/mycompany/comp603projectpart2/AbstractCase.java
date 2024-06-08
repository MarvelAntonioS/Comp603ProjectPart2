
package com.mycompany.comp603projectpart2;

public abstract class AbstractCase {
    protected int caseNumber;
    protected int money;

    public AbstractCase(int caseNumber, int money) 
    {
        this.caseNumber = caseNumber;
        this.money = money;
    }

    public int getCaseNumber() 
    {
        return caseNumber;
    }

    public int getMoney() 
    {
        return money;
    }

    public void open() 
    {
        System.out.println("\nCase number " + caseNumber + " had..... $" + money);
    }
}


