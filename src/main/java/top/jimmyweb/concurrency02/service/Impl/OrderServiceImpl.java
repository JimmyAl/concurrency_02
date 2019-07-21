package top.jimmyweb.concurrency02.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jimmyweb.concurrency02.dao.OrderDao;
import top.jimmyweb.concurrency02.dataobject.MsOrder;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.service.OrderService;
import top.jimmyweb.concurrency02.vo.GoodsVo;

import java.util.Date;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/20 0020
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDao orderDao;

    /*@Autowired
    private RedisUtil redisUtil;*/

    @Override
    public MsOrder getMsOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getMsOrderByUserIdGoodsId(userId,goodsId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goodsVo) {
        //1.生成order_info
        OrderInfo orderInfo=new OrderInfo();
        //long类型 private Long deliveryAddrId;   L
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        //秒杀价格
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        //订单状态  ---0-新建未支付  1-已支付  2-已发货  3-已收货
        orderInfo.setOrderStatus(0);
        //用户id
        orderInfo.setUserId(user.getId());
        //返回orderId
        long orderId=orderDao.insertSelective(orderInfo);
        System.out.println("-----orderId:"+orderId);

        OrderInfo orderquery=orderDao.selectorderInfo(user.getId(), goodsVo.getId());
        long orderIdquery=orderquery.getId();
        System.out.println("-----orderIdquery:"+orderIdquery);

        //2.生成miaosha_order
        MsOrder msOrder =new MsOrder();
        msOrder.setGoodsId(goodsVo.getId());
        //将订单id传给秒杀订单里面的订单orderid
        msOrder.setOrderId(orderIdquery);
        msOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(msOrder);
        //set(KeyPrefix prefix,String key,T value)   设置缓存数据。
        //redisUtil.set(OrderKey.getMiaoshaOrderByUidAndGid, ""+user.getId()+"_"+goodsvo.getId(), miaoshaorder);
        return orderInfo;
    }
}


