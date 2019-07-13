package top.jimmyweb.concurrency02.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/27 0027
 */
@WebFilter(filterName = "druidWebStatFilter" , urlPatterns = "/*"
            ,initParams = @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"))
public class DruidStatFilter extends WebStatFilter {
}
