package com.cg.dao;

import java.sql.*;
import java.util.*;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;

public class AccountDAOImpl implements AccountDAO {
	Connection con=JdbcConnection.getConnection();
	static Map<Long, Account> accamp=new HashMap<Long,Account>();

	@Override
	public boolean addAccount(Account ob) throws SQLException {
		// TODO Auto-generated method stub
		accamp.put(ob.getMobile(), ob);
		int id=ob.getAid();
		Long mb=ob.getMobile();
		String ah=ob.getAccountholder();
		double bal=ob.getBalance();
		String sqlQuery="insert into account values(?,?,?,?)";
		
		PreparedStatement st= con.prepareStatement(sqlQuery);
		st.setInt(1,id);
		st.setLong(2,mb);
		st.setString(3,ah);
		st.setDouble(4,bal);
		
		int insertedRec=st.executeUpdate();
		System.out.println("Inserted Records:- "+insertedRec);
		
		con.commit();
		
		return true;
		
	}

	@Override
	public boolean updateAccount(int id,double amount) throws SQLException {
		// TODO Auto-generated method stub
		//accamp.put(ob.getMobile(), ob);
		PreparedStatement updateSt=con.prepareStatement("update account set balance=? where aid=?");
		
		updateSt.setDouble(1,amount);
		updateSt.setInt(2,id);
		int i1=updateSt.executeUpdate();
		System.out.println("Account updated"+i1);
		con.commit();
		
		return true;
	}

	@Override
	public boolean deleteAccount(int id) throws SQLException {
		//accamp.remove(ob);
		PreparedStatement selectSt=con.prepareStatement("delete from account where aid=?");
		selectSt.setInt(1,id);
		ResultSet rs1=selectSt.executeQuery();
		System.out.println("Account deleted");
		
		con.commit();
		con.close();
		
		return true;
	}

	@Override
	public Account findAccount(int id) throws SQLException {
		// TODO Auto-generated method stub
		Account ob=accamp.get(id);
		PreparedStatement selectSt=con.prepareStatement("select * from account where aid=?");
		selectSt.setInt(1,id);
		ResultSet rs1=selectSt.executeQuery();
		while(rs1.next()) {
		double bal=rs1.getDouble(4);
		System.out.println(" Balance:-"+bal);
		System.out.println("================================");
		}
		
		return ob;
	}

	

	@Override
	public Map<Long, Account> getAllAccount() throws SQLException {
		// TODO Auto-generated method stub
		Statement st=con.createStatement();//used to pass sql quries
		
		///to display records..........
		ResultSet rs=st.executeQuery("select * from account");
		while(rs.next()) {
			int a_id=rs.getInt("aid"); //column name or column position............
			long mobile=rs.getLong("mobileno");
			String ah=rs.getString(3);
			double bal=rs.getDouble(4);
			System.out.println("Account id:- "+a_id+", Mobile No:- "+mobile+", Name:- "+ah+", Balance:-"+bal);
			System.out.println("================================");
		}
		
		return accamp;
	}

	@Override
	public boolean TransferMoney(int from, int to, double amount) throws InsuffecientFundException, SQLException {
		// TODO Auto-generated method stub
		double bal=0.0;
		//dao.updateAccount(ob);
		PreparedStatement selectSt=con.prepareStatement("select * from account where aid=?");
		selectSt.setInt(1,from);
		ResultSet rs1=selectSt.executeQuery();
		while(rs1.next()) {
		bal=rs1.getDouble(4);
		bal=bal-amount;
		}
		updateAccount(from,bal);
		
		 selectSt=con.prepareStatement("select * from account where aid=?");
		selectSt.setInt(1,to);
		 rs1=selectSt.executeQuery();
		while(rs1.next()) {
		bal=rs1.getDouble(4);
		bal=bal+amount;
		}
		updateAccount(to,bal);
		
		return true;
	}

	
}
