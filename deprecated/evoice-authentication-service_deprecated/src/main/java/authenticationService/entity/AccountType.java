package authenticationService.entity;

import enm.AccountTypeName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "account_type")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited
public class AccountType {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "max_count_users")
    private String maxCountUsers;

    @Column(name = "account_type_name")
    @Enumerated(STRING)
    private AccountTypeName accountTypeName;

    // ToDo: rework?
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "account_role",
               joinColumns = {@JoinColumn(name = "account_type_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<Role> defaultRoles;
}
