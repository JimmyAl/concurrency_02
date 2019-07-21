package top.jimmyweb.concurrency02.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfo {

    private Long id;


    private Long userId;


    private Long goodsId;


    private Long deliveryAddrId;


    private String goodsName;


    private Integer goodsCount;


    private BigDecimal goodsPrice;


    private int orderChannel;


    private int orderStatus;


    private Date createDate;


    private Date payDate;
}