package top.jimmyweb.concurrency02.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/26 0026
 */
@Data
public class MiaoshaUser {

    /*用户ID*/
    private Long id;
    /*用户昵称*/
    private String nickname;
    /*用户密码*/
    private String pwd;
    /**
     * 验证字符串
     */
    private String salt;
    /*用户头像*/
    private String head;
    /*注册日期*/
    private Date registerDate;
    /**
     * 最后登陆日期
     */
    private Date lastLoginDate;
    /*登录次数*/
    private Integer loginCount;
}
