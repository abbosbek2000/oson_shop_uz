package uz.spring.oson_shop_uz.admin.security.base;


import uz.spring.oson_shop_uz.admin.entity.Category;
import uz.spring.oson_shop_uz.admin.response.ApiResponse;

import java.util.List;

public interface BaseService<T,R,V> {
    R add(T t);

    List<V> get();

    ApiResponse edit(T t, Long id);

    R delete(Long id);

}
