package top.jimmyweb.concurrency02.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jimmyweb.concurrency02.dataobject.MsOrder;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/20 0020
 */
@Mapper
public interface OrderDao {


    /**
     * 查询秒杀订单
     * @param userId
     * @param goodsId
     * @return
     */
    MsOrder getMsOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);


    /**
     * 创建秒杀订单
     * @param record
     * @return
     */
    int insertSelective(OrderInfo record);


    /**
     * 根据用户ID和商品ID查询订单详情
     * @param userId
     * @param goodsId
     * @return
     */
    public OrderInfo selectorderInfo(@Param("userId") Long userId, @Param("goodsId") Long goodsId);


    /**
     * 插入秒杀订单
     * @param msOrder
     */
    public void insertMiaoshaOrder(MsOrder msOrder);


    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return
     */
    public OrderInfo getOrderByOrderId(@Param("orderId") long orderId);

    /*@Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdAndCoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);
    @Insert("insert into order_info (user_id,goods_id,goods_name,goods_count,goods_price,order_channel,order_status,create_date) values "
            + "(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{orderStatus},#{createDate})")
    @SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="select last_insert_id()")
    public long insert(OrderInfo orderInfo);//使用注解获取返回值，返回上一次插入的id

    @Select("select * from order_info where user_id=#{userId} and goods_id=#{goodsId}")
    public OrderInfo selectorderInfo(@Param("userId") Long userId, @Param("goodsId") Long goodsId);//使用注解获取返回值，返回上一次插入的id

    @Insert("insert into miaosha_order (user_id,goods_id,order_id) values (#{userId},#{goodsId},#{orderId})")
    public void insertMiaoshaOrder(MiaoshaOrder miaoshaorder);

    @Select("select * from order_info where id=#{orderId}")
    public OrderInfo getOrderByOrderId(@Param("orderId") long orderId);*/
}
