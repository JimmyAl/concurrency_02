package top.jimmyweb.concurrency02.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jimmyweb.concurrency02.exception.GlobleException;
import top.jimmyweb.concurrency02.redis.RedisUtil;
import top.jimmyweb.concurrency02.result.Result;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.MiaoshaUserService;
import top.jimmyweb.concurrency02.util.StrUtil;
import top.jimmyweb.concurrency02.vo.LoginVo;
import top.jimmyweb.concurrency02.vo.RegistVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Random;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/4 0004
 */
@Controller
@RequestMapping("/ms")
public class RegistController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取验证码
     */
    @RequestMapping("/getOpt")
    @ResponseBody
    public Result<String> getOtp(@RequestParam(name = "telphone")String telphone){

        //生成验证码
        Random random = new Random();
        int randonInt = random.nextInt(999999);
        randonInt += 100;
        String otpCode = String.valueOf(randonInt);

        //手机号与验证码关联
        boolean tag = redisUtil.set(telphone,randonInt);
        if (!tag){
            throw new GlobleException(codeMsg.GET_OTP_FAIL);
        }

        //发送验证码
        return Result.success(otpCode);
    }


    /**
     * 用户注册
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Result<String> doRegister(@Valid RegistVo registVo, HttpServletResponse res){
        logger.info(registVo.toString());
        codeMsg codeMsg = miaoshaUserService.doRegister(registVo,res);
        if (codeMsg.getCode()==0){
            return Result.success("注册成功");
        }else {
            return Result.error(codeMsg);
        }
    }



}
