package TalkClientToSever;

import java.util.HashMap;

//管理客户端连接到服务器端的线程的类
public class ManageClientConnectServerThread {
    //我们吧多个线程放入一个HashMap集合，key就是用户id，value就是线程
    private static HashMap<String ,ClientConnectServerThread> hm=new HashMap<>();
    //将某个线程加入到集合
    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread)
    {
        hm.put(userId,clientConnectServerThread);
    }
    //获取线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId)
    {
        return hm.get(userId);
    }
}
