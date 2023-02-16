package authenticationService.repository;

import authenticationService.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findByUserId(UUID userId);

    Optional<UserAccount> findByUserName(String userName);

    Optional<UserAccount> findByUserNameAndPassword(String userName, String password);

    boolean existsByUserNameAndPassword(String userName, String password);
}