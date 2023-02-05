package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserDto {
    private UUID id;

    private boolean active;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate dateBirthday;

    private String phone;

    private boolean sex;

    private Collection<AccountDto> userAccounts;
}
