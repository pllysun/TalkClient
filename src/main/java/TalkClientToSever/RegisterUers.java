package TalkClientToSever;

import TalkBasic.Message;
import TalkBasic.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class RegisterUers {
    private final User u = new User();

    public boolean register(String reuser,String password) {
        boolean checkUser = false;
        //创建user对象
        u.setReuser(reuser);
        u.setRepwd(password);
        //连接到服务端，发送u对象

        try {
            InetAddress byName = InetAddress.getByName("39.108.56.141");
            Socket socket = new Socket(byName, 42986);
            socket.setSoTimeout(10000000);
            //得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            //读取从服务器回复的Message对象
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            ois.skip(4);
//            Message ms = (Message) ois.readObject();
//            //获取成功注册消息
//            String content = ms.getContent();
//            System.out.println(content);
            System.out.println("注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUser;
    }
}


