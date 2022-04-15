package service.user.micro.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.user.micro.api.filter.CustomURLFilter;
import service.user.micro.api.service.user.UserService;
import service.user.micro.api.utils.Const;
import service.user.micro.config.handler.CustomAccessDeniedHandler;
import service.user.micro.config.handler.CustomAuthenticationFailureHandler;
import service.user.micro.config.handler.MySimpleUrlAuthenticationSuccessHandler;
import service.user.micro.store.repositories.UserRepository;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final MySimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                // Доступ только для не зарегистрированных пользователей
                .antMatchers(Const.Login_URL).permitAll()
                .antMatchers(Const.REST_URL).not().authenticated()
                // Доступ только для пользователей с ролью ADMIN
                .antMatchers(Const.ADMIN_URL + "/**").hasRole("ADMIN")
                // Доступ только для пользователей с ролью USER
                .antMatchers(Const.USER_URL + "/**").hasRole("USER")
                .antMatchers("/version").permitAll()
                // Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                // Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)

                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(accessDeniedHandler)
                .and()


        ;
    }


    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}