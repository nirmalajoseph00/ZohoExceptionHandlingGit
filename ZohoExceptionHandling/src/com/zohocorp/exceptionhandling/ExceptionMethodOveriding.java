package com.zohocorp.exceptionhandling;

import java.io.*;  

class Parent{    
  void msg()throws Exception {  
    System.out.println("parent method");  
  }    
}    
    
class ExceptionMethodOveriding extends Parent{    
  void msg()throws ArithmeticException //subclass exception or no exception
  {  
    System.out.println("child method");  
  }    
    
  public static void main(String args[]){    
   Parent p = new ExceptionMethodOveriding();    
     
   try {    
   p.msg();    
   }  
   catch(Exception e) 
   {
	   System.out.println(e);
   }    
  }    
}    