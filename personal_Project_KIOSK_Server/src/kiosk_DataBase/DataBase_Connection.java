package kiosk_DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase_Connection {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "personal_project_java_lecture";
	private String password = "1234";
	private Connection conn;
	
	
	
	public DataBase_Connection() {
		System.out.println("DataBase_Connection : ===== DataBase_Connection() =====");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if(conn != null) {
				System.out.println("DataBase_Connection : ▶ DataBase Connect 성공");
			}else {
				System.out.println("DataBase_Connection : ▶ DataBase Connect 실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
