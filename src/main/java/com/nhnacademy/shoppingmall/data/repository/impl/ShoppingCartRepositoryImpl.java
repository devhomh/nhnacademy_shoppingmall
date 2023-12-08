package com.nhnacademy.shoppingmall.data.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.data.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.repository.interfaces.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

public class ShoppingCartRepositoryImpl implements Repository<ShoppingCart> {
    private String query;

    @Override
    public int save(ShoppingCart shoppingCart) {
        if(shoppingCart == null){
            throw new DomainNullPointerException(shoppingCart);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "INSERT INTO ShoppingCart(CartID, Quantity, ProductID, DateCreateed) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, shoppingCart.getCartID());
            ps.setInt(2, shoppingCart.getQuantity());
            ps.setInt(3, shoppingCart.getProductID());
            ps.setTimestamp(4, Timestamp.valueOf(shoppingCart.getDateCreated()));
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ShoppingCart> findById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query ="SELECT * FROM ShoppingCart WHERE RecordID=?";

        try(PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if(rs.next()){
                    ShoppingCart shoppingCart = new ShoppingCart(
                            rs.getInt("RecordID"),
                            rs.getString("CartID"),
                            rs.getInt("Quantity"),
                            rs.getInt("ProductID"),
                            Objects.nonNull(rs.getTimestamp("DateCreateed")) ? rs.getTimestamp("DateCreateed").toLocalDateTime() : null
                    );
                    return Optional.of(shoppingCart);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int update(ShoppingCart shoppingCart) {
        if(shoppingCart == null){
            throw new DomainNullPointerException(shoppingCart);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "UPDATE ShoppingCart SET CartID = ?, Quantity = ?, ProductID = ?, DateCreateed = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, shoppingCart.getCartID());
            ps.setInt(2, shoppingCart.getQuantity());
            ps.setInt(3, shoppingCart.getProductID());
            ps.setTimestamp(4, Timestamp.valueOf(shoppingCart.getDateCreated()));
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query ="DELETE FROM ShoppingCart WHERE RecordID = ?";

        try(PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(int id) {
        return findById(id).isPresent() ? 1 : 0;
    }

    @Override
    public Optional<Integer> totalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "SELECT count(*) AS total FROM ShoppingCart";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int result = rs.getInt("total");
                return Optional.of(result);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
