package com.jiajia.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Serializable序列化
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
