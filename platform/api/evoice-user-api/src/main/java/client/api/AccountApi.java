package client.api;

import dto.AccountDto;
import dto.envers.RevisionDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.UUID;

public interface AccountApi {
    @GetMapping("/{accountId}")
    ResultResponse<AccountDto> getAccountById(@PathVariable("accountId") UUID accountId);

    @GetMapping("/all")
    ResultResponse<Collection<AccountDto>> getAllAccounts();

    @PostMapping
    ResultResponse<AccountDto> saveAccount(@RequestParam AccountDto account);

    @DeleteMapping("/{accountId}")
    void deleteAccount(@PathVariable("accountId") UUID accountId);

    @GetMapping("/revisions/all/{accountId}")
    Collection<RevisionDto> getAllRevisions(@PathVariable("accountId") UUID accountId);

    @GetMapping("/revisions/last/{accountId}")
    RevisionDto getLastRevision(@PathVariable("accountId") UUID accountId);
}
