package service.user.micro.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.user.micro.api.dto.UserDto;

import java.time.Instant;

public class UserDtoTest {
    public static final String username = "qwerty";
    public static final String role = "USER";
    public static final Instant createdAt = Instant.now();
    public static final Instant updatedAt = Instant.now();

    @Test
    void createNewUserDtoObject() {

        UserDto result = UserDto
                .builder()
                .username(username)
                .role(role)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getUsername());
        Assertions.assertNotNull(result.getRole());

        Assertions.assertSame(result.getCreatedAt(),createdAt);
        Assertions.assertSame(result.getUpdatedAt(),updatedAt);

        Assertions.assertEquals(result.getUsername(), username);
        Assertions.assertEquals(result.getRole(), role);

    }
}
