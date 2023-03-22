package com.zohocorp.exceptionhandling;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DivideByZeroError {

	public static void main(String[] args) 
	{
		int a,d=0;
		PrintWriter pw;  
		
		try 
		{
			//checked exception
			try//nested try
			{
				pw = new PrintWriter("jtp.txt"); //may throw exception   
	            pw.println("saved");
	            System.out.println("File saved successfully");
			}
			catch (FileNotFoundException e) {  
	            
	            System.out.println(e);  
	        }  
            
			//unchecked exception
			try
			{
	            int b[]=new int[5];    
	            System.out.println(b[8]);
			}
			catch(ArrayIndexOutOfBoundsException e)  
	        {  
	         System.out.println("ArrayIndexOutOfBounds Exception occurs");  
	        } 
            try
            {
            	String s=null;  
            	System.out.println(s.length());  
            }
            catch(NullPointerException npe)
    		{
    			System.out.println(npe);
    		}
            
			try
			{
				a=42/d;
			} //no appropriate catch block so it goes to parent try-catch block
			catch(ArrayIndexOutOfBoundsException e)  
	        {  
	         System.out.println("ArrayIndexOutOfBounds Exception occurs");  
	        } 
			
			  
		}
		catch(ArithmeticException e)//type of generated exception
		{
			System.out.println(e);
			System.out.println("Cannot divide by zero"); //custom msg
			System.out.println("After resolving error");
			a=42/(d+1); //resolve exception
			System.out.println(a);
		}  
		 
		catch(Exception e)  //catch blocks must be ordered from most specific to most general
        {  
         System.out.println("Parent Exception occurs");  
        }   
		
		finally //only 1 finally for each try block
		{  
		        System.out.println("finally block is always executed");  
        }    
		   
		System.out.println("rest of the code...");  
	}

}
