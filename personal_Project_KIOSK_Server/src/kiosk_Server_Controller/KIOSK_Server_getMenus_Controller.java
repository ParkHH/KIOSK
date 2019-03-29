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
		System.out.println("KIOSK_Server_getMenus_Controller : ===== KIOSK_Server_getMenus_Controller() ȣ�� =====");
		this.serverThread = serverThread;
		getMenus();
	}
	
	// DB ���� SQL �� ������ ���� ��û�� �´� Data �� ������ 	
	public void getMenus() {
		try {
			conn = serverThread.getServer_Main().getDataBase_connection().getConn();
			System.out.println("KIOSK_Server_getMenus_Controller : ServerMain ���� ���� Connection ��ü -> "+this.conn);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			System.out.println("\nKIOSK_Server_getMenus_Controller : DataBase �� ���� SQL ���� �����Ͽ� Data �� ���ɴϴ�.");
			System.out.println("KIOSK_Server_getMenus_Controller : ===== getMenus() columnCount : "+columnCount+" =====");
			rs.last();
			int data_quantity = rs.getRow();
			System.out.println("KIOSK_Server_getMenus_Controller : �� ��ȸ�� Data ���� : "+data_quantity);
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
			
			System.out.println("KIOSK_Server_getMenus_Controller : �� �Ʒ��� DataBase Products Table ���ο� �ִ� Data �Դϴ�.");
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
			System.out.println("KIOSK_Server_getMenus_Controller : �� menus_info[][] �� ���� : "+menus_info.length);
			create_responseJSON();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// select ���� ���� �� ResultSet �� PreparedStatement �� close() �ϴ� method �� �ҷ���
			Select_SQL_Closer select_sql_closer = new Select_SQL_Closer();
			select_sql_closer.select_sql_close(pstmt, rs);
		}
	}
	
	
	
	// �� JSON �� ���� Array ���޿� �־� JSONArray �� JSONObject �� ����Ѵ�. JSONObejct �� Map �� ���� Key-value ������ �Ǿ��ְ�
	// Ư�� ���� Map �� Ư���� ������. ��, key ���� �ߺ��� ���ġ �ʰ� Value �� �ߺ��� ����Ѵ�.
	// ���� ���� ���� database ���� �޾ƿ� data �� ������ menus_info[][] �� ������ �迭�� �����Ǿ��ְ�
	// ������ �迭�� JSONArray �� ǥ���ϱ� ���ؼ� �ݺ����� ������ ���� Key ���� �ߺ��� �߻��Ͽ� �������� �߰��Ǵ� ������
	// ������ JSONArray �� value ���� ��� ��ü�ȴ�.
	// �� ���� �Ʒ� method �� ����� ���� JSON ���� �ۼ��ϰ� �װ��� �ݺ��ϴ� ������� JSON �� �迭�� ǥ���Ѵ�.
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
			System.out.println("KIOSK_Server_getMenus_Controller : response_getMenus Json ������ �����մϴ�.");
			buffw = serverThread.getBuffw();
			System.out.println("KIOSK_Server_getMenus_Controller : serverThread ���� BufferedWriter �� ���ɴϴ� "+buffw);
			buffw.write(response_msg+"\n");
			buffw.flush();
			System.out.println("KIOSK_Server_getMenus_Controller : response_getMenus Json ������ �Ϸ��߽��ϴ�.");
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
