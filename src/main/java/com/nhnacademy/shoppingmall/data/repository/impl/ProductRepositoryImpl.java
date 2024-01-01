package com.nhnacademy.shoppingmall.data.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.repository.interfaces.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryImpl implements Repository<Product> {
    private String sql;

    @Override
    public Optional<Product> findById(int productId) {
        sql = "SELECT * FROM Products WHERE ProductID = ?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Product product = new Product(
                            rs.getInt("ProductID"),
                            rs.getInt("CategoryID"),
                            rs.getString("ModelNumber"),
                            rs.getString("ModelName"),
                            rs.getInt("Quantity"),
                            rs.getString("ProductImage"),
                            rs.getInt("UnitCost"),
                            rs.getString("Description")
                    );
                    return Optional.of(product);
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "INSERT INTO Products(CategoryID, ModelNumber, ModelName, Quantity, ProductImage, UnitCost, Description) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, product.getCategoryID());
            ps.setString(2, product.getModelNumber());
            ps.setString(3, product.getModelName());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, Objects.nonNull(product.getProductImage()) ? product.getProductImage() : "no-image");
            ps.setInt(6, product.getUnitCost());
            ps.setString(7, product.getComment());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "DELETE FROM Products WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, productId);
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Products SET CategoryID = ?, ModelNumber = ?, ModelName = ?, Quantity = ?, ProductImage = ?, UnitCost = ?, Description = ? WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, product.getCategoryID());
            ps.setString(2, product.getModelNumber());
            ps.setString(3, product.getModelName());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, Objects.nonNull(product.getProductImage()) ? product.getProductImage() : "no-image.png");
            ps.setInt(6, product.getUnitCost());
            ps.setString(7, product.getComment());
            ps.setInt(8, product.getProductID());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(int productId) {
        return findById(productId).isPresent() ? 1 : 0;
    }

    public Optional<Integer> totalCount(){
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "SELECT count(*) AS total FROM Products";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
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
