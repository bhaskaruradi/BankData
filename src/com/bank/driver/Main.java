package com.bank.driver;

import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Register;
import com.bank.dao.Transactions;
import com.bank.daoimpl.LoginImpl;
import com.bank.daoimpl.RegisterImpl;
import com.bank.daoimpl.TransactionImpl;
import com.bank.model.Customer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		Transactions transactions=new TransactionImpl();
		Register register=new RegisterImpl();
		Login login1=new LoginImpl();
		Scanner sc = new Scanner(System.in);
		int i;
		do {
		System.out.println("Enter your choice");
		System.out.println("1. Registration");
		System.out.println("2. Login");
		int ch = sc.nextInt();
            switch(ch)
            {
            case 1 :
            	register.registration();
            	//register.validation(aadhar);
            	break;
            case 2:
            	System.out.println("Enter account num");
            	customer.setAccountNo(sc.nextLong());
            	System.out.println("Enter password");
            	customer.setPassword(sc.next());
            	login1.login(customer);
            	break;
            default:
            		System.exit(0);
            }
            System.out.println("Enter 1 to continue and press any number to exit.");
             i = sc.nextInt();
		} while(i==1);
		System.out.println("Thank you");
	}

}
