package top.jimmyweb.concurrency02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.jimmyweb.concurrency02.dataobject.MsOrder;
import top.jimmyweb.concurrency02.dataobject.OrderInfo;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.GoodsService;
import top.jimmyweb.concurrency02.service.MiaoshaService;
import top.jimmyweb.concurrency02.service.OrderService;
import top.jimmyweb.concurrency02.vo.GoodsVo;

import java.util.List;


/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/2 0002
 */
@RequestMapping("/ms")
@Controller
public class GoodController {

    private static Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    /**
     * 商品列表页
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/good_list")
    public String getList(Model model,MiaoshaUser user){
        List<GoodsVo> goodsVoList = goodsService.getGoodsVoList();
        model.addAttribute("goodsList",goodsVoList);
        logger.info(goodsVoList.toString());
        return "goods_list";
    }

    /**
     * 商品详情页
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/goods_detail/{goodsId}")
    public String getDetail(Model model, MiaoshaUser user,
                            @PathVariable("goodsId")long goodsId){
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        //开始时间
        long start = goodsVo.getStartDate().getTime();
        //结束时间
        long end = goodsVo.getEndDate().getTime();
        //现在时间
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (now < start){
            //秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int) (start - now) / 1000;
        }else if (now > end){
            //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {
            //秒杀正在进行
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("goods",goodsVo);
        model.addAttribute("user",user);
        model.addAttribute("status",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        logger.info(goodsVo.toString());
        return "goods_detail";
    }

    /**
     * 商品秒杀
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model,MiaoshaUser user,
                            @RequestParam("goodsId")long goodsId){
        model.addAttribute("user",user);
        if(user == null){
            return "login";
        }
        //判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goodsVo.getStockCount();
        if (stock <= 0){
            model.addAttribute("errMsg",codeMsg.GOODS_NOSTOCK.getMsg());
            return "miaosha_fail";
        }
        //判断是否重复下单
        MsOrder order = orderService.getMsOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order != null){
            model.addAttribute("errMsg",codeMsg.REPEATE_ORDER.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.doMiaosha(user,goodsVo);
        logger.info(orderInfo.toString());
        model.addAttribute("goods",goodsVo);
        model.addAttribute("orderInfo",orderInfo);
        return "order_detail";
    }
}
