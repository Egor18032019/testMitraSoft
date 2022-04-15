//package service.user.micro.api.controllers.helpers;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Component;
//import service.user.micro.api.exceptions.BadRequestException;
//import service.user.micro.api.exceptions.NotFoundException;
//import service.user.micro.store.entities.UserEntitty;
//import service.user.micro.store.repositories.UserRepository;
//
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Component
//@Transactional
//public class ControllerHelper {
//
//    UserRepository userRepository;
//
//    public UserEntitty getUserOrThrowException(Long projectId) {
//
//        return userRepository
//                .findById(projectId)
//                .orElseThrow(() ->
//                        new NotFoundException(
//                                String.format(
//                                        "Project with \"%s\" doesn't exist.",
//                                        projectId
//                                )
//                        )
//                );
//    }
//
//
//    public UserEntitty getUserOrThrowException(String username) {
//        Optional<UserEntitty> savedUser = userRepository.findByUsername(username);
//        boolean isUserHave = savedUser.isPresent();
//        if (isUserHave) {
//            throw new BadRequestException(
//                    String.format(
//                            "User with \"%s\" doesn't exist.",
//                            username
//                    ));
//        }
//
//        return null;
//
//    }
//}