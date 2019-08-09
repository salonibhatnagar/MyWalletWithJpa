package com.cg.dao;

import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;

import java.sql.SQLException;
import java.util.*;

public interface AccountDAO {
	
	public boolean addAccount(Account ob) throws SQLException;
	
	public boolean updateAccount(int id, double amount) throws SQLException;
	
	public boolean deleteAccount(int id) throws SQLException;
	
	public Account findAccount(int id) throws SQLException;
	
	
	
	public Map<Long,Account> getAllAccount() throws SQLException;
	public boolean TransferMoney(int from, int to,double amount) throws InsuffecientFundException, SQLException;
	
	

}
