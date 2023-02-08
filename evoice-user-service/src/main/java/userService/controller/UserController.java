package userService.controller;

import client.api.UserApi;
import component.response.ResultResponseFactory;
import dto.UserDto;
import dto.envers.RevisionDto;
import dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import userService.mapper.RevisionMapper;
import userService.mapper.UserMapper;
import userService.service.UserService;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@RequestMapping("/users")
@CrossOrigin(methods = {GET, POST, DELETE})
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final ResultResponseFactory responseFactory;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RevisionMapper revisionMapperDefault;

    @Override
    public ResultResponse<UserDto> getUserById(@PathVariable UUID userId) {
        return responseFactory.createResponseOk(
                userMapper.apply(userService.getUser(userId))
        );
    }

    @Override
    public ResultResponse<Collection<UserDto>> getAllUsers() {
        return responseFactory.createResponseOk(
                userMapper.to(userService.getAllUsers())
        );
    }

    @Override
    public ResultResponse<UserDto> saveUser(@RequestBody UserDto user) {
        return responseFactory.createResponseOk(
                userMapper.apply(userService.saveUser(userMapper.apply(user)))
        );
    }

    @Override
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

    @Override
    public Collection<RevisionDto> getAllRevisions(@PathVariable UUID userId) {
        return revisionMapperDefault.mapRevisions(userService.getRevisions(userId).stream().collect(Collectors.toSet()));
    }

    @Override
    public RevisionDto getLastRevision(@PathVariable UUID userId) {
        return revisionMapperDefault.apply(userService.getLastRevision(userId));
    }
}
