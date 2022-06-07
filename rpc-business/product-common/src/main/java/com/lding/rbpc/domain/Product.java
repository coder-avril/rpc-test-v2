package com.lding.rbpc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    //id
    private Long id;
    //产品编号
    private String sn;
    //产品名称
    private String name;
    //产品价格
    private BigDecimal price;
}
