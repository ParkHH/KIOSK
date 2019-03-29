package kiosk_Client_Controller;

import java.lang.reflect.Array;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_FoodBox.KIOSK_FoodBox;
import kiosk_Payment_Window.KIOSK_Payment_Window;
import kiosk_Selected_Window.KIOSK_Selected_Window;
import kiosk_Selected_Window.KIOSK_Selelcted_Window_CenterPanel;


// Client_Distributer 로 부터 넘겨받은 JSONObject data 를 용도에 맞게 재가공하는 Class
// JSONObject 을 Parsing 하여 JSON 내부의 "Menus" key의 value 값을 배열로 재가공하여 FoodBox 의 표현에 이용하여야한다.
public class KIOSK_Client_getMenus_Controller {
	private KIOSK_Client_Main client_main;
	private KIOSK_ClientThread clientThread;
	private JSONObject obj;
	private List<Object> menus = new ArrayList<Object>();
	
	
	public KIOSK_Client_getMenus_Controller(KIOSK_Client_Main client_main, JSONObject obj) {
		System.out.println("KIOSK_Client_getMenus_Controller : ===== KIOSK_Client_getMenus_Controller() 호출 =====");
		this.client_main = client_main;
		System.out.println("KIOSK_Client_getMenus_Controller : client_main : "+client_main);
		this.clientThread = this.client_main.getClient_Thread();
		this.obj = obj;
		serverResponseMsgParser();
	}
	
	public void serverResponseMsgParser() {
		JSONArray jsonArray = new JSONArray();
		jsonArray = (JSONArray) obj.get("Menus");
		
	
		for(int i=0; i<jsonArray.size(); i++) {
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : menu : "+jsonArray.get(i));
			
			// 배열에는 여전히 "key" : "value" 형태로 data 가 저장되어있기 때문에 value 들만 추출하여 List 에 담아준다.
			JSONObject tmp = (JSONObject) jsonArray.get(i);
			String seq_product = tmp.get("seq_product").toString();
			String product_name = tmp.get("product_name").toString();
			String product_price = tmp.get("product_price").toString();
			String product_description = tmp.get("product_description").toString();
			String product_img = tmp.get("product_img").toString();
			String product_type = tmp.get("product_kind").toString();
			
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : parsing 한 ");
			String[] menu = new String[6];							// JSON 을 Parsing 하여 JSON 내부 배열 데이터를 담을 배열
			menu[0] = seq_product;
			menu[1] = product_name;
			menu[2] = product_price;
			menu[3] = product_description;
			menu[4] = product_img;
			menu[5] = product_type;
			
			
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : menus List 에 담을 menu 배열 : "+menu);
			System.out.println("===============================");
			for(int a=0; a<menu.length; a++) {
				System.out.println("menu 배열의 내용 : "+menu[a]);
			}
			System.out.println("===============================");
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : menus List 에 jsonArray 의 모든 Data 를 담아놓습니다.");
			menus.add(menu);
		}
		System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() menus List 의 길이 : "+menus.size());
		System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() menus List 안의 내용을 모두 출력");
		for(int i=0; i<menus.size(); i++) {
			String[] menu= (String[]) menus.get(i);
			System.out.println("menus List 에서 가져오는 Data 정보 : "+menu);
			System.out.println("==============================");
			for(int a=0; a<menu.length; a++) {
				System.out.println(menu[a]);
			}
			System.out.println("==============================");
		}
		
		System.out.println("KIOSK_Client_getMenus_Controller : Server 로 부터 menu 갯수를 얻어온 다음 메뉴 선택 화면을 출력합니다.");
		add_FoodBox();
	}
	
	
	public void add_FoodBox() {
		System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() : KIOSK_Client_getMenus_Controller 에서 얻은 menu 갯수 만큼 FoodBox 를 생성합니다.");
		int food_box_quantity = menus.size();
		System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() : 얻어온 menu 갯수 : "+food_box_quantity);
		System.out.println("KIOSK_Client_getMenus_Controller : ===== FoodBox 를 생성합니다. =====");
		for(int i=0; i<food_box_quantity; i++) {
			KIOSK_FoodBox foodBox = new KIOSK_FoodBox(this, i);
			// ★ client_main의 selected_window 가 null 값이 뜹니다.
			// null 값이 뜨는 경우가 있고 그렇지 않은 경우로 나타납니다.
			System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() client_main.getSelected_window() : "+client_main.getSelected_window());
			client_main.getSelected_window().getSelected_window_CenterPanel().add(foodBox);
			
		}
		client_main.getSelected_window().getSelected_window_CenterPanel().updateUI();
		System.out.println("KIOSK_Selelcted_Window_CenterPanel : FoodBox 생성 완료!");
	
		
	}
	

	
	
	
	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public KIOSK_ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(KIOSK_ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public List<Object> getMenus() {
		return menus;
	}

	public void setMenus(List<Object> menus) {
		this.menus = menus;
	}
	
	
	
}
