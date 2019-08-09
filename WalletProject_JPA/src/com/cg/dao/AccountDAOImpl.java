package com.cg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.bean.Account;
import com.cg.exception.InsuffecientFundException;

public class AccountDAOImpl implements AccountDAO {

	static Map<Long,Account> accmap = new HashMap<Long,Account>();
	
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
	
	EntityManager em=emf.createEntityManager();
	
	
	

	
	@Override
	public boolean addAccount(Account ob) {
		
		em.getTransaction().begin();
		Account ob1= new Account();
		ob1.setAid(ob.getAid());
		ob1.setAccountholder(ob.getAccountholder());
		ob1.setMobile(ob.getMobile());
		ob1.setBalance(ob.getBalance());
		
		
		em.persist(ob1);
		em.getTransaction().commit();
		System.out.println(" Successfully Added ");
		
		
	    return true;
		
	}

	

	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		
	 
		em.getTransaction().begin();
		em.remove(ob);
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public Account findAccount(Long mobileno) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Account ob=em.find(Account.class,mobileno);
		em.getTransaction().commit();
		return ob;
	}

	@Override
	public Map<Long, Account> getAllAccounts() throws SQLException {
		// TODO Auto-generated method stub
		String qry="select acc from Account acc";
		Query query=em.createQuery(qry);
		List<Account> acclist=query.getResultList();
		
		for(Account ac:acclist)
		{
			accmap.put(ac.getMobile(), ac);
		}
		
		
		
		
		return accmap;
	}
	@Override
	public boolean updateAccount(Account ob) throws SQLException {

        Account s2=em.find(Account.class, ob.getMobile());
        em.getTransaction().begin();
        s2.setBalance(ob.getBalance());
        em.merge(s2);
        em.getTransaction().commit();
        
        
        
        
        
		return true;
	}

	@Override
	public double TransferMoney(Account from, Account to, double amount) throws InsuffecientFundException, SQLException {
		// TODO Auto-generated method stub
		
		
		
		
			
			
			
			
		from.setBalance(from.getBalance()-amount);
		Account s1=em.find(Account.class, from.getMobile());
	    em.getTransaction().begin();
	    s1.setBalance(from.getBalance());
	    em.merge(s1);
	    em.getTransaction().commit();
	        
	        
	     to.setBalance(to.getBalance()+amount);
         Account s2=em.find(Account.class, to.getMobile());
         em.getTransaction().begin();
	     s2.setBalance(to.getBalance());
	        em.merge(s2);
	        em.getTransaction().commit();
	       
		
			
			
			
		return from.getBalance();
	}



	
	

}
