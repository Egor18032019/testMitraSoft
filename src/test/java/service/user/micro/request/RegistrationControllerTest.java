package service.user.micro.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.store.repositories.UserRepository;
import service.user.micro.user.JsonUtil;
import service.user.micro.user.UserTestData;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.user.micro.api.utils.Const.REST_URL;
import static service.user.micro.user.UserTestData.user_60001;


public class RegistrationControllerTest extends AbstractUserRestControllerTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void registerNewUser() throws Exception {

        UserDto receivedUser = UserTestData.asUserDto(
                perform(
                        MockMvcRequestBuilders.post(REST_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("username",user_60001.getUsername())
                                .param("password",user_60001.getPassword()))
                        .andExpect(status().isCreated())
                        .andReturn());
        // сравниваем то что хотели и то что в базе ?
        UserDto registeredUserDto = UserDtoFactory.makeProjectDto(
                userRepository.findByUsername(user_60001.getUsername())
                        .orElseGet(null)
                //как тут выдавать ?
        );

        UserTestData.assertEquals(receivedUser, registeredUserDto);
    }
}
