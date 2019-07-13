package top.jimmyweb.concurrency02.util;

import java.util.UUID;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/28 0028
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
