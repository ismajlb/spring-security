package com.pluralsight.conference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.repository.AccountRepository;
import com.pluralsight.conference.service.AccountService;
import com.pluralsight.conference.util.OnCreateAccountEvent;

@Controller
public class AccountController {
	

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping("account")
	public String getRegistration(@ModelAttribute("account")Account account) {
		return "account";
	}
	
	@PostMapping("account")
	public String add(@ModelAttribute("account")Account account) {
		
		
		// check for errors
		// should verify that the account and the user don't already exist
		// should verify email address
		
		// encrypt password
		account.setPassword(encoder.encode(account.getPassword()));
		
		// create the account 
		account = accountService.create(account);
		
		// fire off an event on creation 
		eventPublisher.publishEvent(new OnCreateAccountEvent(account, "conference"));
		
		return "redirect:account";
	}
	
	@GetMapping("accountConfirm")
	public String confirmAccount(@RequestParam("token") String token) {
		accountService.confirmAccount(token);
		
		return "accountConfirmed";
	}
	
	

    

}
