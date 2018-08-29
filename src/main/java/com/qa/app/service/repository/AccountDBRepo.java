package com.qa.app.service.repository;

import com.google.gson.Gson;
import com.qa.app.Domain.Account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class AccountDBRepo {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private Gson gson;

    @Transactional(REQUIRED)
    public String getAccount(Long id) {
        return gson.toJson(findAccount(id));
    }

    @Transactional(REQUIRED)
    public String getAllAccounts() {
        return gson.toJson(findAllAccounts());
    }

    @Transactional(REQUIRED)
    public String createAccount(String accountJSON) {
        Account newAccount = gson.fromJson(accountJSON, Account.class);
        manager.persist(newAccount);
        return "{ message: \"Account created successfully\"}";
    }

    @Transactional(REQUIRED)
    public String deleteAccount(Long id) {
        Account accountToDelete = findAccount(id);
        if(accountToDelete !=null) {
            manager.remove(accountToDelete);
        }
        return "{ message: \"Account deleted successfully\"}";
    }

    @Transactional(REQUIRED)
    public String updateAccount(Long id, String newJSON) {
        Account accountNew = gson.fromJson(newJSON, Account.class);
        Account accountToEdit = findAccount(id);
        if(accountNew != null) {
            accountToEdit = accountNew;
            manager.merge(accountToEdit);
        }
        return "{ message: \"Account updated successfully\"}";
    }

    private List<Account> findAllAccounts() {
        return manager.createQuery("Select t from Account t", Account.class).getResultList();
    }

    private Account findAccount(Long id) {
        return manager.find(Account.class, id);
    }

}
