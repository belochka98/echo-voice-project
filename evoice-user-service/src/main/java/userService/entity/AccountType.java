package userService.entity;

import userService.entity.enums.AccountTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "account_type")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccountType {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "name")
    @Enumerated(STRING)
    private AccountTypeName name;
}
