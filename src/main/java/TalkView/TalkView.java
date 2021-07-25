package TalkView;

import TalkBasic.User;
import TalkClientToSever.*;
import TalkView.Utility.Utility;

public class TalkView {
    public static void main(String[] args) {
        TalkView tk=new TalkView();
        tk.mainMenu();
    }
    private  FileClientService fileClientService =new FileClientService();
    private boolean loop=true;//控制是否一致显示菜单
    private String key="";//获取用户输入
    User user=new User();
    private final UserClient userClient =new UserClient();//用于登录服务器/注册用户
    private final MessageToService messageToService =new MessageToService();//用于聊天的发送
    //用户菜单
    public void UserMenu()
    {
        System.out.println("=======用户"+user.getUserId()+"=======");
        System.out.println("\t\t 1 显示在线用户列表");
        System.out.println("\t\t 2 群发消息");
        System.out.println("\t\t 3 私聊消息");
        System.out.println("\t\t 4 发送文件");
        System.out.println("\t\t 9 退出系统");
        System.out.print("请输入您的选择：");
    }
    //显示主菜单
    public void mainMenu()
    {
        while(loop)
        {
            System.out.println("=======欢迎使用Talk即时通讯系统=======");
            System.out.println("\t\t\t1 登录账号");
            System.out.println("\t\t\t2 注册账号");
            System.out.println("\t\t\t9 退出系统");
            System.out.print("请输入您的选择：");
            key = Utility.readString(1);
            //根据用户的输入，来处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.print("请输入用户ID:");
                    user.setUserId(Utility.readString(16));
                    System.out.print("请输入密码:");
                    user.setPassword(Utility.readString(16)) ;
                    if (userClient.checkUser(user.getUserId(), user.getPassword())) {
                        while (true) {
                            UserMenu();
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClient.onlineFriendList();
                                    break;
                                case "2":
                                    MessageToService toall = new MessageToService();

                                    System.out.println("请输入想对在线用户的话：");
                                    String string = Utility.readString(100);
                                    //调用一个方法，将消息封装成message对象，发送给服务端
                                    toall.sendMessageToAll(string,user.getUserId());
                                    break;
                                case "3":
                                    System.out.println("请输入想要私聊的用户ID(在线)： ");
                                    String getter = Utility.readString(50);
                                    System.out.println("请输入向发送的消息:");
                                    String content = Utility.readString(100);
                                    this.messageToService.sendMessageToUser(content, user.getUserId(), getter);
                                    break;
                                case "4":
                                    System.out.print("你想法文件给哪个用户(需要该用户在在线)：");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入发送文件的完整路径（例如：d:\\XX.jpg）");
                                    String src = Utility.readString(100);
                                    System.out.println("请输入想要把文件储存到对方的完整路径");
                                    String dest = Utility.readString(100);
                                    fileClientService.SendFileToOne(src,dest, user.getUserId(), getterId);
                                    break;
                                case "9":
                                    userClient.logout();
                                    loop = false;
                                    break;
                            }
                        }

                    } else {
                        System.out.println("=====登录失败=====");
                    }
                    break;
                case "2":
                    RegisterCheck registerCheck = new RegisterCheck();
                    while(true) {
                        System.out.print("请输入用户ID:");
                        user.setReuser(Utility.readString(16));
                        boolean check = registerCheck.CheckRegister(user.getReuser());
                        if(check)
                        {
                            RegisterUers registerUers = new RegisterUers();
                            System.out.print("请输入密码:");
                            user.setRepwd(Utility.readString(16));
                            registerUers.register(user.getReuser(), user.getRepwd());
                            break;
                        }
                        else System.out.println("用户名已存在，请重新输入！！！");
                    }
                    break;
                case "9":
                    loop = false;
                    System.out.println("退出系统");
                    break;
            }
        }
    }

}
