package top.jimmyweb.concurrency02.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.exception.GlobleException;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.Impl.MiaoshaUserServiceImpl;
import top.jimmyweb.concurrency02.service.MiaoshaUserService;

import javax.servlet.http.HttpServletResponse;


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
    private MiaoshaUserService miaoshaUserService;

    @RequestMapping("/good_list")
    public String getList(Model model,HttpServletResponse response,
                          @CookieValue(value = MiaoshaUserServiceImpl.COOKIE_NAME,required = false) String cookieToken,
                          @RequestParam(value = MiaoshaUserServiceImpl.COOKIE_NAME,required = false) String parmToken){
        if (StringUtils.isBlank(cookieToken)&&StringUtils.isBlank(parmToken)){
            return "login";
        }
        String token = cookieToken.isEmpty() ? parmToken : cookieToken;
        MiaoshaUser user  = miaoshaUserService.getByToken(response,token);
        if (user.equals("")){
            throw new GlobleException(codeMsg.GET_USERINFO_FAIL);
        }
        model.addAttribute("user",user);
        return "good_list";
    }
}
