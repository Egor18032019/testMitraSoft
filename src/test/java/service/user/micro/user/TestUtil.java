package service.user.micro.user;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import service.user.micro.store.entities.UserEntitty;

public class TestUtil {

    public static RequestPostProcessor user(UserEntitty user) {
        AuthorizedUser authorizedUser = new AuthorizedUser(user);
        return SecurityMockMvcRequestPostProcessors.user(authorizedUser);
    }
}
