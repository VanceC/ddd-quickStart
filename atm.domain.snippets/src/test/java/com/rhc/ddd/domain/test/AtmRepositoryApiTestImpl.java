package com.rhc.ddd.domain.test;

import java.util.HashMap;
import java.util.Map;

import com.rhc.ddd.domain.Account;
import com.rhc.ddd.domain.AtmRepositoryApi;

public class AtmRepositoryApiTestImpl implements AtmRepositoryApi {

	private Map<String, Account> accountMap = new HashMap<String, Account>();
	
	public void addAccount(Account account) {
		accountMap.put(account.getAccountId(), account);
	}

	@Override
	public Account getAccount(String accountId) {
		return accountMap.get(accountId);
	}

	@Override
	public void update(Account account) {
		addAccount(account);
	}

}
