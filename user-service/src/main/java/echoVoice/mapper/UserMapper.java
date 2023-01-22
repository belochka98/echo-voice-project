package echoVoice.mapper;

import echoVoice.dto.AccountDto;
import echoVoice.dto.UserDto;
import echoVoice.entity.User;
import echoVoice.service.AccountService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @Mapping(target = "userAccounts", source = "id", qualifiedByName = "fillUserAccounts")
    public abstract UserDto apply(User user);

    public abstract Collection<UserDto> to(Collection<User> users);

    public abstract User apply(UserDto user);

    public abstract Collection<User> from(Collection<UserDto> user);

    @Named("fillUserAccounts")
    Collection<AccountDto> fillUserAccounts(UUID userId) {
        return accountMapper.to(accountService.getUserAccounts(userId));
    }
}
