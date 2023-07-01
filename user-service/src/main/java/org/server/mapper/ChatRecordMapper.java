package org.server.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.server.controller.req.chatroomRecord.ListReq;
import org.server.dao.ChatRecordDAO;

@Mapper
public interface ChatRecordMapper {


  int insertChatRecord(ChatRecordDAO dao);

  List<ChatRecordDAO> selectChatRecords(ListReq req);






}
