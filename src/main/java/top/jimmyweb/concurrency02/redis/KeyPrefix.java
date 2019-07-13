package top.jimmyweb.concurrency02.redis;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/28 0028
 */
public interface KeyPrefix {

    /**
     * 过期时间
     * @return
     */
    public int expireSecond();

    /**
     * Redis-->key前缀
     * @return
     */
    public String getPrefix();
}
