package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        User admin = new User("admin", "admin1", "1234", "19940116", User.Auth.ROLE_ADMIN, 0, LocalDateTime.now(), LocalDateTime.now());
        User user = new User("user", "user1", "1234", "19940116", User.Auth.ROLE_USER, 0, LocalDateTime.now(), LocalDateTime.now());

        if(userService.getUser("admin") == null){
            userService.saveUser(admin);
        }

        if(userService.getUser("user") == null){
            userService.saveUser(user);
        }

        sce.getServletContext().setAttribute("userService", userService);
    }
}
