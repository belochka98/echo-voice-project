package authenticationService.mapper;

import authenticationService.component.UserAuthentication;
import authenticationService.entity.UserCredentials;
import dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {
    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "name", source = "userDto.name")
    @Mapping(target = "roles", ignore = true)
    public abstract UserAuthentication apply(UserDto userDto, UserCredentials userCredentials);

    public abstract UserCredentials apply(UserDto userDto, String password);
}
