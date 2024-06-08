
package com.mycompany.comp603projectpart2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayInitializer {

    public Integer[] loadMoneyValues(String filePath) 
    {
        return loadValuesFromFile(filePath);
    }

    public Integer[] loadCaseNumbers(String filePath) 
    {
        return loadValuesFromFile(filePath);
    }

    private Integer[] loadValuesFromFile(String filePath) 
    {
        List<Integer> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                values.add(Integer.parseInt(line.trim()));
            }
        } 
        catch (IOException e) 
        {
            System.out.println("IOException occurred during runtime: " + e.getMessage());
        }
        return values.toArray(new Integer[0]);
    }
}
