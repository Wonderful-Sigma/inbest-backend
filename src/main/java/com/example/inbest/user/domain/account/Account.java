package com.example.inbest.user.domain.account;

import com.example.inbest.user.domain.account.value.AccountId;
import com.example.inbest.user.domain.account.value.HolderId;

public final class Account {

    private final AccountId accountId;
    private final HolderId holderId;

    public Account(AccountId accountId, HolderId holderId) {
        this.accountId = accountId;
        this.holderId = holderId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public HolderId getHolderId() {
        return holderId;
    }
}
