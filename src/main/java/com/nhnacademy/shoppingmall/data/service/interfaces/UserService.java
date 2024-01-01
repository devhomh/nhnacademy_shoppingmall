package com.nhnacademy.shoppingmall.data.service.interfaces;

import com.nhnacademy.shoppingmall.data.domain.User;

public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

}
