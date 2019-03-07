package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.bank.dao.Register;
import com.bank.driver.Main;
import com.bank.model.Customer;

public class RegisterImpl implements Register {

	public void registration() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Customer customer = new Customer();
		// long aadharCardNo=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");

			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into customerDetails values(?,?,?,?,?,?,?,?,?)");

			System.out.println("Enter firstname");
			customer.setFirstname(sc.next());
			System.out.println("Enter lastname");
			customer.setLastname(sc.next());

			Random r = new Random();
			customer.setAccountNo(Math.abs(r.nextLong()));

			System.out.println("Enter pan no");
			customer.setPanNo(sc.next());
			System.out.println("Enter accounttype");
			customer.setAccountType(sc.next());
			System.out.println("Enter Email");
			customer.setEmail(sc.next());
			System.out.println("Enter password");
			customer.setPassword(sc.next());

			customer.setBalance(0);
			System.out.println("Enter aadhar");
			customer.setAadharCardNo(sc.nextLong());

			if (!validation(customer.getAadharCardNo())) {

				preparedStatement.setString(1, customer.getFirstname());
				preparedStatement.setString(2, customer.getLastname());

				preparedStatement.setLong(4, customer.getAccountNo());
				preparedStatement.setString(5, customer.getPanNo());
				preparedStatement.setString(6, customer.getAccountType());
				preparedStatement.setString(7, customer.getEmail());
				preparedStatement.setString(8, customer.getPassword());
				preparedStatement.setLong(9, customer.getBalance());
				preparedStatement.setLong(3, customer.getAadharCardNo());

				int i = preparedStatement.executeUpdate();

				if (i == 1) {
					System.out.println("registration done");
					System.out.println("Your account number is :" + customer.getAccountNo());
				} else {
					System.out.println("error");
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
		// return aadharCardNo;
	}

	@Override
	public boolean validation(long aadharNo) {
		boolean check = true;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");

			Statement st = connection.createStatement();

			ResultSet resultSet = st.executeQuery("select * from customerDetails");

			while (resultSet.next()) {
				if (aadharNo == resultSet.getLong(3)) {
					System.out.println("Existing customer ");
					System.out.println("Enter the valid details");
					Main m = new Main();
					m.main(null);
				} else {
					check = false;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;

	}

}
