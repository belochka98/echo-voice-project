package echoVoice.mapper;

import echoVoice.dto.AccountDto;
import echoVoice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {
    AccountDto apply(Account account);

    Collection<AccountDto> to(Collection<Account> account);

    Account apply(AccountDto account);

    Collection<Account> from(Collection<AccountDto> account);
}
