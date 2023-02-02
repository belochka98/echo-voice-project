package client.api;

import dto.UserDto;
import dto.envers.RevisionDto;
import dto.response.ResultResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.UUID;

public interface UserApi {
    @GetMapping("/{userId}")
    ResultResponse<UserDto> getUserById(@PathVariable("userId") UUID userId);

    @GetMapping("/all")
    ResultResponse<Collection<UserDto>> getAllUsers();

    @PostMapping("/save")
    ResultResponse<UserDto> saveUser(@RequestBody UserDto user);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable("userId") UUID userId);


    @GetMapping("/revisions/all/{userId}")
    Collection<RevisionDto> getAllRevisions(@PathVariable("userId") UUID userId);

    @GetMapping("/revisions/last/{userId}")
    RevisionDto getLastRevision(@PathVariable("userId") UUID userId);
}
