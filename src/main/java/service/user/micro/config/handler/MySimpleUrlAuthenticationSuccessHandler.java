package service.user.micro.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.store.entities.UserEntitty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    UserDtoFactory userDtoFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(" MySimpleUrlAuthenticationSuccessHandler ");

        UserEntitty authorizedUser = (UserEntitty) authentication.getPrincipal();
        System.out.println(authorizedUser.toString());
        UserDto autUserDto = userDtoFactory.makeProjectDto(authorizedUser);
        response.setCharacterEncoding("UTF-8");
        System.out.println(String.valueOf(autUserDto));
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        response.getWriter().write(objectMapper.writeValueAsString(autUserDto));
        response.setStatus(200);
    }
}
