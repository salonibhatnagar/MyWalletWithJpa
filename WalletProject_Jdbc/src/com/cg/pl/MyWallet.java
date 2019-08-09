package com.cg.pl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

import com.cg.bean.*;
import com.cg.exception.InsuffecientFundException;
import com.cg.service.*;

public class MyWallet {

	public static void main(String[] args) throws IOException, InsuffecientFundException, SQLException {
		// TODO Auto-generated method stub
		AccountService service=new AccountService();
		Map<Long,Account>accmap=new TreeMap<Long,Account>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice="";
		while(true) {
		System.out.println("Menu");
		System.out.println("=====================");
		System.out.println("1 Create new Account");
		System.out.println("2 Print all Account");
		System.out.println("3 Withdraw");
		System.out.println("4 Deposite");
		System.out.println("5 Fund Transfer");
		System.out.println("6 Delete Account");
		System.out.println("7 Exit");
		System.out.println("=====================");
		System.out.println("Enter Your Choice");
		choice=br.readLine();

		
		switch(choice) {
		case"1": int id=0;
				long mb=0L;
				String ah="";
				double bal=0.0;
				//Accepting and validating input for account number.......................
				System.out.println("Enter Account No:-");
				while(true) 
				{
					String s_id=br.readLine();
					boolean ch1=Validator.validatedata(s_id, Validator.aidpattern);
					if(ch1==true)
					{
						try
						{
							id=Integer.parseInt(s_id);
							break;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Account Number must be numeric. Re-Enter");
						}
					}
					else
					{
						System.out.println("Re Enter Account Number in 3 digits");
					}
				}//end of while.................
				//Accepting and validate input for mobile number
				System.out.println("Enter Mobile No:-");
				while(true) 
				{
					String s_mb=br.readLine();
					boolean ch1=Validator.validatedata(s_mb, Validator.mobilepattern);
					if(ch1==true)
					{
						try
						{
							mb=Long.parseLong(s_mb);
							break;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Monile Number must be numeric. Re-Enter");
						}
					}
					else
					{
						System.out.println("Re Enter Mobile Number in 10 digits");
					}
				}//end of while for mobile
				//accepting and validating account holder
				System.out.println("Enter Account Holder Name:-");
				while(true) {
					String s_nm=br.readLine();
					boolean ch2=Validator.validatedata(s_nm,Validator.namepattern);
					if(ch2==true)
					{
						ah=s_nm;
						break;
					}
					else {
						System.out.println("Re-Enter Name. Name should be in formate of Ram Kumar");
					}
				}//holdernamw while end........
				
				
				//accepting and validating account balance
				System.out.println("Enter Balance:-");
				while(true) {
					String s_bal=br.readLine();
					boolean ch3=Validator.validatedata(s_bal, Validator.balancepattern);
					if(ch3==true)
					{
						try
						{
							bal=Double.parseDouble(s_bal);
							break;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Invalid Amount please enter again");
						}
					}
					else
					{
						System.out.println("Re Enter Amount. Should not be less then 1000 and in formate 0000.00");
					}
				}
				
				
				
				Account ob=new Account(id,mb,ah,bal);
				
				//calling addAccount method..........
				boolean b= service.addAccount(ob);
				System.out.println("Account Created");
				
			break;
		case"2"://for displaying all account........
		
			
			
			Map<Long,Account>accamp=service.getAllAccount();
			
			
			break;
		case"3"://for withdraw........
			
			System.out.println("Enter your account no");
			String s_no=br.readLine();
			id=Integer.parseInt(s_no);
			 Account ob1=service.findAccount(id);
			 
			System.out.println("Enter amount to withdraw");
			
			while(true){
				String s_amt=br.readLine();
				bal=Double.parseDouble(s_amt);
			 if(bal>0){
				  boolean bal1=true;
			 try {
				 bal1=service.withdraw(id, bal);
				 break;
			 }
			 catch (InsuffecientFundException e){
				 
				 System.out.println("Invalid Amount.Re Enter Amount.");
				// System.err.println(e.getMessage());
			 }
			 }else{
				 System.out.println("Invalid Amount.Re Enter Amount.");
			 }
			}
			break;
			
		case"4"://for deposite.........
			
			System.out.println("Enter your account no");
			String s_no1=br.readLine();
			id=Integer.parseInt(s_no1);
			 Account ob2=service.findAccount(id);
			 
			System.out.println("Enter amount to deposite");
			
			while(true){
				String s_amt=br.readLine();
				bal=Double.parseDouble(s_amt);
			 if(bal>0){
				  boolean bal1=true;
			
				 bal1=service.deposite(id, bal);
				 break;
			 }else{
				 System.out.println("Invalid Amount.Re Enter Amount.");
			 }
			}
			break;
		case"5"://for fund transfer..........
			System.out.println("Enter account id from which you want to transfer fund");
			String s_id1=br.readLine();
			id=Integer.parseInt(s_id1);
			Account obb=service.findAccount(id);
			
			System.out.println("Enter account id to which you want to transfer fund");
			String s_id2=br.readLine();
			int id1=Integer.parseInt(s_id2);
			 obb=service.findAccount(id1);
			
			System.out.println("Enter Amount");
			String s_bal3=br.readLine();
			
			
			
			while(true){
				String s_amt=br.readLine();
				bal=Double.parseDouble(s_bal3);
			 if(bal>0){
				  boolean bal1=true;
			
				  bal1=service.TransferMoney(id, id1, bal);
				 break;
			 }else{
				 System.out.println("Invalid Amount.Re Enter Amount.");
			 }
			}
			break;
		case"6":
				System.out.println("Enter account number which you want to delet");
				String s_id3=br.readLine();
				int id3=Integer.parseInt(s_id3);
				boolean del=service.deleteAccount(id3);
    break;
		case"7":System.out.println("Exiting Program");
		
				System.exit(0);
		    break;
		default:System.out.println("Invalid Choice");    
		}
		}//end of menu...........
		
		
		
		
		
		
		
	}

}
