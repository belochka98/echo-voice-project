package authenticationService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "action")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Action {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "name")
    private String name;

    @Column(name = "group_name")
    private String groupName;
}
