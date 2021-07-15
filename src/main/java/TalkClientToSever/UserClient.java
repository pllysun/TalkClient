package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;
import TalkBasic.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

//完成用户登录和用户注册等功能
public class UserClient {
    private User u =new User();
    private Socket socket;
    //根据userid和password到服务器验证用户是否合法
    public boolean checkUser(String userId,String pwd) {
        boolean checkUser=false;
        //创建user对象
        u.setUserId(userId);
        u.setPassword(pwd);
        //连接到服务端，发送u对象

        try {
            InetAddress byName = InetAddress.getByName("39.108.56.141");
            socket = new Socket(byName, 42986);
            socket.setSoTimeout(10000000);
            //得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            //读取从服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();
            //检测是否登录成功
            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                //如果成功，创建一个于服务器端保持通信的线程->创建一个类ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动线程
                clientConnectServerThread.start();
                //这里为了以后客户端的扩展，我们将线程放入到集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId,clientConnectServerThread);
                checkUser=true;
            }
            else {
                //如果登录失败，我们就不能启动与服务器通讯的线程，关闭socket
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUser;
    }

    //向服务器端请求在线列表

    public void onlineFriendList()
    {
        //发送一个Message，类型MESSAGE_GET_ONLINE_FRIEND
        Message message =new Message();
        message.setSender(u.getUserId());
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);

        //发送给服务器
        //应该得到当前线程的socket，对应的ObjectOutputStream对象
        try {
            //从管理线程的集合里拿到当前线程，通过用户id获取，获取到线程以后得到socket，再从socket里拿到输出流
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);//发送一个请求获取在线列表的消息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //退出客户端，并给服务端发送一个关闭线程的消息对象
    public void logout(){
        Message messageExit= new Message();
        messageExit.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        messageExit.setSender(u.getUserId());

        //发送message
        try {
            ObjectOutputStream oos =new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(messageExit);
            System.out.println(u.getUserId()+"退出系统");
            System.exit(0);//结束进程
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
