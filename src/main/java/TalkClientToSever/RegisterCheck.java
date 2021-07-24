package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.MessageType;
import TalkBasic.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class RegisterCheck {
    private final User u = new User();
    private Socket socket;

    //根据userid和password到服务器验证用户是否合法
    public boolean CheckRegister(String reuser) {
        boolean checkUser = false;
        //创建user对象
        u.setReuser(reuser);
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
            //检测注册的用户名是否已经被注册过
            if (ms.getMesType().equals(MessageType.MESSAGE_REGISTER_SUCCEED)) {
                //如果验证成功返回true
                checkUser = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUser;
    }



}
