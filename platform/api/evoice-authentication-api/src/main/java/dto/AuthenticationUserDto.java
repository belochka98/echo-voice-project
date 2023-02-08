package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import enm.AccountTypeName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationUserDto {
    @NotEmpty
    @Pattern(regexp = "(([А-ЯЁ])(([\\-])|([а-яё]+))[\\-\\s\\.']?[1-9]?[I-X]?){1,4}")
    private String name;

    @NotEmpty
    @Pattern(regexp = "(([А-ЯЁ])(([\\-])|([а-яё]+))[\\-\\s\\.']?[1-9]?[I-X]?){1,4}")
    private String surname;

    @NotEmpty
    @Pattern(regexp = "(([А-ЯЁ])(([\\-])|([а-яё]+))[\\-\\s\\.']?[1-9]?[I-X]?){1,4}")
    private String patronymic;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBirthday;

    @NotEmpty
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    private boolean sex;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @NotNull
    private AccountTypeName accountTypeName;

    @NotEmpty
    private Set<String> userRoles;
}
