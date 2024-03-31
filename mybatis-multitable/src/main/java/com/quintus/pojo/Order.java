package com.quintus.pojo;

import lombok.Data;

@Data
public class Order {

    private Integer orderId;
    private String OrderName;
    private Integer customerId;

    // 一个订单对应一个客户 对一关系 所以存一个客户对象
    private Customer customer;
}
