package com.cg.service;
import java.sql.SQLException;

import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;

public interface Transaction extends AccountOperation{
	
	public boolean withdraw(int id,double amount) throws InsuffecientFundException, SQLException;
	
	public boolean deposite(int id,double amount) throws SQLException;
	
	public boolean TransferMoney(int from, int to,double amount) throws InsuffecientFundException, SQLException; 
	
	public default void printStatement(Account ob)
	{
		System.out.println("========================");
		System.out.println("Statement for Account No:- " + ob.getAid());
		System.out.println("Account holder name:- " + ob.getAccountholder());
		System.out.println("Mobile no:- " + ob.getMobile());
		System.out.println("Balanceis:- " + ob.getBalance());
		System.out.println("========================");
	}

}
