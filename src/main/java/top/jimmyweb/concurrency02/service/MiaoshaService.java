package top.jimmyweb.concurrency02.service;

import org.springframework.transaction.annotation.Transactional;
import top.jimmyweb.concurrency02.dataobject.Goods;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.vo.GoodsVo;


/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/20 0020
 */
public interface MiaoshaService {

    public OrderInfo doMiaosha(MiaoshaUser user, GoodsVo goods);
}
