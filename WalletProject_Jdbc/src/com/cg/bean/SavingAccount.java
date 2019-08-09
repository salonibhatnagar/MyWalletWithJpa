package com.cg.bean;

public class SavingAccount extends Account{
	
	private double intrest;
	private final double Min_balance=1000.00;
	
	public SavingAccount() {
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(int aid, int mobile, String accountholder, double balance) {
		super(aid, mobile, accountholder, balance);
		// TODO Auto-generated constructor stub
	}

	public double getIntrest() {
		return intrest;
	}

	public void setIntrest(double intrest) {
		this.intrest = super.getBalance()*0.04;
	}

	@Override
	public String toString() {
		return super.toString()+ "SavingAccount [intrest=" + intrest + "]";
	}

	
	
	
	
	
	

}
