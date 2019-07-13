package top.jimmyweb.concurrency02.redis;

import top.jimmyweb.concurrency02.util.BasePrefix;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/28 0028
 */
public class MsUserKey extends BasePrefix {

    /**
     * 3600s * 24 * 2 = 2 天
     */
    public static final int TOKEN_EXPIRE=3600*24*2;

    public MsUserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static MsUserKey token=new MsUserKey(TOKEN_EXPIRE,"tk");

    /**
     * 对象缓存一般没有有效期，设置为永久有效
     */
    public static MsUserKey getById=new MsUserKey(0,"id");
}
