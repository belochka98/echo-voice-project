package echoVoice.controller.impl;

import echoVoice.controller.AccountController;
import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.controller.utills.response.ResultResponseFactory;
import echoVoice.dto.AccountDto;
import echoVoice.dto.envers.RevisionDto;
import echoVoice.mapper.AccountMapper;
import echoVoice.mapper.RevisionMapper;
import echoVoice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@RequestMapping("/accounts")
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final ResultResponseFactory responseFactory;
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final RevisionMapper revisionMapper;

    @Override
    @GetMapping("/{accountId}")
    public ResultResponse<AccountDto> getAccountById(@PathVariable UUID accountId) {
        return responseFactory.createResponseOk(
                accountMapper.apply(accountService.getAccount(accountId))
        );
    }

    @Override
    @GetMapping("/all")
    public ResultResponse<Collection<AccountDto>> getAllAccounts() {
        return responseFactory.createResponseOk(
                accountMapper.to(accountService.getAllAccounts())
        );
    }

    @Override
    @PostMapping
    public ResultResponse<AccountDto> saveAccount(@RequestParam AccountDto account) {
        return responseFactory.createResponseOk(
                accountMapper.apply(accountService.saveAccount(accountMapper.apply(account)))
        );
    }

    @Override
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable UUID accountId) {
        accountService.deleteAccount(accountId);
    }

    @Override
    @GetMapping("/revisions/all/{accountId}")
    public Collection<RevisionDto> getAllRevisions(@PathVariable UUID accountId) {
        return revisionMapper.mapRevisions(accountService.getRevisions(accountId).stream().collect(Collectors.toSet()));
    }

    @Override
    @GetMapping("/revisions/last/{accountId}")
    public RevisionDto getLastRevision(@PathVariable UUID accountId) {
        return revisionMapper.apply(accountService.getLastRevision(accountId));
    }
}