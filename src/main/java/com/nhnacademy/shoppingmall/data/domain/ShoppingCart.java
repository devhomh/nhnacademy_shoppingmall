package com.nhnacademy.shoppingmall.data.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingCart {
    private int recordID;
    private String cartID;
    private int quantity;
    private int productID;
    private LocalDateTime dateCreated;

    public ShoppingCart(String cartID, int quantity, int productID, LocalDateTime dateCreated){
        this.cartID = cartID;
        this.quantity = quantity;
        this.productID = productID;
        this.dateCreated = dateCreated;
    }
}
