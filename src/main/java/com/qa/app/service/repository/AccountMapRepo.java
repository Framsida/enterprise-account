package com.qa.app.service.repository;

import com.google.gson.Gson;
import com.qa.app.Domain.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Alternative
@ApplicationScoped
public class AccountMapRepo implements IAccountRepo {

    Long counter;


    private Map<Long, Account> map;

    private Gson gson;

    public AccountMapRepo() {
        counter = 0L;
        map = new HashMap<>();
        gson = new Gson();
    }


    @Override
    public String getAccount(Long id) {
        return gson.toJson(map.get(id));
    }

    @Override
    public String getAllAccounts() {
        return gson.toJson(map.values());
    }

    @Override
    public String createAccount(String accountJson) {
        counter++;
        Account account = gson.fromJson(accountJson, Account.class);
        account.setId(counter);
        map.put(counter, account);
        return "{ \"message\": \"Account created successfully\"}";
    }

    @Override
    public String deleteAccount(Long id) {
        map.remove(id);
        return "{ \"message\": \"Account deleted successfully\"}";
    }

    @Override
    public String updateAccount(Long id, String newJSON) {
        Account account = gson.fromJson(newJSON, Account.class);
        map.replace(id, account);
        return "{ \"message\": \"Account updated successfully\"}";
    }
}

