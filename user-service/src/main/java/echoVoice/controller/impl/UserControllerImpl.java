package echoVoice.controller.impl;

import echoVoice.controller.UserController;
import echoVoice.controller.utills.response.ResultResponse;
import echoVoice.controller.utills.response.ResultResponseFactory;
import echoVoice.dto.UserDto;
import echoVoice.dto.envers.RevisionDto;
import echoVoice.mapper.RevisionMapper;
import echoVoice.mapper.UserMapper;
import echoVoice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserControllerImpl implements UserController {
    private final ResultResponseFactory responseFactory;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RevisionMapper revisionMapper;

    @Override
    @GetMapping("/{userId}")
    public ResultResponse<UserDto> getUserById(@PathVariable UUID userId) {
        return responseFactory.createResponseOk(
                userMapper.apply(userService.getUser(userId))
        );
    }

    @Override
    @GetMapping("/all")
    public ResultResponse<Collection<UserDto>> getAllUsers() {
        return responseFactory.createResponseOk(
                userMapper.to(userService.getAllUsers())
        );
    }

    @Override
    @PostMapping("/save")
    public ResultResponse<UserDto> saveUser(@RequestBody UserDto user) {
        return responseFactory.createResponseOk(
                userMapper.apply(userService.saveUser(userMapper.apply(user)))
        );
    }

    @Override
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }

    @Override
    @GetMapping("/revisions/all/{userId}")
    public Collection<RevisionDto> getAllRevisions(@PathVariable UUID userId) {
        return revisionMapper.mapRevisions(userService.getRevisions(userId).stream().collect(Collectors.toSet()));
    }

    @Override
    @GetMapping("/revisions/last/{userId}")
    public RevisionDto getLastRevision(@PathVariable UUID userId) {
        return revisionMapper.apply(userService.getLastRevision(userId));
    }
}
