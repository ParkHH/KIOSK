package kiosk_Server_Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import kiosk_Server.KIOSK_ServerThread;

public class KIOSK_Server_login_Controller {
	private KIOSK_ServerThread server_thread;
	private JSONObject obj;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String id;
	private String pw;
	private BufferedWriter buffw;
	
	public KIOSK_Server_login_Controller(KIOSK_ServerThread server_thread, JSONObject obj) {
		System.out.println("KIOSK_Server_login_Controller : ===== KIOSK_Server_login_Controller() ȣ�� =====");
		this.server_thread = server_thread;
		this.obj = obj;
		this.buffw = this.server_thread.getBuffw();
		parsing_obj();
		login_Confirm();
	}
	
	public void parsing_obj() {
		System.out.println("KIOSK_Server_login_Controller : parsing_obj() ȣ��");
		id = obj.get("id").toString();
		pw = obj.get("pw").toString();
	}
	
	
	public void login_Confirm() {
		String sql = "select id, pw from member where id=? and pw=?";
		conn = server_thread.getServer_Main().getDataBase_connection().getConn();
		System.out.println("KIOSK_Server_login_Controller : ServerMain ���� ���� Connection ��ü -> "+this.conn);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			System.out.println("KIOSK_Server_login_Controller : ������ Query�� : "+sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				try {
					System.out.println("KIOSK_Server_login_Controller : login_Confirm() : ��ȸ��� ����");
					StringBuffer sb = new StringBuffer();
					sb.append("{");
					sb.append("\"ResponseType\" : \"login\",");
					sb.append("\"result\" : \"permission\"");
					sb.append("}");
					System.out.println("KIOSK_Server_login_Controller : Client ���� ���� ���� JSON : "+sb.toString());
					System.out.println("KIOSK_Server_login_Controller : Client ���� JSON �� �����մϴ�.");
					buffw.write(sb.toString()+"\n");
					buffw.flush();
					System.out.println("KIOSK_Server_login_Controller : JSON �� ���ۿϷ�.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				try {
					System.out.println("KIOSK_Server_login_Controller : login_Confirm() : ��ȸ��� ����");
					StringBuffer sb = new StringBuffer();
					sb.append("{");
					sb.append("\"ResponseType\" : \"login\",");
					sb.append("\"result\" : \"denied\"");
					sb.append("}");
					System.out.println("KIOSK_Server_login_Controller : Client ���� ���� ���� JSON : "+sb.toString());
					System.out.println("KIOSK_Server_login_Controller : Client ���� JSON �� �����մϴ�.");
					buffw.write(sb.toString()+"\n");
					buffw.flush();
					System.out.println("KIOSK_Server_login_Controller : JSON �� ���ۿϷ�.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
