package dto.envers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.history.RevisionMetadata;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RevisionDto<T> {
    private long id;

    private RevisionMetadata.RevisionType operation;

    // ToDo: LocalDate -> LocalDateTime
    private LocalDate date;

    private String userName;

    private T object;
}
