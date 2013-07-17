package com.rhc.ddd.domain.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.rhc.ddd.domain.Account;
import com.rhc.ddd.domain.AccountServiceImpl;

public class AtmTest {

	private static final String TO_ACCOUNT_ID = "2000";
	private static final String FROM_ACCOUNT_ID = "1000";

	@Test
	public void atmSuccessTest() {

		Account a1 = new Account(FROM_ACCOUNT_ID, new BigDecimal(1000));
		Account a2 = new Account(TO_ACCOUNT_ID, new BigDecimal(2000));

		AtmRepositoryApiTestImpl testRepository = new AtmRepositoryApiTestImpl();
		testRepository.addAccount(a1);
		testRepository.addAccount(a2);

		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAtmRepository(testRepository);

		boolean result = accountService.transferFunds(FROM_ACCOUNT_ID, TO_ACCOUNT_ID,
				new BigDecimal(50));

		assertEquals(true, result);
		assertEquals(new BigDecimal(950), accountService.getBalance(FROM_ACCOUNT_ID));
		assertEquals(new BigDecimal(2050), accountService.getBalance(TO_ACCOUNT_ID));

	}
}