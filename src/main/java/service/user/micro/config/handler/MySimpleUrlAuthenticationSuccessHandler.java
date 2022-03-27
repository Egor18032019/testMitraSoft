package service.user.micro.config.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import service.user.micro.api.dto.UserDto;
import service.user.micro.api.factories.UserDtoFactory;
import service.user.micro.store.entities.UserEntitty;

import javax.servlet.FilterChain;
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
        response.setContentType("application/json;charset=UTF-8");
        System.out.println(String.valueOf(autUserDto));
        response.getWriter().write(String.valueOf(autUserDto));
        response.setStatus(200);
    }
}
