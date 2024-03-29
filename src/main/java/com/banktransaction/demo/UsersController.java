package com.banktransaction.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/UsersController")
public class UsersController {
	@Autowired
	ArrayListDAO ALLBANK = new ArrayListDAO();
	@GetMapping("/getAllUsers")
	public String getAllUsers() {
		return "HTTP GET request was sent <br>" + ALLBANK.printAllBalancetoString();
	}
	@GetMapping("/getAccountByName/{name}")
	public String getName(@PathVariable("name") String name) {
		Bank thisbank = ALLBANK.getAccountByName(name);		
		return thisbank.printinfo();
	}
	@GetMapping("/getUsers")
	public String getUsers() {
		ALLBANK.getAllAccounts();
		return "HTTP GET request was sent <br>" + ALLBANK.printAllBalancetoString();
	}
	@GetMapping("/accountbalance/{accountnumber}")
	public String getAccountBalance(@PathVariable("accountnumber") String accountnumber) {
		
		return ALLBANK.displaybalance(accountnumber);
	}
	@PostMapping("/createnewaccount")
	public String createUser(@RequestBody Bank newaccount) {
		ALLBANK.addNewAccount(newaccount);
		//ALLBANK.addAlltoDatabase();
		return newaccount.getCustomername() + " - " + "Added";
	}
	@DeleteMapping("/deleteaccount/{accountnumber}")
	public String deleteUser(@PathVariable("accountnumber") String accountnumber) {
		ALLBANK.deleteAccount(accountnumber);
		return accountnumber + " Account has been deleted";
	}
	@PutMapping("/updateusername/{accountnumber}")
	public String updateUsername(@PathVariable("accountnumber") String accountnumber,@RequestParam String value ) {
		ALLBANK.updateName(accountnumber, value);
		return accountnumber + "'s customer name was changed to " + value;
	}
	@PutMapping("/updateemail/{accountnumber}")
	public String updateEmail(@PathVariable("accountnumber") String accountnumber,@RequestParam String value ) {
		ALLBANK.updateEmail(accountnumber, value);
		return accountnumber + "'s email was changed to " + value;
	}
	@PutMapping("/updatephonenumber/{accountnumber}")
	public String updatePhonenumber(@PathVariable("accountnumber") String accountnumber,@RequestParam String value ) {
		ALLBANK.updatePhoneNumber(accountnumber, value);
		return accountnumber + "'s phonenumber was changed to " + value;
	}
	@PutMapping("/deposit/{accountnumber}")
	public String updateDeposit(@PathVariable("accountnumber") String accountnumber,@RequestParam double value ) {
		if ((value>=5) &&(value<=10000)) {
			ALLBANK.deposit(accountnumber, value);
			return accountnumber + " has deposited $" + value;			
		}
		return "Deposit fund must be between $5 - $10000";
	}	
	
	@GetMapping("/hello")
	public ArrayListDAO hello() {
		
//		Bank user4 = new Bank("123213114", 5000.50, "Four Pham", "nova4@gmail.com","5103334444");
//		Bank user5 = new Bank("123241225", 75000.50, "Five Pham", "nova5@gmail.com","510333555");
//		Bank user6 = new Bank("121223336", 90000.50, "Six Pham", "nova6@gmail.com","5103336666");
//		
//		ALLBANK.addNewAccount(user4);
//		ALLBANK.addNewAccount(user5);
//		ALLBANK.addNewAccount(user6);
		//ALLBANK.addAlltoDatabase();
		//String displayAPI = ALLBANK.displayall();

		return ALLBANK;
	}
}
