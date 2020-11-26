package com.pluralsight.conference.service;

import org.springframework.stereotype.Service;

import com.pluralsight.conference.model.Account;

@Service
public interface AccountService {
	
	public Account create(Account account);
	
	void createVerificationToken(Account account, String token);
	
	void confirmAccount(String token);

}
