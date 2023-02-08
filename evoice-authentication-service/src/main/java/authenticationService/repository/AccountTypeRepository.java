package authenticationService.repository;

import authenticationService.entity.AccountType;
import enm.AccountTypeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, UUID>, RevisionRepository<AccountType, UUID, Long> {
    Optional<AccountType> findByAccountTypeName(AccountTypeName accountTypeName);

    Set<AccountType> findAllByAccountTypeNameIn(Set<AccountTypeName> accountTypeNames);
}