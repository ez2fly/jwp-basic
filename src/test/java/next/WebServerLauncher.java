package next;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;

public class WebServerLauncher {
	private static final Logger logger = LoggerFactory.getLogger(WebServerLauncher.class);
	
	public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        
        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");
        
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        
        // 160710_문의 : 여기서 더미데이터를 넣어도 실제 접속시에는 DataBase가 없는 것으로 나옴. 이유는??
//  		DataBase.addUser(new User("ez2fly", "1234", "김봉남", "ez2fly@naver.com"));
//  		DataBase.addUser(new User("kkang18x", "1234", "남상칠", "kkang18x@nate.com"));
//  		logger.debug("DataBase Count : " + DataBase.getDataCount());
      		
        tomcat.getServer().await();
    }
}
