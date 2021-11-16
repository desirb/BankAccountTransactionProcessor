public class Checking extends Account{
	private int numCheck;
	private int maxCheck;

	public Checking(int number, String owner, double balance, int numCheck, int maxCheck) {
		super(number, owner, balance);
		this.numCheck = numCheck;
		this.maxCheck = maxCheck;
	}

	public void withdraw(Account a, double amt, String owner) {
		if (((Checking) a).numCheck + 1 > ((Checking) a).maxCheck) {
			System.out.println("Account " + a.getNumber() + " has reached it's maximum check amount of " + ((Checking) a).maxCheck + ". The withdrawal could not be completed.");
		}else if (a.getOwner().equalsIgnoreCase(owner) == false){
			System.out.println("The owner of account " + a.getNumber() + " could not be verified. The withdrawal could not be completed.");
		}else {
		((Checking) a).numCheck +=1;
		a.setBalance(a.getBalance() - amt);
		}
	}

	public void deposit(Account a, double amt) {
		if (((Checking) a).numCheck + 1> ((Checking) a).maxCheck) {
			System.out.println("Account " + a.getNumber() + " has reached it's maximum check amount of " + ((Checking) a).maxCheck + ". The deposit could not be completed.");
		}else {
			((Checking) a).numCheck +=1;
			a.setBalance(a.getBalance() + amt);
		}
	}

	public void close(Account a, String owner) {
		if (a.getBalance() < 0) {
			System.out.println("The balance of $" + a.getBalance() + " on account " + a.getNumber() + " does not meet the requirements to be closed.");
		}else if (a.getOwner().equalsIgnoreCase(owner) == false) {
			System.out.println("The owner of account " + a.getNumber() + " could not be verified. This account can not be closed.");
		}else {
			a.setNumber(0);
		}
	}
	
	public void transfer(Account a, Account b, double amt, String owner) {
		if (((Checking) a).numCheck > ((Checking) a).maxCheck) {
			System.out.println("Account " + a.getNumber() + " has reached it's maximum check amount of " + ((Checking) a).maxCheck + ". The transfer could not be completed.");
		}else if (a.getOwner().equalsIgnoreCase(owner) == false){
			System.out.println("The owner of the account " + a.getNumber() + " could not be verified. The transfer could not be completed.");
		}else {
			((Checking) a).numCheck +=1;
			a.setBalance(a.getBalance() - amt);
			b.setBalance(b.getBalance() + amt);
		}
	}
	

	public int getNumCheck() {
		return numCheck;
	}

	public void setNumCheck(int numCheck) {
		this.numCheck = numCheck;
	}


	public int getMaxCheck() {
		return maxCheck;
	}


	public void setMaxCheck(int maxCheck) {
		this.maxCheck = maxCheck;
	}

}
