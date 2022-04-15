package service.user.micro.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.api.utils.Const;
import service.user.micro.store.entities.UserEntitty;
import service.user.micro.store.repositories.UserRepository;
import service.user.micro.user.UserTestData;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.user.micro.user.TestUtil.user;
import static service.user.micro.user.UserTestData.*;


public class AdminControllerTest extends AbstractUserRestControllerTest{
    @Autowired
    private UserRepository userRepository;

    private static final String REST_URL = Const.ADMIN_URL + "/all";

    @Test
    void getAllWithAdminRole() throws Exception {
        List<UserEntitty> usersListForBDonTestAdmin = userRepository.findAll();
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(user(user_60000)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        jsonListMatcher(
                                UserDtoFactory.makeUserDtoFromListUserEntity(usersListForBDonTestAdmin),
                                UserTestData::assertListEquals))
        .andDo(print());
    }

    @Test
    void getAllWithUserRole() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(user(user_60002)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
