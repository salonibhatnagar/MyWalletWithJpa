package com.cg.service;

import java.sql.SQLException;
import java.util.Map;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;

public interface AccountOperation {
	
    public boolean addAccount(Account ob) throws SQLException;
	
	
	
	public boolean deleteAccount(int id) throws SQLException;
	
	public Account findAccount(int id) throws SQLException;
	
	public boolean updateAccount(int id,double amount) throws SQLException;
	
	
	public Map<Long,Account> getAllAccount() throws SQLException;
	public boolean TransferMoney(int from, int to,double amount) throws InsuffecientFundException, SQLException;



	
	

}
