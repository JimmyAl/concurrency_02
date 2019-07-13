package top.jimmyweb.concurrency02.Entity;

import lombok.Data;

import java.util.Date;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/25 0025
 */
@Data
public class User {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}
