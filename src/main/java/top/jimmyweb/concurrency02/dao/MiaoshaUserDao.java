package top.jimmyweb.concurrency02.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.jimmyweb.concurrency02.domain.MiaoshaUser;

/**
 * @author : jimmy
 * @Description:
 * @date : 2019/6/26 0026
 */
@Mapper
public interface MiaoshaUserDao {

    /**
     * 根据ID查找用户
     * @param id
     * @return
     */
    @Select("select * from miaosha_user where id= #{id}")
    public MiaoshaUser getById(@Param("id") long id);

    /**
     * 保存注册用户
     * @param user
     */
    public void saveUser(MiaoshaUser user);

}
