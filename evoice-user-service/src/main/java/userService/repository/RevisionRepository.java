package userService.repository;

import dto.filter.RevisionFilterDto;
import userService.entity.envers.RevisionChange;

import java.util.Collection;

public interface RevisionRepository {
    Collection<RevisionChange> findRevisions(RevisionFilterDto filter);
}