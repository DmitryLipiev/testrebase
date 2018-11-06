import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * A user.
 */
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode
public class User implements Serializable {

    @EqualsAndHashCode.Exclude
    @Id
    @Column(name = "user_id")
    private Long id;

}
