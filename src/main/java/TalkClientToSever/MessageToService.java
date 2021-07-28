package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;


//客户端的发送消息到服务端
public class MessageToService {
    public void sendMessageToUser(String content, String senderId, String getterId)
    {

        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);//普通消息
        message.setSender(senderId);//发送者id
        message.setGetter(getterId);//接收者的id
        message.setContent(content);//消息内容
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp sendtime = new Timestamp(currentTimeMillis);
        message.setSendTime(sendtime);//发送时间


        //发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageToAll(String content,String senderId)
    {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//普通消息
        message.setSender(senderId);//发送者id
        message.setContent(content);//消息内容
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp sendtime = new Timestamp(currentTimeMillis);
        message.setSendTime(sendtime);//发送时间
        System.out.println("你给所有在线的用户发送 "+content+" 发送成功，其他在线人员已收到！");
        //发送给服务端

        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
