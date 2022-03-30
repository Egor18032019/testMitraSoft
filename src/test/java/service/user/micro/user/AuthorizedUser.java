package service.user.micro.user;

import org.springframework.security.core.userdetails.User;
import service.user.micro.store.entities.UserEntitty;

public class AuthorizedUser extends User {

    public AuthorizedUser(UserEntitty user) {
        super(user.getUsername(), user.getPassword(),
                user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(),
                user.getRoles()
        );
    }
}

