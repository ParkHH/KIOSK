package kiosk_Request;

import java.io.BufferedWriter;
import java.io.IOException;

import kiosk_login.KIOSK_Client_LoginForm;
import kisosk_Server_Connection.KIOSK_Client_Server_Connecter;

public class KIOSK_Request_login {
	private KIOSK_Client_LoginForm client_loginForm;
	private KIOSK_Client_Server_Connecter server_connect;
	private BufferedWriter buffw;
	
	private String id;
	private String pw;
	
	public KIOSK_Request_login(KIOSK_Client_LoginForm client_loginForm) {
		System.out.println("KIOSK_Request_login : ===== KIOSK_Request_login() ȣ�� =====");
		this.client_loginForm = client_loginForm;
		this.server_connect = this.client_loginForm.getServer_connect();
		System.out.println("KIOSK_Request_login : Client_LoginForm ���� ���� ���� client_loginForm : "+client_loginForm);
		System.out.println("KIOSK_Request_login : Client_LoginForm ���� ���� ���� server_connect getClientThread : "+this.server_connect.getClientThread());
		this.buffw = server_connect.getClientThread().getBuffw();
		
		get_insertInfo();
		create_loginJSON();
	}
	
	
	public void get_insertInfo() {
		id = client_loginForm.getTf_admin_id().getText();
		char[] password = client_loginForm.getPf_admin_pw().getPassword();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<password.length; i++) {
			sb.append(password[i]);
		}
		System.out.println("KIOSK_Request_login : Client_LoginForm ���� ���� ���� �Է� ��й�ȣ : "+sb.toString());
		pw = sb.toString();
	}
	
	public void create_loginJSON() {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("\"RequestType\" : "+"\"login\"," );
			sb.append("\"id\" : "+"\""+id+"\",");
			sb.append("\"pw\" : "+"\""+pw+"\"");
			sb.append("}");
			System.out.println("KIOSK_Request_login : create_loginJSON() : �ۼ��� JSON : "+sb.toString());
			System.out.println("KIOSK_Request_login : create_loginJSON() : �ۼ��� JSON �� Server �� �����մϴ�.");
			buffw.write(sb.toString()+"\n");
			buffw.flush();
			System.out.println("KIOSK_Request_login : create_loginJSON() : JSON ���ۿϷ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
