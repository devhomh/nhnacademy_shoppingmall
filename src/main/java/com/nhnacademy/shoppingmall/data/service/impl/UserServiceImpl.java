package com.nhnacademy.shoppingmall.data.service.impl;

import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.data.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.data.service.interfaces.UserService;
import com.nhnacademy.shoppingmall.data.domain.User;
import com.nhnacademy.shoppingmall.data.repository.interfaces.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId){
        if(userId == null){
            throw new NullPointerException("ID 값이 없습니다.");
        }
        //todo#4-1 회원조회
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if(user == null){
            throw new DomainNullPointerException(user);
        }
        if(userRepository.countByUserId(user.getUserId()) == 1) {
            throw new UserAlreadyExistsException(user.getUserId());
        } else{
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if(user == null){
            throw new DomainNullPointerException(user);
        }
        if(userRepository.countByUserId(user.getUserId()) == 0){
            throw new UserNotFoundException(user.getUserId());
        } else{
            userRepository.update(user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if(userId == null){
            throw new NullPointerException("ID 값이 없습니다.");
        }
        if(userRepository.countByUserId(userId) == 0){
            throw new UserNotFoundException(userId);
        } else{
            userRepository.deleteByUserId(userId);
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        if(userId == null || userPassword == null){
            throw new NullPointerException("ID 값이 없습니다.");
        }
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        Optional<User> optionalUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        User user = optionalUser.orElse(null);
        if(user != null){
            userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
            return user;
        }
        throw new UserNotFoundException(userId);
    }

}
