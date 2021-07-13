package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

//客户端的发送消息到服务端
public class MessageToService {
    public void sendMessageToUser(String content,String senderId,String getterId)
    {
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);//普通消息
        message.setSender(senderId);//发送者id
        message.setGetter(getterId);//接收者的id
        message.setContent(content);//消息内容
        message.setSendTime(new java.util.Date().toString());//发送时间
        System.out.println("你给用户："+getterId+"发送 "+content+" 发送成功，对方已收到！");
        //发送给服务端

        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
