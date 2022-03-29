package service.user.micro.api.service.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.user.micro.api.exceptions.NotFoundException;
import service.user.micro.store.entities.UserEntitty;
import service.user.micro.store.repositories.UserRepository;

import java.util.Optional;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntitty> user = userRepository.findByUsername(username);
        boolean isUserHave = user.isPresent();
        if (!isUserHave) {
            throw new NotFoundException("User no found ..");
        } else {
             return user.get();
        }
    }
}
