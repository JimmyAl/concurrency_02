package top.jimmyweb.concurrency02.service;

import top.jimmyweb.concurrency02.dataobject.Goods;
import top.jimmyweb.concurrency02.dataobject.MsOrder;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.vo.GoodsVo;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/20 0020
 */
public interface OrderService {

    /**
     * 查询用户秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    public MsOrder getMsOrderByUserIdGoodsId(long userId,long goodsId);

    /**
     * 创建秒杀订单
     * @param user
     * @param goodsVo
     * @return
     */
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goodsVo);
}
