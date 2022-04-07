package service.user.micro.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.user.micro.api.controllers.helpers.ControllerHelper;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.exceptions.BadRequestException;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.api.utils.Const;
import service.user.micro.store.entities.Role;
import service.user.micro.store.entities.UserEntitty;
import service.user.micro.store.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
@RequestMapping(value = Const.REST_URL)
public class RegistrationRestController {

    BCryptPasswordEncoder bCryptPasswordEncoder;
    ControllerHelper controllerHelper;
    UserRepository userRepository;


    @PostMapping()
    public ResponseEntity<UserDto> createUser(
            @RequestParam(value = "username", required = true) Optional<String> optionalUserName,
            @RequestParam(value = "password", required = true) Optional<String> optionalUserPassword) {

        System.out.println("optionalUserName " + optionalUserName);
        System.out.println("optionalUserPassword " + optionalUserPassword);
        optionalUserName = optionalUserName.filter(projectName -> !projectName.trim().isEmpty());
        boolean allFieldHave = optionalUserName.isPresent() && optionalUserPassword.isPresent();
        if (!allFieldHave) {
            throw new BadRequestException("Password or name can't be empty.");
        }

        final String username = optionalUserName.get();
        final String password = bCryptPasswordEncoder.encode(optionalUserPassword.get());
        final Set<Role> roles = Collections.singleton(Role.USER);
        final UserEntitty userForBD = optionalUserName
                .map(controllerHelper::getUserOrThrowException)
                .orElseGet(
                        () ->
                                UserEntitty.builder()
                                        .username(username)
                                        .password(password)
                                        .roles(roles)
                                        .build()
                );


        userRepository.saveAndFlush(userForBD);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoFactory.makeProjectDto(userForBD));

    }


}


