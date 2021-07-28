package TalkBasic;

import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;
import java.sql.Timestamp;

public class OffLineMessage implements Serializable {

    private static final long serialVersionUID= 5137771119790824135L;

    String senduser;
    String getuser;
    String message;
    Timestamp sendtime;


    public String getSenduser() {
        return senduser;
    }

    public void setSenduser(String senduser) {
        this.senduser = senduser;
    }

    public String getGetuser() {
        return getuser;
    }

    public void setGetuser(String getuser) {
        this.getuser = getuser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }
}
