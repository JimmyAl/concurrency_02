package top.jimmyweb.concurrency02.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jimmyweb.concurrency02.dao.GoodsDaoMapper;
import top.jimmyweb.concurrency02.dataobject.Goods;
import top.jimmyweb.concurrency02.service.GoodsService;
import top.jimmyweb.concurrency02.vo.GoodsVo;

import java.util.List;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/13 0013
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDaoMapper goodsDao;

    @Override
    public List<GoodsVo> getGoodsVoList() {
        return goodsDao.getGoodsVoList();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(Long id) {
        return goodsDao.getGoodsVoByGoodsId(id);
    }


    @Override
    public void reduceStock(long goodsId) {
        goodsDao.reduceStock(goodsId);
    }

}
