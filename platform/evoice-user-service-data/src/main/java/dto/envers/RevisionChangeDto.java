package dto.envers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.history.RevisionMetadata;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RevisionChangeDto {
    private UUID id;

    private String tableName;

    private String entityClassName;

    private String revisionId;

    // ToDo: LocalDate -> LocalDateTime
    private LocalDate revisionDate;

    private RevisionMetadata.RevisionType revisionOperation;
}
