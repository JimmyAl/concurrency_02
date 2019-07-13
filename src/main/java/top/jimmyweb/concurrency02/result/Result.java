package top.jimmyweb.concurrency02.result;

import lombok.Data;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/21 0021
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public static <T> Result<T> error(codeMsg cm){
        return new Result<T>(cm);
    }

    private Result(codeMsg cm){
        if (cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }


}
