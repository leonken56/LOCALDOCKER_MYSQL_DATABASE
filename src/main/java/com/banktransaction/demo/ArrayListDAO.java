package com.banktransaction.demo;
import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class ArrayListDAO implements InterfaceBankDAO {
	ArrayList<Bank> BANKACCOUNTS = new ArrayList();
	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
	static final String USER = "root";
	static final String PASS = "my-secret-pw";
	static final String QUERY = "SELECT accountnumber, customername, email, phonenumber, balance FROM user";
	public ArrayListDAO(){
		Bank user1 = new Bank("123213111", 5000.50, "One Pham", "nova1@gmail.com","5103334444");
		Bank user2 = new Bank("123241222", 75000.50, "Two Pham", "nova2@gmail.com","510333555");
		Bank user3 = new Bank("121223333", 90000.50, "Three Pham", "nova3@gmail.com","5103336666");
		
		addNewAccount(user1);
		addNewAccount(user2);
		addNewAccount(user3);
		
		
	}
	public ArrayList<Bank> getAllAccounts() {
		// TODO Auto-generated method stub
		return BANKACCOUNTS;
	}


	public Bank getAccount(String accountnumber) {
		// TODO Auto-generated method stub
	    for (Bank x : BANKACCOUNTS) {
	    	String curAN = x.getAccountnumber();
	        if (curAN.equals(accountnumber))
	        	return x;
	      }
		return Bank.empty();
	}


	public void updateName(String accountnumber, String name) {
		// TODO Auto-generated method stub
		Bank thisBank = this.getAccount(accountnumber);
		thisBank.setCustomername(name);
	}


	public void updateEmail(String accountnumber, String email) {
		// TODO Auto-generated method stub
		Bank thisBank = this.getAccount(accountnumber);
		thisBank.setEmail(email);
	}


	public void updatePhoneNumber(String accountnumber, String PhoneNumber) {
		// TODO Auto-generated method stub
		Bank thisBank = this.getAccount(accountnumber);
		thisBank.setPhonenumber(PhoneNumber);
	}


	public void deleteAccount(String accountnumber) {
		// TODO Auto-generated method stub
		BANKACCOUNTS.remove(BANKACCOUNTS.indexOf(accountnumber));
	}

	public void addNewAccount(Bank account) {
		// TODO Auto-generated method stub
		BANKACCOUNTS.add(account);
		
	}


	public void deposit(String accountnumber, double amount) {
		// TODO Auto-generated method stub
		Bank thisBank = this.getAccount(accountnumber);
		if ((amount>=5) &&(amount<=10000))
			thisBank.deposit(amount);
		else System.out.println("Deposit fund must be between $5 - $10000");
			
	}

	public String printAllBalancetoString() {
		String result = "";
		result += "ACCOUNT NUMBER / CUSTOMER NAME / BALANCE ($)<br>";
	    for (Bank x : BANKACCOUNTS) {
	    	result += x.getAccountnumber() + " / " + x.getCustomername() + " / Balance: $" + x.getBalance() + "<br>"; 
	    	
	      }
	    return result;
	}

	public Bank getAccountByName(String name) {
		// TODO Auto-generated method stub
	    for (Bank x : BANKACCOUNTS) {
	    	String curName = x.getCustomername();
	        if (curName.equals(name))
	        	return x;
	      }
		return Bank.empty();
	}
	public String displayinfo(Bank account) {
		// TODO Auto-generated method stub
		Bank thisBank = account;
		return thisBank.getAccountnumber() + " / " + thisBank.getCustomername() + " / " + thisBank.getEmail() + " / " + thisBank.getPhonenumber() + " / $" + thisBank.getBalance();
	}

	public String displaybalance(String accountnumber) {
		// TODO Auto-generated method stub
		Bank curAccount = getAccount(accountnumber);
		double curBalance = curAccount.getBalance();
		System.out.println("This account [" + accountnumber + "] has a balance of: $" + curBalance);
		return "This account [" + accountnumber + "] has a balance of: $" + curBalance;
	}

	public void displayall() {
	    for (Bank x : BANKACCOUNTS) {
	    	x.printinfo();
	      }
		
	}
	public void addAccounttoDatabase(Bank thisaccount) {
		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         // Execute a query
			 	System.out.println("Connected database successfully...");             
		        String sql = "";
		        
		 			System.out.println("Inserting "+ thisaccount.getCustomername() +" into the table...");
		 			sql = "INSERT INTO user (accountnumber,customername,phonenumber,email,balance) VALUES "
		 					+ "('"+ thisaccount.getAccountnumber() +"','"+ thisaccount.getCustomername()+"','"+ thisaccount.getPhonenumber()+"','"+ thisaccount.getEmail() +"','"+thisaccount.getBalance()+"');";
		 			
		 			//System.out.println(sql);
		 			stmt.executeUpdate(sql);
		 			System.out.println("Inserted " + thisaccount.getAccountnumber() + " record into the table...");

		 		
		            	  
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 	
		
	}
	
	public void getAllfromDatabase() {

		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(QUERY);
			      ) {		      
			         while(rs.next()){
			            //Display values
			        	System.out.println("-----------------------------------------");
			            System.out.println("AccountNumber: " + rs.getString("accountnumber"));
			            System.out.println("Name: " + rs.getString("customername"));
			            System.out.println("Phone Number: " + rs.getString("phonenumber"));
			            System.out.println("Email: " + rs.getString("email"));
			            System.out.println("Balance: " + rs.getDouble("balance"));  
			            //System.out.println("Password: " + rs.getString("password")); 
			            //System.out.println("Created on: " + rs.getTimestamp("time")); 
			            System.out.println("-----------------------------------------");
			         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		 

	}
	public void addAlltoDatabase() {

		 try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         // Execute a query
			 	System.out.println("Connected database successfully...");             
		        String sql = "";
		        
		 		for (Bank x : BANKACCOUNTS) {
		 			
		 			System.out.println("Inserting "+ x.getCustomername() +" into the table...");
		 			sql = "INSERT INTO user (accountnumber,customername,phonenumber,email,balance) VALUES "
		 					+ "('"+ x.getAccountnumber() +"','"+ x.getCustomername()+"','"+ x.getPhonenumber()+"','"+ x.getEmail() +"','"+x.getBalance()+"');";
		 			
		 			//System.out.println(sql);
		 			stmt.executeUpdate(sql);
		 			System.out.println("Inserted " + x.getAccountnumber() + " record into the table...");
		 		}
		 		
		            	  
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		 

	}

}
