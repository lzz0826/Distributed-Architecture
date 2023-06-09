package org.server.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import javax.annotation.Resource;
import org.server.dao.ChatroomDAO;
import org.server.exception.AddErrorException;
import org.server.exception.chatroom.ChatroomNotOpenException;
import org.server.exception.chatroom.NotFoundChatroomException;
import org.server.mapper.ChatroomMapper;
import org.server.sercice.IdGeneratorService;
import org.server.vo.ChatroomVO;
import org.server.websocket.mpa.WsChatRoom;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ChatroomService {


  @Resource
  private ChatroomMapper chatroomMapper;

  @Resource
  private IdGeneratorService idGeneratorService;


  public Page<ChatroomVO> getChatroomAll(int page, int pageSize){
    Page<ChatroomVO> vos = new Page<>();
    Page<ChatroomDAO> daos = PageHelper.startPage(page,pageSize)
        .doSelectPage(() -> chatroomMapper.selectAll());
    if(daos.isEmpty()){
      return vos;
    }

    BeanUtils.copyProperties(daos,vos);

    for (ChatroomDAO dao : daos) {
      ChatroomVO vo = convertDAOToVO(dao);
      vos.add(vo);
    }
    return vos;
  }


  public ChatroomVO getChatroomById(String id){
    ChatroomDAO dao = chatroomMapper.selectById(id);
    return convertDAOToVO(dao);
  }

  public ChatroomVO convertDAOToVO(ChatroomDAO dao){
    if(dao == null){
      return null;
    }
    ChatroomVO vo = ChatroomVO
        .builder()
        .id(dao.getId())
        .name(dao.getName())
        .adminUserId(dao.getAdminUserId())
        .status(dao.getStatus())
        .updateTime(dao.getUpdateTime())
        .createTime(dao.getCreateTime())
        .build();
    return vo;
  }






  public void addChatroom(String name,String adminUserId) throws AddErrorException {

    ChatroomDAO dao = ChatroomDAO
        .builder()
        .id(idGeneratorService.getNextId())
        .name(name)
        .adminUserId(adminUserId)
        .status(false)
        .updateTime(new Date())
        .createTime(new Date())
        .build();

    int i = chatroomMapper.insertChatroom(dao);

    if(i == 0){
      throw new AddErrorException();
    }

  }

  public ChatroomVO joinChatroom(String chatroomId ,String userId) throws ChatroomNotOpenException {

    ChatroomVO vo = getChatroomById(chatroomId);

    if(vo == null){
      throw new NotFoundChatroomException();
    }

    if(!vo.getStatus()){
      throw new ChatroomNotOpenException();
    }

    WsChatRoom.addUserToChatRoom(chatroomId,userId);

    return vo;

  }

  public void leaveChatroom(String userId){
    WsChatRoom.removeUserChatRoomAll(userId);
  }


}
