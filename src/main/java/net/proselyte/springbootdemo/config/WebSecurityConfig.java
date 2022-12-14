package net.proselyte.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    private final AuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
//                    .antMatchers("index.html").hasRole("ADMIN")
//                    .antMatchers("userPage.html").hasRole("USER")
                    .antMatchers("/admin/").hasRole("ADMIN")
                    .antMatchers("/user/").hasRole("USER")
                    .antMatchers("/").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin().permitAll()
                    .successHandler(successHandler)
                .and()
                .logout()
                    .permitAll();
    }
}



//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(5);
//    }




//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    UserServiceImpl userServiceImpl;
//    @Autowired
//    SuccessUserHandler successUserHandler;
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() { return new BCryptPasswordEncoder(5); }
//
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        //????????????
//        httpSecurity
//                    .csrf()
//                    .disable()
//                    .authorizeRequests()
//                    //???????????? ???????????????? ???????? ??????????????????????????
//                    .antMatchers("/").permitAll()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                    //?????? ?????????????????? ???????????????? ?????????????? ????????????????????????????
//                    .anyRequest().authenticated()
//                .and()
//                    //?????????????????? ?????? ?????????? ?? ??????????????
//                    .formLogin()
//                    //?????????????????????????????? ???? ???????????????? ?????????? ?????????????????? ?????????? ???????????????? successUserHandler
//                    .successHandler(successUserHandler)
////                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll();
//    }
//
////    @Autowired
////    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userServiceImpl);
////    }
//}

