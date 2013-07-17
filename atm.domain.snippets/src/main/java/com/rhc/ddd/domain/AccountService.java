package com.rhc.ddd.domain;

import java.math.BigDecimal;

public interface AccountService {
	
	// The means by which clients obtain accoutIds is outside the scope of the QuickStart.
	
	boolean transferFunds(String accountIdFrom, String accountIdTo, BigDecimal amount);
	
	BigDecimal getBalance(String accountId);

}
