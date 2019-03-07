package com.bank.dao;

import com.bank.model.Customer;

public interface Transactions {

	long withdraw(Customer customer);
	long deposit(Customer customer);
	
}
