package authenticationService.repository;

import authenticationService.entity.envers.RevisionChange;
import dto.envers.RevisionFilterDto;

import java.util.Collection;

public interface RevisionRepository {
    Collection<RevisionChange> findRevisions(RevisionFilterDto filter);
}