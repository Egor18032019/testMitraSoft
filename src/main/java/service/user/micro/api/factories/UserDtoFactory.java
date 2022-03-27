package service.user.micro.api.factories;

import org.springframework.stereotype.Component;
import service.user.micro.api.dto.UserDto;
import service.user.micro.store.entities.UserEntitty;
@Component
public class UserDtoFactory {

    public UserDto makeProjectDto(UserEntitty entity) {
        System.out.println(" makeProjectDto ");
        return UserDto.builder()
                .username(entity.getUsername())
                .role(entity.getRole())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
