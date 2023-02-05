package dto.envers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.history.RevisionMetadata;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class RevisionFilterDto {
    private Set<String> tableNames;

    private Set<String> entityClassNames;

    private Set<RevisionMetadata.RevisionType> operations;

    private Set<Long> revisionIds;

    private Set<LocalDate> dateStarts;

    private Set<LocalDate> dateEnds;
}
