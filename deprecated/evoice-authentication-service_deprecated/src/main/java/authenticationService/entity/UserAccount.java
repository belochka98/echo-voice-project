package authenticationService.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Audited
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    // ToDo: rework?
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "account_type_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AccountType accountType;

    @Column(name = "active")
    private boolean active;

    // ToDo: rework?
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "user_account_role",
               joinColumns = {@JoinColumn(name = "user_account_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<Role> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream().flatMap(r -> r.getActions().stream()).map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
