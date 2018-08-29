package com.qa.app.service.repository;

import com.qa.app.Domain.Account;

import java.util.List;

public interface IAccountRepo {

    String getAccount(Long id);

    String getAllAccounts();

    String createAccount(String accountJson);

    String deleteAccount(Long id);

    String updateAccount(Long id, String newJSON);

}
