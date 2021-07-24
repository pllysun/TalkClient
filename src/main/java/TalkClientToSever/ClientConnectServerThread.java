package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;
import TalkView.TalkView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    //该线程需要持有Socket

    private final Socket socket;

    public ClientConnectServerThread(Socket socket)
    {
        //构造器可以接受一个Socket对象
        this.socket=socket;
    }

    @Override
    public void run() {
        System.out.println("客户端线程，等待读取从服务器端发送的消息");
        //因为Thread需要在后台和服务器通信，因此我们需要用While循环
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送Message对象，线程会阻塞在这里
                Message message = (Message) ois.readObject();
                //判断message类型，然后做相应业务处理
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND))
                {//如果是读取到的是服务端返回的在线用户列表，取出在线列表信息并显示
                    //规定
                    //获取的用户名为 用户1 用户2 用户3 ...
                    String[] onlineUsers=message.getContent().split(" ");
                    System.out.println("=====当前在线用户列表======");
                    for (String onlineUser : onlineUsers) {
                        System.out.println("用户： " + onlineUser);
                    }

                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES))
                {
                    //把从服务器转发的消息显示在我们的控制台即可
                    TalkView talkView = new TalkView();
                    talkView.UserMenu();
                    System.out.println("\n"+"用户:"+message.getSender()+"对你发送消息："+message.getContent()+"\n"+"发送消息时间为："+message.getSendTime());
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES))
                {
                    System.out.println("\n"+"用户："+message.getSender()+"对大家说："+message.getContent());

                }
                else if(message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {//如果是文件消息
                    System.out.println("\n"+message.getSender()+"给"+message.getSender()+"发文件："+message.getSrc()+"到我的电脑目录"+message.getDest());
                    //将取出message的文件字节数组，通过文件输出流写出到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                    System.out.println("\n"+"文件保存成功");
                }
                else
                {
                    System.out.println("其他类型消息，暂时不处理");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //为了更方便的得到Socket
    public Socket getSocket()
    {
        return socket;
    }

}
