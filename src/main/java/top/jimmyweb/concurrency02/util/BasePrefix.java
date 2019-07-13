package top.jimmyweb.concurrency02.util;

import lombok.NoArgsConstructor;
import top.jimmyweb.concurrency02.redis.KeyPrefix;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/28 0028
 */
@NoArgsConstructor
public abstract class BasePrefix implements KeyPrefix {

    private int expireSecond;
    private String prefix;


    public BasePrefix(String prefix){
        this(0,prefix);
    }

    public BasePrefix(int expireSecond,String prefix){
        this.expireSecond = expireSecond;
        this.prefix = prefix;
    }

    @Override
    public int expireSecond() {
        return expireSecond;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className +":"+ prefix;
    }
}
