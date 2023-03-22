package com.zohocorp.exceptionhandling;

import java.io.IOException;

public class DemonstrateThrow {
	public static void n()throws IOException
	{
		throw new IOException("demo IOException ");
		
	}
	
	static void demoprog() throws IOException //exception declared
	{
		n();
	}
	public static void main(String args[]) //exception handled
	{
		try 
		{
			demoprog();
		} 
		catch (IOException e) 
		{
			System.out.println("Recaught:" + e);
		}
	}

}
