package top.jimmyweb.concurrency02.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.jimmyweb.concurrency02.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/26 0026
 */
@Data
public class LoginVo {

    @NotNull
    @IsMobile
    private String username;

    @NotNull
    @Length(max = 32)
    private String password;

}
