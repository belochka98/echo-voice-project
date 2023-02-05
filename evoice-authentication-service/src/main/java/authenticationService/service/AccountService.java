package authenticationService.service;

import authenticationService.entity.Account;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.util.Collection;
import java.util.UUID;

public interface AccountService {
    Account getAccount(UUID accountId);

    Collection<Account> getAllAccounts();

    Account saveAccount(Account account);

    Collection<Account> saveAccounts(Collection<Account> accounts);

    void deleteAccount(UUID accountId);

    Collection<Account> getUserAccounts(UUID userId);

    Revisions<Long, Account> getRevisions(UUID accountId);

    Revision<Long, Account> getLastRevision(UUID accountId);
}
