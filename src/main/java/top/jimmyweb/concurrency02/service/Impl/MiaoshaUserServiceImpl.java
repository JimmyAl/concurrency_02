package top.jimmyweb.concurrency02.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jimmyweb.concurrency02.util.Md5Tools;
import top.jimmyweb.concurrency02.util.UUIDUtil;
import top.jimmyweb.concurrency02.dao.MiaoshaUserDao;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.exception.GlobleException;
import top.jimmyweb.concurrency02.redis.MsUserKey;
import top.jimmyweb.concurrency02.redis.RedisUtil;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.MiaoshaUserService;
import top.jimmyweb.concurrency02.vo.LoginVo;
import top.jimmyweb.concurrency02.vo.RegistVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/27 0027
 */
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    public static final String COOKIE_NAME = "token";

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 登录业务流程
     * @param loginVo
     * @param res
     * @return
     */
    @Override
    public codeMsg login(LoginVo loginVo, HttpServletResponse res) {
        if (loginVo==null){
            throw new GlobleException(codeMsg.SERVER_ERROR);
        }
        String inputPass = loginVo.getPassword();
        String mobile = loginVo.getUsername();
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        if (user == null){
            throw new GlobleException(codeMsg.USER_NOTEXISTS);
        }
        /*验证密码*/
        String dbPass = user.getPwd();
        String dbSalt = user.getSalt();
        String cPass = Md5Tools.formToDbPass(inputPass,dbSalt);
        if(!cPass.equals(dbPass)){
            throw new GlobleException(codeMsg.PASSWORD_ERROR);
        }

        /*生成Cookic*/
        String tokenId = UUIDUtil.uuid();
        addCookie(res,user,tokenId);
        return codeMsg.SUCCESS;
    }

    /**
     * 注册业务流程
     * @param registVo
     * @param res
     * @return
     */
    @Override
    public codeMsg doRegister(RegistVo registVo, HttpServletResponse res) {
        if (registVo==null){
            throw new GlobleException(codeMsg.SERVER_ERROR);
        }
        long telphone = registVo.getTelphone();
        MiaoshaUser user = miaoshaUserDao.getById(telphone);
        if (user != null){
            throw new GlobleException(codeMsg.USER_EXISTS);
        }

        return codeMsg.SUCCESS;
    }

    /**
     * 在redis查询Token
     * @param res
     * @param token
     * @return
     */
    @Override
    public MiaoshaUser getByToken(HttpServletResponse res,String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        MiaoshaUser user = (MiaoshaUser) redisUtil.get(MsUserKey.token+token);
        //刷新Token过期时间
        if (user != null){
            addCookie(res,user,token);
        }

        return user;
    }


    @Override
    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    /**
     * 生成Cookie
     * @param res
     * @param user
     */
    private void addCookie(HttpServletResponse res , MiaoshaUser user , String tokenId){
        redisUtil.set(MsUserKey.token+tokenId,user);
        Cookie ck = new Cookie(COOKIE_NAME,tokenId);
        ck.setMaxAge(MsUserKey.token.expireSecond());
        ck.setPath("/");
        res.addCookie(ck);
    }



}
