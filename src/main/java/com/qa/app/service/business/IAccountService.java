package com.qa.app.service.business;

public interface IAccountService {

    String getAccount(Long id);

    String getAllAccounts();

    String updateAccount(Long id, String accountJSON);

    String deleteAccount(Long id);

    String createAccount(String accountJSON);

}
