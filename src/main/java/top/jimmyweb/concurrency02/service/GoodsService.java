package top.jimmyweb.concurrency02.service;

import org.springframework.stereotype.Service;
import top.jimmyweb.concurrency02.dataobject.Goods;
import top.jimmyweb.concurrency02.vo.GoodsVo;

import java.util.List;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/13 0013
 */

public interface GoodsService {

    /**
     * 商品列表
     * @return
     */
    public List<GoodsVo> getGoodsVoList();

    /**
     * 商品详情
     * @param id
     * @return
     */
    GoodsVo getGoodsVoByGoodsId(Long id);


    /**
     * stock_count>0的时候才去更新，数据库本身会有锁，那么就不会在数据库中同时多个线程更新一条记录，使用数据库特性来保证超卖的问题
     * @param goodsId
     */
    public void reduceStock(long goodsId);

}
