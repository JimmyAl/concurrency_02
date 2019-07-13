package top.jimmyweb.concurrency02.validator;

import org.apache.commons.lang3.StringUtils;
import top.jimmyweb.concurrency02.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : jimmy
 * @Description: 手机号码校验器
 * @date : 2019/7/5 0005
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    /**
     * 判断是否是必须值
     */
    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            /**
             * 看值是否为必须值，如果为true，返回校验结果，如果不是，则判断是否为空，为空就返回通过
             */
            return StrUtil.isMobileNum(s);
        }else{
            if (StringUtils.isEmpty(s)){
                return true;
            }else {
                return StrUtil.isMobileNum(s);
            }
        }
    }


}
