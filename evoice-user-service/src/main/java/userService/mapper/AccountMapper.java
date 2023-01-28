package userService.mapper;

import dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import userService.entity.Account;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {
    @Mapping(target = "accountType", source = "source.accountType.name")
    AccountDto apply(Account source);

    Collection<AccountDto> to(Collection<Account> source);

    @Mapping(target = "accountType.id", ignore = true)
    @Mapping(target = "accountType.active", constant = "true")
    @Mapping(target = "accountType.name", source = "accountType")
    Account apply(AccountDto source);

    Collection<Account> from(Collection<AccountDto> source);
}
