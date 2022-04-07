package service.user.micro.request;

import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.user.micro.api.utils.Const.Login_URL;

public class LoginControllerTest extends AbstractUserRestControllerTest{

    @Test
    void postWithRightUser() throws Exception {
        perform(formLogin(Login_URL)
                .user("qwe")
                .password("123"))
                .andExpect(status().isOk());
    }

    @Test
    void postWithWrongUser() throws Exception {
        perform(formLogin(Login_URL)
                .user("qwert")
                .password("12345"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginAndLogout() throws Exception {
        perform(
                formLogin(Login_URL)
                .user("qwe")
                .password("123")
        )
                .andExpect(status().isOk());

                perform(
                        logout()
                )
                .andExpect(status().isFound());
     }
}
