public class Savings extends Account{
	private static final double MIN_BALANCE = 150.0;
	private static final double FEE = 30.0;
	
	public Savings(int number, String owner, double balance, double MIN_BALANCE, double FEE) {
		super(number, owner, balance);
				
	}
	
	public void withdraw(Account a, double amt, String owner) {
		if (a.getOwner().equalsIgnoreCase(owner) == false){
			System.out.println("The owner of account " + a.getNumber() + " could not be verified. The withdrawal could not be completed.");
		}else if(a.getBalance() - amt < 0) {
			System.out.println("The balance of $" + a.getBalance() + " on account " + a.getNumber() + " does not meet the requirements for a $" + amt + " withdrawal. The transaction could not be completed.");
		}else if(a.getBalance() - amt < MIN_BALANCE) {
			a.setBalance(a.getBalance() - FEE);
			a.setBalance(a.getBalance() - amt);
		}else {
			a.setBalance(a.getBalance() - amt);
		}
	}

	public void deposit(Account a, double amt) {
		a.setBalance(a.getBalance() + amt);
	}

	public void close(Account a, String owner) {
		if (a.getBalance() < 0) {
			System.out.println("The balance of $" + a.getBalance() + " on account " + a.getNumber() + " does not meet the requirements to be closed.");
		}else if (a.getOwner().equalsIgnoreCase(owner) == false) {
			System.out.println("The owner of account " + a.getNumber() + " could not be verified. The account can not be closed.");
		}else {
			a.setNumber(0);
		}
	}
	
	public void transfer(Account a, Account b, double amt, String owner) {
		if (a.getOwner().equalsIgnoreCase(owner) == false){
			System.out.println("The owner of account " + a.getNumber() + " could not be verified. The transfer could not be completed.");
		}else if(a.getBalance() - amt < 0) {
			System.out.println("The balance of $" + a.getBalance() + " on account " + a.getNumber() + " does not meet the requirements for a $" + amt + " transfer. This transaction could not be completed.");
		}else if(a.getBalance() - amt < MIN_BALANCE) {
			a.setBalance(a.getBalance() - FEE);
			a.setBalance(a.getBalance() - amt);
			b.setBalance(b.getBalance() + amt);
		}else {
			a.setBalance(a.getBalance() - amt);
			b.setBalance(b.getBalance() + amt);
		}
	}

	public static double getMIN_BALANCE() {
		return MIN_BALANCE;
	}

	public static double getFEE() {
		return FEE;
	}
}
