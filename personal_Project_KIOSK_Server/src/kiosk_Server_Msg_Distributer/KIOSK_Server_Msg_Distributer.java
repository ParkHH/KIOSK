package kiosk_Server_Msg_Distributer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kiosk_Server.KIOSK_ServerThread;
import kiosk_Server_Controller.KIOSK_Server_Payment_Controller;
import kiosk_Server_Controller.KIOSK_Server_getMenus_Controller;
import kiosk_Server_Controller.KIOSK_Server_login_Controller;


// �� Ŭ������ ServerThread ���� listen �� Client �� JSON ��û���� �ؼ��Ͽ� 
// ��û Type �� ���� �ش� �۾��� �����ϴ� Controller ���� ó���� Data �� �Ѱ��ִ� ������ �ϴ� Ŭ�����Դϴ�.
// ��� Controller �� �̰����� ����(new) �˴ϴ�.
// �ش� Class �� �����ڴ� Main �� �����Ͽ� Connection ��ü��, socket �� ������������ ServerThread �� �̿��Ѵ�.
public class KIOSK_Server_Msg_Distributer {
	private KIOSK_ServerThread serverThread;
	private String msg;
	
	public KIOSK_Server_Msg_Distributer(KIOSK_ServerThread serverThread, String msg) {
		System.out.println("KIOSK_Server_Msg_Distributer : ===== KIOSK_Server_Msg_Distributer() ȣ�� =====");
		this.serverThread = serverThread;
		this.msg = msg;
		distribute_Msg();
	}
	
	// Client �� ���� ���� msg �� �ؼ��Ͽ� ��û�� �´� ó���� �����ϴ� Controller �� �����Ѵ�.
	public void distribute_Msg() {
		System.out.println("KIOSK_Server_Msg_Distributer : msg �ؼ��� �����մϴ�");
		System.out.println("KIOSK_Server_Msg_Distributer : �ؼ��� msg -> "+msg);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(msg);
			String requestType = obj.get("RequestType").toString();
			System.out.println("KIOSK_Server_Msg_Distributer : msg �� RequestType �� "+requestType);
			
			if(requestType.equals("login")) {
				System.out.println("KIOSK_Server_Msg_Distributer : requestType �� \"login\" �̹Ƿ� KIOSK_Server_login_Controller �� ȣ���մϴ�.");
				KIOSK_Server_login_Controller login_Controller = new KIOSK_Server_login_Controller(serverThread, obj);
			}else if(requestType.equals("getMenus")) {
				System.out.println("KIOSK_Server_Msg_Distributer : requestType �� \"getMenus\" �̹Ƿ� KIOSK_Server_getMenus_Controller �� ȣ���մϴ�.");
				KIOSK_Server_getMenus_Controller getMenus_Controller = new KIOSK_Server_getMenus_Controller(serverThread, msg);
			}else if(requestType.equals("payment")){
				System.out.println("KIOSK_Server_Msg_Distributer : requestType �� \"payment\" �̹Ƿ� KIOSK_Server_Payment_Controller �� ȣ���մϴ�.");
				KIOSK_Server_Payment_Controller payment_controller = new KIOSK_Server_Payment_Controller(serverThread, obj);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
