package top.jimmyweb.concurrency02.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import top.jimmyweb.concurrency02.dataobject.Goods;
import top.jimmyweb.concurrency02.vo.GoodsVo;

import java.util.List;

@Mapper
public interface GoodsDaoMapper {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> getGoodsVoList();


    /**
     * 获取商品信息
     * @param goodsId
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);


    /**
     * stock_count>0的时候才去更新，数据库本身会有锁，那么就不会在数据库中同时多个线程更新一条记录，使用数据库特性来保证超卖的问题
     * @param goodsId
     */
    public void reduceStock(long goodsId);



}