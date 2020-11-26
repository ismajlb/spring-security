package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AccountRepository {
    public Account create (Account account);

    void saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String token);

    Account findByUsername(String username);

	public void createUserDetails(ConferenceUserDetails userDetails);

	public void createAuthorities(ConferenceUserDetails userDetails);

	public void delete(Account account);

	public void deleteToken(String token);


}
