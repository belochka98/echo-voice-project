package client.api;

import dto.AccountDto;
import dto.envers.RevisionDto;
import dto.response.ResultResponse;

import java.util.Collection;
import java.util.UUID;

public interface AccountApi {
    ResultResponse<AccountDto> getAccountById(UUID accountId);

    ResultResponse<Collection<AccountDto>> getAllAccounts();

    ResultResponse<AccountDto> saveAccount(AccountDto account);

    void deleteAccount(UUID accountId);

    Collection<RevisionDto> getAllRevisions(UUID accountId);

    RevisionDto getLastRevision(UUID accountId);
}
