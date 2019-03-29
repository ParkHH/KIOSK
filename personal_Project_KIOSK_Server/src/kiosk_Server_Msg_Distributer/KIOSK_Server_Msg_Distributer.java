package kiosk_Server_Msg_Distributer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kiosk_Server.KIOSK_ServerThread;
import kiosk_Server_Controller.KIOSK_Server_Payment_Controller;
import kiosk_Server_Controller.KIOSK_Server_getMenus_Controller;
import kiosk_Server_Controller.KIOSK_Server_login_Controller;


// 본 클래스는 ServerThread 에서 listen 한 Client 의 JSON 요청문을 해석하여 
// 요청 Type 에 따라 해당 작업을 진행하는 Controller 에게 처리할 Data 를 넘겨주는 역할을 하는 클래스입니다.
// 모든 Controller 는 이곳에서 생성(new) 됩니다.
// 해당 Class 의 생성자는 Main 에 접근하여 Connection 객체와, socket 을 가져오기위해 ServerThread 를 이용한다.
public class KIOSK_Server_Msg_Distributer {
	private KIOSK_ServerThread serverThread;
	private String msg;
	
	public KIOSK_Server_Msg_Distributer(KIOSK_ServerThread serverThread, String msg) {
		System.out.println("KIOSK_Server_Msg_Distributer : ===== KIOSK_Server_Msg_Distributer() 호출 =====");
		this.serverThread = serverThread;
		this.msg = msg;
		distribute_Msg();
	}
	
	// Client 로 부터 받은 msg 를 해석하여 요청에 맞는 처리를 진행하는 Controller 를 실행한다.
	public void distribute_Msg() {
		System.out.println("KIOSK_Server_Msg_Distributer : msg 해석을 시작합니다");
		System.out.println("KIOSK_Server_Msg_Distributer : 해석할 msg -> "+msg);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(msg);
			String requestType = obj.get("RequestType").toString();
			System.out.println("KIOSK_Server_Msg_Distributer : msg 의 RequestType 은 "+requestType);
			
			if(requestType.equals("login")) {
				System.out.println("KIOSK_Server_Msg_Distributer : requestType 이 \"login\" 이므로 KIOSK_Server_login_Controller 를 호출합니다.");
				KIOSK_Server_login_Controller login_Controller = new KIOSK_Server_login_Controller(serverThread, obj);
			}else if(requestType.equals("getMenus")) {
				System.out.println("KIOSK_Server_Msg_Distributer : requestType 이 \"getMenus\" 이므로 KIOSK_Server_getMenus_Controller 를 호출합니다.");
				KIOSK_Server_getMenus_Controller getMenus_Controller = new KIOSK_Server_getMenus_Controller(serverThread, msg);
			}else if(requestType.equals("payment")){
				System.out.println("KIOSK_Server_Msg_Distributer : requestType 이 \"payment\" 이므로 KIOSK_Server_Payment_Controller 를 호출합니다.");
				KIOSK_Server_Payment_Controller payment_controller = new KIOSK_Server_Payment_Controller(serverThread, obj);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
