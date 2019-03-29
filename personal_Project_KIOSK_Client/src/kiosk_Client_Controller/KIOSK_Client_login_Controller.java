package kiosk_Client_Controller;

import java.net.Socket;

import org.json.simple.JSONObject;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Client_Msg_Distributer.KIOSK_Client_Msg_Distributer;
import kiosk_login.KIOSK_Client_LoginForm;
import kisosk_Server_Connection.KIOSK_Client_Server_Connecter;

public class KIOSK_Client_login_Controller {
	private KIOSK_Client_LoginForm client_loginForm;
	private JSONObject obj;
	private KIOSK_Client_Server_Connecter server_connect;
	private Socket client;
	private KIOSK_ClientThread client_Thread;
	private KIOSK_Client_Msg_Distributer client_Msg_distributer;
	
	public KIOSK_Client_login_Controller(KIOSK_Client_LoginForm client_loginForm, JSONObject obj, KIOSK_Client_Msg_Distributer client_Msg_distributer) {
		System.out.println("\nKIOSK_Client_login_Controller : ===== KIOSK_Client_login_Controller() 호출 =====");
		this.client_loginForm = client_loginForm;
		this.obj = obj;
		this.server_connect = client_loginForm.getServer_connect();
		this.client = this.server_connect.getClient();
		this.client_Thread = this.server_connect.getClientThread();
		this.client_Msg_distributer = client_Msg_distributer;
		System.out.println("KIOSK_Client_login_Controller : client_loginForm 으로 부터 받아온 server_connect : "+this.server_connect);
		System.out.println("KIOSK_Client_login_Controller : client_loginForm 으로 부터 받아온 client : "+this.client);
		System.out.println("KIOSK_Client_login_Controller : client_loginForm 으로 부터 받아온 client_Thread : "+this.client_Thread);
		System.out.println("KIOSK_Client_login_Controller : client_Msg_distributer 으로 부터 받아온 client_Msg_distributer : "+this.client_Msg_distributer);
		parsing_obj();
	}
	
	public void parsing_obj() {
		String result = obj.get("result").toString();
		if(result.equals("permission")){
			System.out.println("KIOSK_Client_login_Controller : parsing_obj() : 로그인이 허가되었습니다.");
			System.out.println("KIOSK_Client_login_Controller : KIOSK_Client_Main 을 생성합니다.");
			KIOSK_Client_Main client_main = new KIOSK_Client_Main(server_connect, client, client_Thread, this);
			client_loginForm.dispose();
		}
	}

	
	
	public KIOSK_Client_LoginForm getClient_loginForm() {
		return client_loginForm;
	}

	public void setClient_loginForm(KIOSK_Client_LoginForm client_loginForm) {
		this.client_loginForm = client_loginForm;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public KIOSK_Client_Server_Connecter getServer_connect() {
		return server_connect;
	}

	public void setServer_connect(KIOSK_Client_Server_Connecter server_connect) {
		this.server_connect = server_connect;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public KIOSK_ClientThread getClient_Thread() {
		return client_Thread;
	}

	public void setClient_Thread(KIOSK_ClientThread client_Thread) {
		this.client_Thread = client_Thread;
	}

	public KIOSK_Client_Msg_Distributer getClient_Msg_distributer() {
		return client_Msg_distributer;
	}

	public void setClient_Msg_distributer(KIOSK_Client_Msg_Distributer client_Msg_distributer) {
		this.client_Msg_distributer = client_Msg_distributer;
	}
	
	
	
	
}
