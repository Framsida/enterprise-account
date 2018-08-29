package com.qa.app.service.business;

import com.google.gson.Gson;
import com.qa.app.Domain.Account;
import com.qa.app.service.repository.IAccountRepo;

import javax.inject.Inject;

public class AccountService implements IAccountService {

    private Gson gson = new Gson();

    @Inject
    IAccountRepo accountRepo;

    @Override
    public String getAccount(Long id) {
        return accountRepo.getAccount(id);
    }

    @Override
    public String getAllAccounts() {
        return accountRepo.getAllAccounts();
    }

    @Override
    public String updateAccount(Long id, String accountJSON) {
        return accountRepo.updateAccount(id, accountJSON);
    }

    @Override
    public String deleteAccount(Long id) {
        return accountRepo.deleteAccount(id);
    }

    @Override
    public String createAccount(String accountJSON) {
        if(gson.fromJson(accountJSON, Account.class).getAccountNumber().equals("9999")) {
            return "{\"message\": \"This account is blocked\"}";
        }

        return accountRepo.createAccount(accountJSON);
    }
}
