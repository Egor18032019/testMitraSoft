package service.user.micro.request;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.user.micro.api.utils.Const;
import service.user.micro.user.JsonUtil;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static service.user.micro.user.TestUtil.user;
import static service.user.micro.user.UserTestData.*;

public class UserControllerTest extends AbstractUserRestControllerTest {

    private static final String REST_URL = Const.USER_URL + "/news";

    @Test
    void getNewsAdminRole() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(user(user_60000)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getNewsUserRole() throws Exception {
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        HashMap<String, String> news = new HashMap<>();
        news.put("10.10.10", "Внимание жильцы. Сверка показаний счетчиков будет проходить с 00 по **.Просьба быть дома");
        news.put("11.11.21", "Уважаемы жители дома ХХ. Большая просьба быть дома и пускать газовую службу");

        map.put("message", news);
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(user(user_60002)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonMatcher(
                        JsonUtil.convertJson(map),
                        (actual, expected) -> assertThat(actual).isEqualTo(expected)
                ));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
