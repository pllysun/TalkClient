import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class TestSQL {
    @Test
    public void TestSql1() throws SQLException {
        Driver driver=new com.mysql.jdbc.Driver();
        String url="jdbc:mysql://localhost:3306/TalkUserMessage";
        Properties info =new Properties();
        info.setProperty("user","root");
        info.setProperty("password","1125887000f");
        Connection connection =driver.connect(url,info);
        System.out.println(connection);
    }
}
