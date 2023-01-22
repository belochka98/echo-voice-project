package userService.service.impl;

import userService.entity.Account;
import userService.entity.UserAccount;
import userService.repository.AccountRepository;
import userService.repository.UserAccountRepository;
import userService.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public Account getAccount(UUID accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public Collection<Account> saveAccounts(Collection<Account> accounts) {
        return accounts.parallelStream().map(this::saveAccount).collect(Collectors.toSet());
    }

    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public Collection<Account> getUserAccounts(UUID userId) {
        return userAccountRepository.findAllByUserId(userId).parallelStream().map(UserAccount::getAccount).collect(Collectors.toSet());
    }

    @Override
    public Revisions<Long, Account> getRevisions(UUID accountId) {
        return accountRepository.findRevisions(accountId);
    }

    @Override
    public Revision<Long, Account> getLastRevision(UUID accountId) {
        return accountRepository.findLastChangeRevision(accountId).orElse(null);
    }
}
