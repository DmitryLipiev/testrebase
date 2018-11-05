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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private Person person;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "attempts", nullable = false)
    private Long attempts = 0L;

    @ToString.Exclude
    @Column(name = "pwd",)
    @NotAudited
    private byte[] password;

    @Column(name = "pwd_expired")
    @NotAudited
    private LocalDate passwordExpirationDate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "alt_email")
    private String alternativeEmail;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    @NotAudited
    private Set<SecurityAnswer> securityAnswers;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_login_datetime")
    @NotAudited
    private LocalDateTime lastLoginDateTime;

    @Column(name = "last_reg_email")
    @NotAudited
    private LocalDateTime lastRegistrationEmail;

    @Column(name = "real_user")
    @NotAudited
    private Boolean realUser;

    @Transient
    public boolean isHistoric() {
        return this.getStatus() == UserStatus.HISTORIC;
    }

    @Transient
    public boolean isPasswordExpired() {
        return LocalDate.now().isAfter(passwordExpirationDate);
    }

    @Transient
    public boolean isActive() {
        return this.getStatus() == UserStatus.ACTIVE;
    }

    @Transient
    public boolean isAdmin() {
        return this.getRole() == UserRole.ADMINISTRATOR;
    }

    @Transient
    public boolean isApiUser() {
        return this.getRole() == UserRole.API_USER;
    }

}
