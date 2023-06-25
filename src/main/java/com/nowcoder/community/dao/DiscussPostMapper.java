package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /**
     * 查询所有的DiscussPost
     *
     * @param userId 考虑之后带有条件的查询
     * @param offset 考虑分页，该参数是起始行号
     * @param limit  考虑分页，该参数是每页限制条数
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名
    // 如果只有一个参数，并且在动态sql<if>里使用，则必须加别名（视频中说只有一个条件<if>的动态sql不加别名会报错）
    //该方法用来查询DiscussPost的行数
    int selectDiscussPostRows(@Param("userId") int userId);
}
