package service.user.micro.api.filter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import service.user.micro.api.exceptions.BadRequestException;
import service.user.micro.api.utils.Const;
import service.user.micro.store.entities.UserEntitty;
import service.user.micro.store.repositories.UserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class CustomURLFilter implements Filter {
    UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(" ");
        System.out.println(
                "Logging Request " + req.getMethod() + " : " + req.getRequestURI() +
                        " -> " + request.getParameter("username"));
        boolean isForRegistrationFilter = req.getMethod().equals("POST") && req.getRequestURI().equals(Const.REST_URL);
        if (isForRegistrationFilter) {
            String username = request.getParameter("username");
            Optional<UserEntitty> savedUser = userRepository.findByUsername(username);
            boolean isUserHave = savedUser.isPresent();
            if (isUserHave) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        chain.doFilter(request, response);
        System.out.println(
                "Logging Response : " +
                        res.getContentType());


    }


}