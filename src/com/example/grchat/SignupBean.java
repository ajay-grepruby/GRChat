package com.example.grchat;

public class SignupBean {
    
	   //private variables
	   int _id;
	   String _name;
	   String _email;
	   String _password;
	   String _cpassword;
	    
	   // Empty constructor
	   public SignupBean(){
	        
	   }
	   // constructor
	   public SignupBean(int id, String name, String _email, String _password, String _cpassword){
	       this._id = id;
	       this._name = name;
	       this._email = _email;
	       this._password = _password;
	       this._cpassword = _cpassword;
	   }
	    
	   // constructor
	   public SignupBean(String name, String _email, String _password, String _cpassword){
	       this._name = name;
	       this._email = _email;
	       this._password = _password;
	       this._cpassword = _cpassword;
	   }
	   // getting ID
	   public int getID(){
	       return this._id;
	   }
	    
	   // setting id
	   public void setID(int id){
	       this._id = id;
	   }
	    
	   // getting name
	   public String getName(){
	       return this._name;
	   }
	    
	   // setting name
	   public void setName(String name){
	       this._name = name;
	   }
	    
	  
	   public String getEmail(){
	       return this._email;
	   }
	    
	 
	   public void setEmail(String _email){
	       this._email = _email;
	   }
	 
	   public String getPassword(){
	       return this._password;
	   }
	    
	  
	   public void setPassword(String _password){
	       this._password = _password;
	   }
	   
	   public String getcPassword(){
	       return this._cpassword;
	   }
	    
	   
	   public void setcPassword(String _cpassword){
	       this._cpassword = _cpassword;
	   }
	}