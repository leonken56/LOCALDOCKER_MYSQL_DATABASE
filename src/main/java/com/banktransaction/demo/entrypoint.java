package com.banktransaction.demo;
public class entrypoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank user1 = new Bank("123213888", 5000.50, "Four Pham", "nova4@gmail.com","5103334444");
		Bank user2 = new Bank("123245555", 5000.50, "Five Pham", "nova5@gmail.com","510333555");
		Bank user3 = new Bank("121223656", 5000.50, "Six Pham", "nova6@gmail.com","5103336666");
		ArrayListDAO ALLBANK = new ArrayListDAO();
		ALLBANK.addAccounttoDatabase(user1);
		ALLBANK.addAccounttoDatabase(user2);
		ALLBANK.addAccounttoDatabase(user3);
//		ALLBANK.displayall();
		//ALLBANK.addAlltoDatabase();
		ALLBANK.getAllfromDatabase();
	}

}
