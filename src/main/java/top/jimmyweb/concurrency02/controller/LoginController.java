package top.jimmyweb.concurrency02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jimmyweb.concurrency02.result.Result;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.MiaoshaUserService;
import top.jimmyweb.concurrency02.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/25 0025
 */
@Controller
@RequestMapping("/ms")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<String> doLogin(@Valid LoginVo loginVo, HttpServletResponse res){
            logger.info(loginVo.toString());
            codeMsg codeMsg = miaoshaUserService.login(loginVo,res);

            return Result.success("登录成功");

    }

}
