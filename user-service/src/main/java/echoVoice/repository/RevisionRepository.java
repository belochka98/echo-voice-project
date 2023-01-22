package echoVoice.repository;

import echoVoice.dto.filter.RevisionFilterDto;
import echoVoice.entity.envers.RevisionChange;

import java.util.Collection;

public interface RevisionRepository {
    Collection<RevisionChange> findRevisions(RevisionFilterDto filter);
}