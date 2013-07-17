package com.rhc.ddd.domain;

public interface AtmRepositoryApi {
	
	Account getAccount(String accountId);
	
	void update(Account account);

}
