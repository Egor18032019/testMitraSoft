package service.user.micro.user;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import service.user.micro.api.dto.UserDto;
import service.user.micro.store.entities.Role;
import service.user.micro.store.entities.UserEntitty;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final long ADMIN_60000_ID = 60000;
    public static final long USER_60002_ID = 60002;

    public static final UserEntitty user_60000 = new UserEntitty(ADMIN_60000_ID, "qwe", "123", Instant.parse("2022-03-29T05:18:35.955806Z"), Instant.parse("2022-03-29T05:18:35.955806Z"), Role.ADMIN);
    public static final UserEntitty user_60002 = new UserEntitty(USER_60002_ID, "asd", "123", Instant.parse("2022-03-30T07:04:10.710532Z"), Instant.parse("2022-03-30T07:04:10.710532Z"), Role.USER);
    public static final List<UserEntitty> usersListForBDonTest = List.of(user_60000, user_60002);


    public static ResultMatcher jsonListMatcher(
            List<UserDto> expected,
            BiConsumer<List<UserDto>, List<UserDto>> equalsAssertion) {

        return mvcResult -> equalsAssertion.accept(asUserTos(mvcResult), expected);
    }

    public static ResultMatcher jsonMapMatcher(
            String expected,
            BiConsumer<String, String> equalsAssertion
    ) {

          return mvcResult -> {
            String jsonActual = mvcResult.getResponse().getContentAsString();

            equalsAssertion.accept(jsonActual, expected);
        };
    }

    public static List<UserDto> asUserTos(MvcResult mvcResult) throws IOException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        System.out.println("jsonActual");
        System.out.println(jsonActual);
        return JsonUtil.readValues(jsonActual, UserDto.class);
    }

    public static <T> void assertListEquals(List<T> actual, List<T> expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
//        assertThat(actual).usingRecursiveComparison().ignoringFields("id","created_at","updated_at").isEqualTo(expected);
    }

    public static <T> void assertMapEquals(HashMap<String, HashMap<String, String>> actual, HashMap<String, HashMap<String, String>> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static ResultMatcher jsonMatcher(String expected, BiConsumer<String, String> equalsAssertion) {
        return mvcResult -> {
            String jsonActual = mvcResult.getResponse().getContentAsString();
            equalsAssertion.accept(jsonActual, expected);
        };
    }
}
