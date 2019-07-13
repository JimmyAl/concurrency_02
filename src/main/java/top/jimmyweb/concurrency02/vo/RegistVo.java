package top.jimmyweb.concurrency02.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.jimmyweb.concurrency02.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/7/5 0005
 */
@Data
public class RegistVo {

    @NotNull
    private String username;

    @NotNull
    @Length(max = 32)
    private String password;

    @NotNull
    @IsMobile
    private long telphone;

    @NotNull
    private String otpCode;
}
