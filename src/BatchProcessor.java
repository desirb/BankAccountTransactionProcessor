/**
 * Britley Desir
 * Fall 2021
 * CMS 270 : Object Oriented Programming
 * 
 * This program is designed to read a text file of a accounts and transactions placed on those accounts formatted in a specific manner. After processing a month's
 * worth of transactions the program will update the account file after computing said transactions.
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class BatchProcessor {

	public BatchProcessor() {
		
	}
	
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	
	static Account processDeposit(Account a, double Amt) {
		a.deposit(a, Amt);
		return a;
	}
	
	static Account processWithdrawal(Account a, double Amt, String owner) {
		a.withdraw(a, Amt, owner);
		return a;
	}
	
	static Account processClose(Account a, String owner) {
		a.close(a, owner);
		return a;
	}
	
	static void processTransfer(Account a, Account b, double Amt, String owner) {
		a.transfer(a, b, Amt, owner);
	}
	
	
	public static void main (String[] args) {
		try {
			File acctObj = new File("accounts.txt");
			Scanner acctReader = new Scanner(acctObj);
			
			while(acctReader.hasNextLine()) {
				String line = acctReader.nextLine();
				String[] words = line.split(" ");
				
				for (int i=0; i < words.length; i++) {
					if(words[i].equals("C")) {
						Account c = new Checking(Integer.parseInt(words[0]), words[2]+" "+words[3], Double.parseDouble(words[4]), Integer.parseInt(words[5]), Integer.parseInt(words[6]));
						accounts.add(c);
					}
				}
				for (int j=0; j < 5; j++) {
					if(words[j].equals("S")) {
						Account s = new Savings(Integer.parseInt(words[0]), words[2]+" "+words[3], Double.parseDouble(words[4]), Savings.getMIN_BALANCE(), Savings.getFEE());
						accounts.add(s);
					}
				}
			}
			acctReader.close();
		}catch (FileNotFoundException e) {
			System.out.print("An error occured.");
			e.printStackTrace();
		}
		
		try {
			File batchObj = new File ("batch.txt");
			Scanner batchReader = new Scanner(batchObj);
			
			while (batchReader.hasNextLine()) {
				String line = batchReader.nextLine();
				String[] words = line.split(" ");
				
				Iterator<Account> itr = accounts.iterator();
				while(itr.hasNext()) {
					Account d = itr.next();
					if(words[0].equals("D") && Integer.parseInt(words[1]) == d.getNumber()) {
						processDeposit(d, Double.parseDouble(words[2]));
					}
					if(words[0].equals("W") && Integer.parseInt(words[1]) == d.getNumber()) {
						processWithdrawal(d, Double.parseDouble(words[2]), words[3] + " " + words[4]);
					}
					if(words[0].equals("T") && Integer.parseInt(words[1]) == d.getNumber()) {
						for(Account t : accounts) {
							if(t.getNumber() == Integer.parseInt(words[2])) {
							processTransfer(d, t, Double.parseDouble(words[3]), words[4] + " " + words[5]);
							}
						}
					}
					if(words[0].equals("C") && Integer.parseInt(words[1]) == d.getNumber()) {
						processClose(d, words[2] + " " + words[3]);
						if(d.getNumber() == 0) {
							itr.remove();
						}
					}
				}
			}
			batchReader.close();
		}catch (FileNotFoundException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}

		
			try {
				FileWriter output = new FileWriter("output.txt");
				for(Account d : accounts) {
					if(d instanceof Checking && d == accounts.get(accounts.size()-1)) {
						output.write(d.getNumber() + " C " + d.getOwner() + " " + d.getBalance() + " " + ((Checking) d).getNumCheck() + " " + ((Checking) d).getMaxCheck());
					}else if (d instanceof Savings && d == accounts.get(accounts.size()-1)) {
						output.write(d.getNumber() + " S " + d.getOwner() + " " + d.getBalance());
					}else if(d instanceof Savings) {
						output.write(d.getNumber() + " S " + d.getOwner() + " " + d.getBalance() + "\n");
					}else {
						output.write(d.getNumber() + " C " + d.getOwner() + " " + d.getBalance() + " " + ((Checking) d).getNumCheck() + " " + ((Checking) d).getMaxCheck() + "\n");
					}
				}
				//output.write(accounts.toString());
				output.close();
			}catch(IOException e) {
				System.out.println("An error has occured.");
				e.printStackTrace();
			}
	}
}
