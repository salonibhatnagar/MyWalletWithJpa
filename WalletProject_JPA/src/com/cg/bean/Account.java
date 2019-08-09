package com.cg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="walletaccount")
public  class Account implements Comparable <Account>{
	
	@Column(length=20)
	private int aid;
	@Id
	@Column(length=10)
	private long mobile;
	@Column(length=20)
	private String accountholder;
	@Column(length=20)
	private double balance;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(int aid, long mobile, String accountholder, double balance) {
		super();
		this.aid = aid;
		this.mobile = mobile;
		this.accountholder = accountholder;
		this.balance = balance;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAccountholder() {
		return accountholder;
	}
	public void setAccountholder(String accountholder) {
		this.accountholder = accountholder;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", mobile=" + mobile + ", accountholder=" + accountholder + ", balance="
				+ balance + "]";
	}
	@Override
	public int compareTo(Account arg0) {
		// default sorting based on account number
		
		int diff=this.getAid()-arg0.getAid();
		if(diff>0)
			return 1;
		else if(diff<0)
			return -1;
		else 
			return 0;
		
	}
	
	
	

}
