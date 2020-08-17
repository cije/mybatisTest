package com.ce.dao;

import com.ce.domain.QueryVo;
import com.ce.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户查询 动态sql 注解
 */
public interface IUserDao7 {
    /**
     * 查询所有操作
     *
     * @return 所有user集合
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 根据queryVo中的条件查询
     */
    @Select("select * from user where username like #{user.username}")
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     *
     * @param user 查询的条件
     * @return
     */
    @Select({"<script>",
            "select *from user",
            "        <where>",
            "            <if test='username!=null'>",
            "                and username = #{username}",
            "            </if>",
            "            <if test='sex!=null'>",
            "                and sex = #{sex}",
            "            </if>",
            "            <if test='birthday!=null'>",
            "                and birthday = #{birthday}",
            "            </if>",
            "            <if test='address!=null'>",
            "                and address = #{address}",
            "            </if>",
            "        </where>",
            "</script>"})
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合查询用户信息
     */
    @Select({"<script>",
            "select * from user",
            "<where>",
            "   <if test='ids !=null and ids.size()>0'>",
            "      <foreach collection='ids' open='id in (' close=')' item='id' separator=','>",
            "          #{id}",
            "      </foreach>",
            "   </if>",
            "</where>",
            "</script>"})
    List<User> findUserInIds(QueryVo vo);
}
