package echoVoice.controller;

import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.dto.AccountDto;
import echoVoice.dto.envers.RevisionDto;

import java.util.Collection;
import java.util.UUID;

public interface AccountController {
    ResultResponse<AccountDto> getAccountById(UUID accountId);

    ResultResponse<Collection<AccountDto>> getAllAccounts();

    ResultResponse<AccountDto> saveAccount(AccountDto account);

    void deleteAccount(UUID accountId);

    Collection<RevisionDto> getAllRevisions(UUID accountId);

    RevisionDto getLastRevision(UUID accountId);
}
