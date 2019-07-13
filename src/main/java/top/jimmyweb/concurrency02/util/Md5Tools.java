package top.jimmyweb.concurrency02.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/25 0025
 */
public class Md5Tools {

    private static final String SALT = "1a2b3c4d";

    private static String getMd5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * 前端输入+salt
     * @param input
     * @return
     */
    private static String inputPassToForm(String input){
        String str ="" + SALT.charAt(0) + SALT.charAt(2) + input + SALT.charAt(5) +SALT.charAt(4);
        return getMd5(str);
    }

    public static String formToDbPass(String inputForm,String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputForm + salt.charAt(5) + salt.charAt(4);
        return getMd5(str);
    }

    public static String dbPass(String inputForm,String salt){
        String input = inputPassToForm(inputForm);
        String dbPass = formToDbPass(input,salt);
        return dbPass;
    }

    public static void main(String[] arg){
        System.out.println(inputPassToForm("123456"));
        System.out.println(formToDbPass("d3b1294a61a07da9b49b6e22b2cbd7f9","1a2b3c4d"));
        System.out.println(dbPass("123456","1a2b3c4d"));
    }
}
