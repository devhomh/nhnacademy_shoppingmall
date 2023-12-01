package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        if(userId == null || userPassword == null){
            throw new NullPointerException("입력한 ID나 Password 값이 없습니다. 다시 한번 확인하세요");
        }
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="SELECT * FROM Users WHERE UserID=? and UserPassWord =?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,userId);
            psmt.setString(2,userPassword);
            try (ResultSet rs = psmt.executeQuery()) {
                if(rs.next() && userId.equals( rs.getString("UserID")) && userPassword.equals(rs.getString("UserPassWord"))){
                    User user = new User(
                            rs.getString("UserID"),
                            rs.getString("UserName"),
                            rs.getString("UserPassWord"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLoginAt")) ? rs.getTimestamp("LatestLoginAt").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        if(userId == null){
            throw new NullPointerException("입력한 ID 값이 없습니다. 다시 한번 확인하세요");
        }
        //todo#3-2 회원조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="SELECT * FROM Users WHERE UserID=?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            try (ResultSet rs = psmt.executeQuery()){
                if(rs.next() && userId.equals( rs.getString("UserID"))){
                    User user = new User(
                            rs.getString("UserID"),
                            rs.getString("UserName"),
                            rs.getString("UserPassWord"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLoginAt")) ? rs.getTimestamp("LatestLoginAt").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(User user) {
        if(user == null){
            throw new NullPointerException("user 값이 null 입니다.");
        }
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String insertQuery = "INSERT INTO Users(UserID, UserName, UserPassWord, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLoginAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement psmt = connection.prepareStatement(insertQuery)){
            psmt.setString(1, user.getUserId());
            psmt.setString(2, user.getUserName());
            psmt.setString(3, user.getUserPassword());
            psmt.setString(4, user.getUserBirth());
            psmt.setString(5, String.valueOf(user.getUserAuth()));
            psmt.setInt(6, user.getUserPoint());
            psmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            psmt.setTimestamp(8, Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
            return psmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        if(userId == null){
            throw new NullPointerException("입력한 ID 값이 없습니다. 다시 한번 확인하세요");
        }
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="DELETE FROM Users WHERE UserID=?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            if(findById(userId).isPresent()){
                return psmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new NoSuchElementException("삭제할 ID가 없습니다.");
    }

    @Override
    public int update(User user) {
        if(user == null){
            throw new NullPointerException("user 값이 null 입니다.");
        }
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        if(findById(user.getUserId()).isEmpty()){
            throw new NoSuchElementException("수정할 ID가 없습니다.");
        } else{
            Connection connection = DbConnectionThreadLocal.getConnection();
            String sql = "UPDATE Users SET UserName = ?, UserPassWord = ?, UserBirth = ?, UserAuth = ?, UserPoint = ?, CreatedAt = ?, LatestLoginAt = ? WHERE UserID = ?";
            try (PreparedStatement psmt = connection.prepareStatement(sql)) {
                psmt.setString(1, user.getUserName());
                psmt.setString(2, user.getUserPassword());
                psmt.setString(3, user.getUserBirth());
                psmt.setString(4, String.valueOf(user.getUserAuth()));
                psmt.setInt(5, user.getUserPoint());
                psmt.setTimestamp(6, Timestamp.valueOf(user.getCreatedAt()));
                psmt.setTimestamp(7, Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
                psmt.setString(8, user.getUserId());
                return psmt.executeUpdate();
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        if(userId == null || latestLoginAt == null){
            throw new NullPointerException("입력한 값이 없습니다. 다시 한번 확인하세요");
        }
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        if(findById(userId).isEmpty()){
            throw new NoSuchElementException("수정할 ID가 없습니다.");
        } else{
            Connection connection = DbConnectionThreadLocal.getConnection();
            String sql = "UPDATE Users SET LatestLoginAt = ? WHERE UserID = ?";
            try (PreparedStatement psmt = connection.prepareStatement(sql)) {
                psmt.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
                psmt.setString(2, userId);
                return psmt.executeUpdate();
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int countByUserId(String userId) {
        if(userId == null){
            throw new NullPointerException("입력한 ID 값이 없습니다. 다시 한번 확인하세요");
        }
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        return findById(userId).isPresent() ? 1 : 0;
    }

}
