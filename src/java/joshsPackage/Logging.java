/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joshsPackage;

import java.io.*;
import java.util.*;

/**
 *
 * @author Fierce
 */
public class Logging 
{
    //Every time a customer confirms an order or cancels an order a corresponding entry is
    //added to the log (either a log-file or database table – see comments below).
    //Every time an administrator adds/removes a product an entry is added to the log
    Logging(){
    }
    
    Logging(ArrayList<String> productOrders, boolean admin, boolean status) 
    {
        writeToLogFile(productOrders, admin, status);
        System.out.println("Wrote to log file!");
    }
    
    public void writeToLogFile(ArrayList<String> productOrders, boolean admin, boolean status)
    {
        String user = "", statusText = "";
        if(admin)
        {
            user = "Admin:";
            if(status)
                statusText = "Added";
            else
                statusText = "Removed";
        }   
        else
        {
            user = "Customer:";
            if(status)
                statusText = "Confirmed";
            else
                statusText = "Cancelled";
        }
        
        
        String text = "Logging Output";
        BufferedWriter output = null;
        try 
        {
            //need to check if file already exists
            File file = new File("LogFile.txt");
            
            output = new BufferedWriter(new FileWriter(file));
            String [] temp;
            for(int i = 0; i < productOrders.size(); i++)
            {
                temp = productOrders.get(i).split(",");
                text += "\nUser: " + user + "\tProduct: " + temp[0] + "\tQuantity: " + temp[1] + "\tStatus: " + statusText;
            }
            if(!file.exists())
            {
                output.write(text);
            }
            else
            {
                output.append(text);
            }
            output.close();
        } 
        catch ( IOException e ) 
        {
            e.printStackTrace();
        } 
    }
    
}
