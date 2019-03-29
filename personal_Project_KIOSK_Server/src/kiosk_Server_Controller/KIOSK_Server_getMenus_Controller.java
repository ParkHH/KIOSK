package kiosk_Server_Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kiosk_DataBase.DataBase_Connection;
import kiosk_DataBase_Closer.Select_SQL_Closer;
import kiosk_Server.KIOSK_ServerThread;

public class KIOSK_Server_getMenus_Controller {
	private KIOSK_ServerThread serverThread;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BufferedWriter buffw;
	private String sql = "select * from products";
	
	//List<String[]> menus_info = new ArrayList<String[]>();
	private String[][] menus_info;

	

	public KIOSK_Server_getMenus_Controller(KIOSK_ServerThread serverThread, String msg) {
		System.out.println("KIOSK_Server_getMenus_Controller : ===== KIOSK_Server_getMenus_Controller() 호출 =====");
		this.serverThread = serverThread;
		getMenus();
	}
	
	// DB 에서 SQL 문 수행을 통해 요청에 맞는 Data 를 가져옴 	
	public void getMenus() {
		try {
			conn = serverThread.getServer_Main().getDataBase_connection().getConn();
			System.out.println("KIOSK_Server_getMenus_Controller : ServerMain 에서 얻어온 Connection 객체 -> "+this.conn);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			System.out.println("\nKIOSK_Server_getMenus_Controller : DataBase 로 부터 SQL 문을 수행하여 Data 를 얻어옵니다.");
			System.out.println("KIOSK_Server_getMenus_Controller : ===== getMenus() columnCount : "+columnCount+" =====");
			rs.last();
			int data_quantity = rs.getRow();
			System.out.println("KIOSK_Server_getMenus_Controller : ▶ 조회된 Data 갯수 : "+data_quantity);
			rs.beforeFirst();
			menus_info = new String[data_quantity][columnCount];
			
			for(int i=0; i<data_quantity; i++) {
				rs.next();
				menus_info[i][0] = rs.getString("seq_product");
				menus_info[i][1] = rs.getString("product_name");
				menus_info[i][2] = rs.getString("product_price");
				menus_info[i][3] = rs.getString("product_description");
				menus_info[i][4] = rs.getString("product_img");
				menus_info[i][5] = rs.getString("product_kind");
			}
			
			System.out.println("KIOSK_Server_getMenus_Controller : ▶ 아래는 DataBase Products Table 내부에 있는 Data 입니다.");
			System.out.println("================================");
			for(int i=0; i<menus_info.length; i++) {
				System.out.println(menus_info[i][0]);
				System.out.println(menus_info[i][1]);
				System.out.println(menus_info[i][2]);
				System.out.println(menus_info[i][3]);
				System.out.println(menus_info[i][4]);
				System.out.println(menus_info[i][5]);
				System.out.println("================================");
			}
			System.out.println("KIOSK_Server_getMenus_Controller : ▶ menus_info[][] 의 길이 : "+menus_info.length);
			create_responseJSON();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// select 문을 수행 후 ResultSet 과 PreparedStatement 를 close() 하는 method 를 불러옴
			Select_SQL_Closer select_sql_closer = new Select_SQL_Closer();
			select_sql_closer.select_sql_close(pstmt, rs);
		}
	}
	
	
	
	// ★ JSON 을 통한 Array 전달에 있어 JSONArray 와 JSONObject 를 사용한다. JSONObejct 는 Map 과 같이 Key-value 쌍으로 되어있고
	// 특성 또한 Map 의 특성을 따른다. 즉, key 값의 중복을 허용치 않고 Value 의 중복은 허용한다.
	// 현재 문제 점은 database 에서 받아온 data 로 구성된 menus_info[][] 가 이차원 배열로 구성되어있고
	// 이차원 배열을 JSONArray 로 표현하기 위해서 반복문을 돌리는 도중 Key 값의 중복이 발생하여 마지막에 추가되는 값으로
	// 생성된 JSONArray 의 value 들이 모두 대체된다.
	// ▶ 따라서 아래 method 의 내용과 같이 JSON 문을 작성하고 그것을 반복하는 방식으로 JSON 에 배열을 표현한다.
	public void create_responseJSON() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"ResponseType\" : \"getMenus\",");
		sb.append("\"Menus\" : [");
		for(int i=0; i<menus_info.length; i++) {
		   		sb.append("{");
		   			sb.append("\"seq_product\" : "+menus_info[i][0]+",");
		   			sb.append("\"product_name\" : \""+menus_info[i][1]+"\",");
		   			sb.append("\"product_price\" : "+menus_info[i][2]+",");
		   			sb.append("\"product_description\" : \""+menus_info[i][3]+"\"");
		   			sb.append("\"product_img\" : \""+menus_info[i][4]+"\"");
		   			sb.append("\"product_kind\" : \""+menus_info[i][5]+"\"");
		   		if(i<menus_info.length-1) {
		   			sb.append("},");
		   		}else {
		   			sb.append("}");
		   		}
		}
		   	sb.append("]");
		sb.append("}");
		
		String response_msg = sb.toString();
		System.out.println("KIOSK_Server_getMenus_Controller : response_getMenus msg Json -> "+response_msg);
		try {
			System.out.println("KIOSK_Server_getMenus_Controller : response_getMenus Json 전송을 시작합니다.");
			buffw = serverThread.getBuffw();
			System.out.println("KIOSK_Server_getMenus_Controller : serverThread 에서 BufferedWriter 를 얻어옵니다 "+buffw);
			buffw.write(response_msg+"\n");
			buffw.flush();
			System.out.println("KIOSK_Server_getMenus_Controller : response_getMenus Json 전송을 완료했습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public KIOSK_ServerThread getServerThread() {
		return serverThread;
	}

	public void setServerThread(KIOSK_ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	public BufferedWriter getBuffw() {
		return buffw;
	}

	public void setBuffw(BufferedWriter buffw) {
		this.buffw = buffw;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[][] getMenus_info() {
		return menus_info;
	}

	public void setMenus_info(String[][] menus_info) {
		this.menus_info = menus_info;
	}
}
