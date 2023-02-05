package userService.mapper;

import dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import userService.entity.User;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "userAccounts", ignore = true)
    UserDto apply(User source);

    Collection<UserDto> to(Collection<User> source);

    User apply(UserDto user);

    Collection<User> from(Collection<UserDto> source);
}
