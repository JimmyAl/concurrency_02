package top.jimmyweb.concurrency02.exception;


import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jimmyweb.concurrency02.result.Result;
import top.jimmyweb.concurrency02.result.codeMsg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/27 0027
 */
@ControllerAdvice
@ResponseBody
public class GlobleExceptionHander{

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if (e instanceof GlobleException){
            GlobleException ge = (GlobleException)e;
            return Result.error(ge.getCm());
        }
        else if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> objectErrors = ex.getAllErrors();
            ObjectError oe = objectErrors.get(0);
            String msg = oe.getDefaultMessage();
            return Result.error(codeMsg.BIND_ERROR.fillArgs(msg));
        }else {
            return Result.error(codeMsg.SERVER_ERROR);
        }

    }
}
