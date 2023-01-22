package echoVoice.controller;

import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.dto.UserDto;
import echoVoice.dto.envers.RevisionDto;

import java.util.Collection;
import java.util.UUID;

public interface UserController {
    ResultResponse<UserDto> getUserById(UUID userId);

    ResultResponse<Collection<UserDto>> getAllUsers();

    ResultResponse<UserDto> saveUser(UserDto user);

    void deleteUser(UUID userId);

    Collection<RevisionDto> getAllRevisions(UUID userId);

    RevisionDto getLastRevision(UUID userId);

}
