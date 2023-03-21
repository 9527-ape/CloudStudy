package com.jiajia.springcloud.service.impl;

import com.jiajia.springcloud.dao.PaymentDao;
import com.jiajia.springcloud.entities.Payment;
import com.jiajia.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    //@Autowired spring的
    @Resource//java自带的
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
