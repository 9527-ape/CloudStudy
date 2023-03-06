package com.jiajia.springcloud.dao;

import com.jiajia.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 一般用@Mapper比@Repository好,@Mapper是mybatis自身带的,@Repository是Spring的
 * */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
