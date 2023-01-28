package userService.mapper;

import dto.AccountDto;
import dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import userService.entity.User;
import userService.service.AccountService;

import java.util.Collection;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @Mapping(target = "userAccounts", source = "id", qualifiedByName = "fillUserAccounts")
    public abstract UserDto apply(User source);

    public abstract Collection<UserDto> to(Collection<User> source);

    public abstract User apply(UserDto user);

    public abstract Collection<User> from(Collection<UserDto> source);

    @Named("fillUserAccounts")
    Collection<AccountDto> fillUserAccounts(UUID source) {
        return accountMapper.to(accountService.getUserAccounts(source));
    }
}
