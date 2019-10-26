# project-2-ATM
Week 2 project: ATM Simulator

## ATM Requirements

Every feature must have corresponding unit tests
Tests should demonstrate proper behavior, and proper handling of misuse (eg. attempts to deposit/transfer/withdraw negative amounts

- com.zipcodewilmington.User interface: CLI (Command line interface) Only
  - Direct Input
  - Numbered options (instead of on-screen buttons)
  - ASCII art welcome but not required
- Must support account types:
  - com.zipcodewilmington.Checking
  - com.zipcodewilmington.Savings
  - Investment
- com.zipcodewilmington.Account Actions
  - Withdraw from acct
  - Deposit to acct
  - Transfer across accounts (self)
  - Open new account
  - Close account (must be empty)
  - Print transaction history
  - Check balance
  - **Challenge:** Transfer to another user's account (but not from)
- Support multiple users
  - Users have associated accounts
  - Can create new user
  - Users are authenticated with a password (generated or provided on user creation)
  - Can exit a user and enter another user
- **BONUS** Persistence
  - Users and accounts remain persistent
  - Opportunity for research


Recommended:
Create a `com.zipcodewilmington.Console` class that manages console interactions.
Create a `ConsoleMock` for testing (provide scripted user input using this object).

## What's next?
The next lab is located [here](https://github.com/Zipcoder/ZCW-MesoLabs-OOP-BankAccountManager).

