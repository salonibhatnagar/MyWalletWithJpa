package com.cg.service;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


import com.cg.bean.*;
import com.cg.dao.*;

import com.cg.exception.InsuffecientFundException;

public class AccountService implements Gst,Transaction {
	Connection con=JdbcConnection.getConnection();
	AccountDAO dao=new AccountDAOImpl();

	@Override
	public boolean withdraw(int id, double amount) throws InsuffecientFundException, SQLException {
		double bal=0.0;
		//dao.updateAccount(ob);
		PreparedStatement selectSt=con.prepareStatement("select * from account where aid=?");
		selectSt.setInt(1,id);
		ResultSet rs1=selectSt.executeQuery();
		while(rs1.next()) {
		bal=rs1.getDouble(4);
		bal=bal-amount;
		}
		return dao.updateAccount(id,bal);
		
	}

	@Override
	public boolean deposite(int id, double amount) throws SQLException {
		double bal=0.0;
		//dao.updateAccount(ob);
		PreparedStatement selectSt=con.prepareStatement("select * from account where aid=?");
		selectSt.setInt(1,id);
		ResultSet rs1=selectSt.executeQuery();
		while(rs1.next()) {
		bal=rs1.getDouble(4);
		bal=bal+amount;
		}
		return dao.updateAccount(id,bal);
	}

	

	@Override
	public double calculateTax(double PCT, double amount) {
		// TODO Auto-generated method stub
		return amount*Gst.PCT_5;
	}

	@Override
	public boolean addAccount(Account ob) throws SQLException {
		// TODO Auto-generated method stub\
		
		return dao.addAccount(ob);
	}
	

	@Override
	public boolean deleteAccount(int id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.deleteAccount(id);
	}

	

	@Override
	public Map<Long, Account> getAllAccount() throws SQLException {
		// TODO Auto-generated method stub
		return dao.getAllAccount();
	}

	@Override
	public boolean updateAccount(int id,double amount) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updateAccount(id,amount);
	}

	@Override
	public boolean TransferMoney(int from, int to, double amount) throws InsuffecientFundException, SQLException {
		// TODO Auto-generated method stub
		return dao.TransferMoney(from, to, amount);
	}

	@Override
	public Account findAccount(int id) throws SQLException {
		// TODO Auto-generated method stub
		return dao.findAccount(id);
	}

	
	
	
	

}
