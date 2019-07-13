package top.jimmyweb.concurrency02.exception;

import lombok.Getter;
import top.jimmyweb.concurrency02.result.codeMsg;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/27 0027
 */
@Getter
public class GlobleException extends RuntimeException {

    private codeMsg cm;

    public GlobleException(codeMsg cm){
        super(cm.toString());
        this.cm = cm;

    }
}
