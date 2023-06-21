package org.server.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.server.dao.ChatroomDAO;
import org.server.dao.UserDAO;

@Mapper
public interface ChatroomMapper {

  int insertChatroom(ChatroomDAO dao);

  List<ChatroomDAO> selectAll();

  ChatroomDAO selectById(@Param("id") String id);






}
