package client.api;

import dto.UserDto;
import dto.envers.RevisionDto;
import dto.response.ResultResponse;

import java.util.Collection;
import java.util.UUID;

public interface UserApi {
    ResultResponse<UserDto> getUserById(UUID userId);

    ResultResponse<Collection<UserDto>> getAllUsers();

    ResultResponse<UserDto> saveUser(UserDto user);

    void deleteUser(UUID userId);

    Collection<RevisionDto> getAllRevisions(UUID userId);

    RevisionDto getLastRevision(UUID userId);
}
