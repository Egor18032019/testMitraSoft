package service.user.micro.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)//все поля приватные
@Entity
@Table(name = "users")
public class UserEntitty implements UserDetails {

    public UserEntitty() {

    }

    public UserEntitty(Long id, String username, @NonNull String password, Instant updatedAt, Instant createdAt, Role role, Role... roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.roles =  EnumSet.of(role, roles);
    }
    public UserEntitty(Long id, String username, @NonNull String password, Instant updatedAt, Instant createdAt, @NonNull Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.roles = roles;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //везде уникальные
            Long id;

    @Column(unique = true)
    String username;

    @NonNull
    @Column
    private String password;

    @Builder.Default
    Instant updatedAt = Instant.now();

    @Builder.Default
    Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "user_id", "role" }, name = "uk_user_roles") }
    )
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinColumn(name = "user_id") // https://stackoverflow.com/a/62848296/548473
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy
    @NonNull
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getRole (){

        return getRoles()
                .stream()
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "UserEntitty{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", roles=" + roles +
                '}';
    }
}
