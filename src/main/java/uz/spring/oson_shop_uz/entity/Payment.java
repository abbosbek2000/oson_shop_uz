package uz.spring.oson_shop_uz.entity;

import uz.spring.oson_shop_uz.entity.base.AbsEntity;

public class Payment extends AbsEntity {
    private Long userId;

    private String checkId;

    private boolean status;

    private Double amount;

}
