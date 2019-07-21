package top.jimmyweb.concurrency02.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.service.GoodsService;
import top.jimmyweb.concurrency02.service.MiaoshaService;
import top.jimmyweb.concurrency02.service.OrderService;
import top.jimmyweb.concurrency02.vo.GoodsVo;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/20 0020
 */
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    /**
     * 秒杀业务 减库存 下订单
     * @param user
     * @param goods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInfo doMiaosha(MiaoshaUser user, GoodsVo goods) {
        goodsService.reduceStock(goods.getId());
        OrderInfo orderInfo = orderService.createOrder(user,goods);
        return orderInfo;
    }
}
