package com.jiajia.springcloud.controller;

import com.jiajia.springcloud.entities.CommonResult;
import com.jiajia.springcloud.entities.Payment;
import com.jiajia.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("=====》插入结果："+result);

        if(result>0){
            return new CommonResult(200,"插入数据成功,server:"+serverPort,result);
        }else {
            return new CommonResult(500,"插入数据失败,server:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("=====》查询结果："+payment);

        if(payment !=null){
            return new CommonResult(200,"查询成功,server:"+serverPort,payment);
        }else {
            return new CommonResult(500,"没有对应记录,server:"+serverPort,null);
        }
    }

}
