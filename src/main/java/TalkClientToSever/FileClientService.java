package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;

//该类完成完成文件传输服务
public class FileClientService {
    /**
     * @param src 源文件
     * @param dest 把该文件传输到对方的目的目录
     * @param senderId 发送用户id
     * @param getterId 接收用户id
     */
    public void SendFileToOne(String src,String dest,String senderId,String getterId)
    {
        //读取src文件 --->message
        Message message =new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);
        System.out.println(message);

        //需要先读取文件

        FileInputStream fileInputStream=null;
        byte[] fileBytes =new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//讲src文件读入到程序的字节数组
            //将文件对应的字节数组设置message

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //提示信息
        System.out.println("\n"+senderId+"给"+getterId+"发送文件"+src+"到对方电脑的目录"+dest);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
