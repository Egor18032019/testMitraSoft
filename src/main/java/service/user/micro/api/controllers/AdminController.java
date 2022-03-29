package service.user.micro.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.api.utils.Const;
import service.user.micro.store.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping(value = Const.ADMIN_URL)
public class AdminController {

    UserRepository userRepository;
    UserDtoFactory userDtoFactory;

    @GetMapping("/all")
    public List<UserDto> getAllUsersInDB() {
        System.out.println("getAllUsersInDB");
        return userRepository.findAll()
                .stream()
                .map(userDtoFactory::makeProjectDto)
                .collect(Collectors.toList());
    }

}
