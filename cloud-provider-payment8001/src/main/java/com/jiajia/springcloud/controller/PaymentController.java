package com.jiajia.springcloud.controller;

import com.jiajia.springcloud.entities.CommonResult;
import com.jiajia.springcloud.entities.Payment;
import com.jiajia.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获得Eureka上面的全部服务名称
        List<String> services = discoveryClient.getServices();
        for (String service: services
             ) {
            log.info("服务地址："+service);
        }

        //根据微服务名称获取全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance  instance:instances
             ) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;

    }

}
