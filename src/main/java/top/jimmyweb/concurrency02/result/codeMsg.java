package top.jimmyweb.concurrency02.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/21 0021
 */
@Data
@AllArgsConstructor
public class codeMsg {
    private int code;
    private String msg;


    /**
     * 系统通用码:5001xx
     */
    public final static codeMsg SUCCESS = new codeMsg(0,"success");
    public final static codeMsg SERVER_ERROR = new codeMsg(500100,"服务器错误");
    public final static codeMsg BIND_ERROR = new codeMsg(500101,"参数校验异常：%s");
    public final static codeMsg GET_USERINFO_FAIL = new codeMsg(500102,"获取用户信息失败，请重新登录");
    /**
     * 登录错误代码：5002xx
     */
    public final static codeMsg PASSWORD_ERROR = new codeMsg(500201,"密码错误");
    public final static codeMsg MOBILE_ERROR = new codeMsg(500202,"手机号错误");
    public final static codeMsg SESSION_ERROR = new codeMsg(500203,"Session不存在或已失效");
    public final static codeMsg TIME_OUT = new codeMsg(500204,"服务器响应超时");
    public final static codeMsg PASSWORD_ISNULL = new codeMsg(500205,"密码不能为空");
    public final static codeMsg MOBILE_ISNULL = new codeMsg(500206,"手机号不能为空");
    public final static codeMsg PM_ERROR = new codeMsg(500207,"账号或密码错误");
    public final static codeMsg USER_NOTEXISTS = new codeMsg(500208,"用户不存在");
    public final static codeMsg GET_OTP_FAIL = new codeMsg(500209,"获取验证码错误");
    public final static codeMsg OTPCODE_FAIL = new codeMsg(500210,"验证码错误");
    public final static codeMsg USER_EXISTS = new codeMsg(500211,"用户已存在");


    /**
     * 商品错误代码:5003xx
     */
    public final static codeMsg GOODS_NOSTOCK = new codeMsg(500301,"商品库存不足，秒杀已结束！");
    public final static codeMsg REPEATE_ORDER = new codeMsg(500301,"不可重复下单！");


    /**
     *
     * @param objects
     * @return
     */
    public codeMsg fillArgs(Object... objects){
        int code = this.code;
        String message = String.format(this.msg,objects);
        return new codeMsg(code,message);
    }


}
