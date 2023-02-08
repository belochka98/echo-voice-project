package userService.mapper;

import dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import userService.entity.User;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto apply(User source);

    Collection<UserDto> to(Collection<User> source);

    User apply(UserDto user);

    Collection<User> from(Collection<UserDto> source);
}
