package com.zohocorp.exceptionhandling;


//user-defined exception
class MyException extends Exception {//subclass of Exception
	public MyException(String s)
	{
		super(s); // Call constructor of parent Exception and passes a string
	}
}

public class CustomExceptions {
	public static void main(String args[])
	{
		try 
		{
			throw new MyException("User defined exception");
		}
		catch (MyException ex) 
		{
			System.out.println("Caught");
			System.out.println(ex.getMessage());
		}
	}
}
