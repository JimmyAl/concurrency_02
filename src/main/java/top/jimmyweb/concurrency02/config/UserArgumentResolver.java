package top.jimmyweb.concurrency02.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;
import top.jimmyweb.concurrency02.exception.GlobleException;
import top.jimmyweb.concurrency02.result.codeMsg;
import top.jimmyweb.concurrency02.service.Impl.MiaoshaUserServiceImpl;
import top.jimmyweb.concurrency02.service.MiaoshaUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieStore;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/13 0013
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {


    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz==MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(MiaoshaUserServiceImpl.COOKIE_NAME);
        String cookieToken = getCookieValue(request,MiaoshaUserServiceImpl.COOKIE_NAME);

        if (StringUtils.isBlank(cookieToken)&&StringUtils.isBlank(paramToken)){
            return null;
        }
        String token = cookieToken.isEmpty() ? paramToken : cookieToken;
        MiaoshaUser user  = miaoshaUserService.getByToken(response,token);
        if (user == null||"".equals(user)){
            throw new GlobleException(codeMsg.GET_USERINFO_FAIL);
        }
        return user;
    }

    private static String getCookieValue(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
