package TalkBasic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    private String sender;//发送者
    private String getter;//接受者
    private String content;//消息内容
    private Timestamp sendTime;//发送时间
    private String mesType;//消息类型【可以在接口定义消息类型】
    private List<OffLineMessage> list; //离线消息数据群

    public List<OffLineMessage> getList() {
        return list;
    }

    public void setList(List<OffLineMessage> list) {
        this.list = list;
    }

    //文件传输
    private byte[] fileBytes;
    private int fileLen=0;
    private String dest;//将文件传输到哪里
    private String src;//源文件路径

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}
