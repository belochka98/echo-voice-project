package authenticationService.repository.impl;

import authenticationService.entity.envers.RevisionChange;
import authenticationService.repository.RevisionRepository;
import authenticationService.repository.utills.RepositoryUtills;
import dto.envers.RevisionFilterDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Repository
public class RevisionRepositoryImpl implements RevisionRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final Class<RevisionChange> entity = RevisionChange.class;

    @Override
    public Collection<RevisionChange> findRevisions(RevisionFilterDto filter) {
        return Objects.isNull(filter) || CollectionUtils.isEmpty(filter.getTableNames()) || CollectionUtils.isEmpty(filter.getTableNames())
               ? this.getRevisionsByHQL(filter)
               : this.getRevisionsByReader(filter);
    }

    private Collection<RevisionChange> getRevisionsByReader(RevisionFilterDto filter) {
        final var reader = AuditReaderFactory.get(entityManager);

        return null;
    }

    private Collection<RevisionChange> getRevisionsByHQL(RevisionFilterDto filter) {
        final Set<String> expressions = Objects.isNull(filter)
                                        ? Collections.emptySet()
                                        : Set.of(
                CollectionUtils.isEmpty(filter.getOperations()) ? "" : "(object.operation in (:operations))"
        );
        final var sqlSelect = String.format(
                "select object from %s object %s",
                entity.getSimpleName(),
                CollectionUtils.isEmpty(expressions) ? "" : "where " + String.join(" and ", expressions)
        );

        final var query = entityManager.createQuery(sqlSelect, entity);
        RepositoryUtills.setQueryParametersByFilter(query, filter);

        return query.getResultList();
    }
}
