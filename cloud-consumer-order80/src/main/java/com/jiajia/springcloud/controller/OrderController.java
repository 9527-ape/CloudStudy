package com.jiajia.springcloud.controller;

import com.jiajia.springcloud.entities.CommonResult;
import com.jiajia.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

     //public static final String PaymentSrv_URL = "http://localhost:8001";

     //集群改为使用微服务名称(相当于key-value键值对)
     public static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

     @Resource
     private RestTemplate restTemplate;

     @GetMapping("/consumer/payment/create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
     public CommonResult<Payment> create(Payment payment)
     {
          return restTemplate.postForObject(PaymentSrv_URL + "/payment/create",payment,CommonResult.class);
     }

     @GetMapping("/consumer/payment/get/{id}")
     public CommonResult<Payment> getPayment(@PathVariable Long id)
     {
          return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/"+id, CommonResult.class, id);
     }

}
