public abstract class Account {
	private int number;
	private String owner;
	private double balance;
	
	public Account(int number, String owner, double balance) {
		this.number = number;
		this.owner = owner;
		this.balance = balance;
	}
	
	public abstract void withdraw(Account a, double amt, String owner);
	public abstract void deposit(Account a, double amt);
	public abstract void close(Account a, String owner);
	public abstract void transfer(Account a, Account b, double amt, String owner);

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
