package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Transactions;
import com.bank.model.Customer;

public class LoginImpl implements Login{

	public void login(Customer customer) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		  try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");
				
				Statement st = connection.createStatement();
				Transactions transactions=new TransactionImpl();
				ResultSet resultSet = st.executeQuery("select * from customerDetails");
				while(resultSet.next())
				{
					if(customer.getAccountNo()==resultSet.getLong(4)&&customer.getPassword().equals(resultSet.getString(8)))
					{
						
						System.out.println("Enter your choice ");
						System.out.println("1. Withdraw");
						System.out.println("2. Deposit");
						System.out.println("3. Exit");
						int ch = sc.nextInt();
						switch(ch)
						{
						case 1:
							System.out.println("Enter amount");
							customer.setAmount(sc.nextLong());
							transactions.withdraw(customer);
							break;
						case 2 :
							System.out.println("Enter amount");
							customer.setAmount(sc.nextLong());
							transactions.deposit(customer);
							break;
						case 3 :
							System.exit(0);
						default:
								System.out.println("Invalid option");
						}
					}
					else {
						System.out.println("Invalid credentials");
						break;
					}
					}
				
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
