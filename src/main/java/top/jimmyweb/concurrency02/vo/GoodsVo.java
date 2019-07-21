package top.jimmyweb.concurrency02.vo;

import lombok.Data;
import top.jimmyweb.concurrency02.dataobject.Goods;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/13 0013
 */
@Data
public class GoodsVo extends Goods {
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private BigDecimal miaoshaPrice;
}
