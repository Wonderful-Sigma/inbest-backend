package sigma.inbest.domain.account;

import sigma.inbest.domain.account.value.AccountId;
import sigma.inbest.domain.account.value.AccountValue;
import sigma.inbest.domain.account.value.HolderId;

public final class Account {

    private final AccountId accountId;
    private final HolderId holderId;
    private AccountValue accountValue;


    public Account(AccountId accountId, HolderId holderId, AccountValue accountValue) {
        this.accountId = accountId;
        this.holderId = holderId;
        this.accountValue = accountValue;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public HolderId getHolderId() {
        return holderId;
    }

    public AccountValue getAccountValue() {
        return accountValue;
    }
}
