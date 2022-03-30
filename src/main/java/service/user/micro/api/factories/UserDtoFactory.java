package service.user.micro.api.factories;

import org.springframework.stereotype.Component;
import service.user.micro.api.dto.UserDto;
import service.user.micro.store.entities.UserEntitty;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoFactory {

    public static UserDto makeProjectDto(UserEntitty entity) {
        System.out.println(" makeProjectDto ");
        System.out.println(entity.toString());
        return UserDto.builder()
                .username(entity.getUsername())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<UserDto> makeUserDtoFromListUserEntity(List<UserEntitty> users) {
        return users.stream().map(UserDtoFactory::makeProjectDto).collect(Collectors.toList());
    }
}
