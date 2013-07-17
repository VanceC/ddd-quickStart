Feature: Transfer funds from one account to another
 
Scenario: Transferring funds should add money to the To current balance and remove money from the From current balance
Given Account A has a balance of 100 and Account B has a balance of 200
When 10 is transferred from A to B
Then A should have a balance of 10 and B should have a balance of 210 