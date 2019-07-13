package top.jimmyweb.concurrency02.service;

import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.vo.LoginVo;
import top.jimmyweb.concurrency02.vo.RegistVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/27 0027
 */

public interface MiaoshaUserService  {

    /**
     * 根据Id查询用户信息
     * @param id
     * @return
     */
    public MiaoshaUser getById(long id);


    /**
     * 用户验证登录
     * @param loginVo
     * @param response
     * @return
     */
    public codeMsg login(LoginVo loginVo,HttpServletResponse response);


    /**
     * 用Token缓存判断近期是否已经登录
     * @param token
     * @return
     */
    public MiaoshaUser getByToken(HttpServletResponse response,String token);


    /**
     * 用户注册
     * @param registVo
     * @param res
     * @return
     */
    public codeMsg doRegister(RegistVo registVo, HttpServletResponse res);
}
