package com.nhnacademy.shoppingmall.data.service.interfaces;

import com.nhnacademy.shoppingmall.data.domain.ShoppingRecord;
import java.util.List;

public interface ShoppingCartService extends Service<ShoppingRecord> {
    List<ShoppingRecord> filterById(String cartID);
}
