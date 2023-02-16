package authenticationService.repository;

import authenticationService.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {
}