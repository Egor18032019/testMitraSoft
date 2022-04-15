package service.user.micro.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.factories.UserDtoFactory;

import static service.user.micro.user.UserTestData.user_60001;

public class UserDtoFactoryTest {

    @Test
    void createNewUserDtoFromUserEntity() {
        UserDto result = UserDto
                .builder()
                .username(user_60001.getUsername())
                .role(user_60001.getRole())
                .createdAt(user_60001.getCreatedAt())
                .updatedAt(user_60001.getUpdatedAt())
                .build();
        Assertions.assertEquals(result, UserDtoFactory.makeProjectDto(user_60001));
    }
}
