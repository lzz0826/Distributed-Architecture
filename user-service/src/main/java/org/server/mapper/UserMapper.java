package org.server.mapper;

import org.server.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  User selectAll();

  int insertUser(User user);


  User selectById(@Param("id") String id);









}