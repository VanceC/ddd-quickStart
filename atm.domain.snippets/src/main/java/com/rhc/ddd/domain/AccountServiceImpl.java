package com.rhc.ddd.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.rhc.drools.reference.ClasspathKnowledgeBaseBuilder;

public class AccountServiceImpl implements AccountService {

	private AtmRepositoryApi atmRepository;

	public AtmRepositoryApi getAtmRepository() {
		return atmRepository;
	}

	public void setAtmRepository(AtmRepositoryApi atmRepository) {
		this.atmRepository = atmRepository;
	}

	@Override
	public boolean transferFunds(String accountIdFrom, String accountIdTo,
			BigDecimal amount) {
		Account fromAccount = atmRepository.getAccount(accountIdFrom);
		Account toAccount = atmRepository.getAccount(accountIdTo);
		TransferRequest tr = new TransferRequest(accountIdFrom, accountIdTo,
				amount);
		StatelessKnowledgeSession kSession;
		ClasspathKnowledgeBaseBuilder kBuilder = new ClasspathKnowledgeBaseBuilder();
		kBuilder.addKnowledgeResource("atm.drl");
		KnowledgeBase kBase = kBuilder.getKnowledgeBase();
		kSession = kBase.newStatelessKnowledgeSession();
		List<Command<?>> commands = new ArrayList<Command<?>>();
		commands.add(CommandFactory.newInsert(fromAccount));
		commands.add(CommandFactory.newInsert(toAccount));
		commands.add(CommandFactory.newInsert(tr));
		commands.add(CommandFactory.newFireAllRules());
		kSession.execute(CommandFactory.newBatchExecution(commands));
		return tr.isSuccess();
	}

	@Override
	public BigDecimal getBalance(String accountId) {
		Account account = atmRepository.getAccount(accountId);
		if (account != null) {
			return account.getBalance();
		} else {
			return null;
		}
	}
}
