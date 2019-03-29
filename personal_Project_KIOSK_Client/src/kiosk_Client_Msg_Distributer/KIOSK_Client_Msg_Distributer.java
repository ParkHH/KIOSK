package kiosk_Client_Msg_Distributer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kiosk_Client_Controller.KIOSK_Client_login_Controller;
import kiosk_login.KIOSK_Client_LoginForm;

public class KIOSK_Client_Msg_Distributer {
	private KIOSK_Client_LoginForm client_loginForm;
	private KIOSK_Client_Main client_main;
	private String msg;
	private KIOSK_Client_login_Controller login_Controller;
	private KIOSK_Client_getMenus_Controller getMenus_Controller;
	
	public KIOSK_Client_Msg_Distributer(KIOSK_Client_LoginForm client_loginForm) {
		System.out.println("KIOSK_Client_Msg_Distributer : ===== KIOSK_Client_Msg_Distributer() ȣ�� =====");
		this.client_loginForm = client_loginForm;
	}
	
	// Server �� ���� ���� msg �� �ؼ��Ͽ� ��û�� �´� ó���� �����ϴ� Controller �� �����Ѵ�.
	public void distribute_Msg(String msg) {
		System.out.println("KIOSK_Client_Msg_Distributer : msg �ؼ��� �����մϴ�");
		System.out.println("KIOSK_Client_Msg_Distributer : �ؼ��� msg -> "+msg);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(msg);
			String responseType = obj.get("ResponseType").toString();
			System.out.println("KIOSK_Client_Msg_Distributer : msg �� ResponseType �� "+responseType);
			
			if(responseType.equals("login")) {
				System.out.println("KIOSK_Client_Msg_Distributer : ResponseType �� \"login\" �̹Ƿ� KIOSK_Client_login_Controller �� ȣ���մϴ�.");
				login_Controller = new KIOSK_Client_login_Controller(client_loginForm, obj, this);
			}else if(responseType.equals("getMenus")) {;
				System.out.println("KIOSK_Client_Msg_Distributer : ResponseType �� \"getMenus\" �̹Ƿ� KIOSK_Client_getMenus_Controller �� ȣ���մϴ�.");
				System.out.println("KIOSK_Client_Msg_Distributer : KIOSK_Client_Msg_Distributer �� client_main : "+client_main);
				getMenus_Controller = new KIOSK_Client_getMenus_Controller(client_main, obj);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	
	
	public KIOSK_Client_getMenus_Controller getGetMenus_Controller() {
		return getMenus_Controller;
	}

	public void setGetMenus_Controller(KIOSK_Client_getMenus_Controller getMenus_Controller) {
		this.getMenus_Controller = getMenus_Controller;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public KIOSK_Client_LoginForm getClient_loginForm() {
		return client_loginForm;
	}

	public void setClient_loginForm(KIOSK_Client_LoginForm client_loginForm) {
		this.client_loginForm = client_loginForm;
	}

	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}
	
	
	
}
