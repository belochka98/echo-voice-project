package authenticationService.repository;

import authenticationService.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    Collection<UserAccount> findAllByUserCredentialsId(UUID userCredentialsId);
}