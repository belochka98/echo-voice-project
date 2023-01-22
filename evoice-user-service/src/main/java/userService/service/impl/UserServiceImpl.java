package userService.service.impl;

import userService.entity.User;
import userService.repository.UserRepository;
import userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Collection<User> saveUsers(Collection<User> users) {
        return users.parallelStream().map(this::saveUser).collect(Collectors.toSet());
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Revisions<Long, User> getRevisions(UUID userId) {
        return userRepository.findRevisions(userId);
    }

    @Override
    public Revision<Long, User> getLastRevision(UUID userId) {
        return userRepository.findLastChangeRevision(userId).orElse(null);
    }
}
