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


// Client_Distributer �� ���� �Ѱܹ��� JSONObject data �� �뵵�� �°� �簡���ϴ� Class
// JSONObject �� Parsing �Ͽ� JSON ������ "Menus" key�� value ���� �迭�� �簡���Ͽ� FoodBox �� ǥ���� �̿��Ͽ����Ѵ�.
public class KIOSK_Client_getMenus_Controller {
	private KIOSK_Client_Main client_main;
	private KIOSK_ClientThread clientThread;
	private JSONObject obj;
	private List<Object> menus = new ArrayList<Object>();
	
	
	public KIOSK_Client_getMenus_Controller(KIOSK_Client_Main client_main, JSONObject obj) {
		System.out.println("KIOSK_Client_getMenus_Controller : ===== KIOSK_Client_getMenus_Controller() ȣ�� =====");
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
			
			// �迭���� ������ "key" : "value" ���·� data �� ����Ǿ��ֱ� ������ value �鸸 �����Ͽ� List �� ����ش�.
			JSONObject tmp = (JSONObject) jsonArray.get(i);
			String seq_product = tmp.get("seq_product").toString();
			String product_name = tmp.get("product_name").toString();
			String product_price = tmp.get("product_price").toString();
			String product_description = tmp.get("product_description").toString();
			String product_img = tmp.get("product_img").toString();
			String product_type = tmp.get("product_kind").toString();
			
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : parsing �� ");
			String[] menu = new String[6];							// JSON �� Parsing �Ͽ� JSON ���� �迭 �����͸� ���� �迭
			menu[0] = seq_product;
			menu[1] = product_name;
			menu[2] = product_price;
			menu[3] = product_description;
			menu[4] = product_img;
			menu[5] = product_type;
			
			
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : menus List �� ���� menu �迭 : "+menu);
			System.out.println("===============================");
			for(int a=0; a<menu.length; a++) {
				System.out.println("menu �迭�� ���� : "+menu[a]);
			}
			System.out.println("===============================");
			System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() : menus List �� jsonArray �� ��� Data �� ��Ƴ����ϴ�.");
			menus.add(menu);
		}
		System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() menus List �� ���� : "+menus.size());
		System.out.println("KIOSK_Client_getMenus_Controller : serverResponseMsgParser() menus List ���� ������ ��� ���");
		for(int i=0; i<menus.size(); i++) {
			String[] menu= (String[]) menus.get(i);
			System.out.println("menus List ���� �������� Data ���� : "+menu);
			System.out.println("==============================");
			for(int a=0; a<menu.length; a++) {
				System.out.println(menu[a]);
			}
			System.out.println("==============================");
		}
		
		System.out.println("KIOSK_Client_getMenus_Controller : Server �� ���� menu ������ ���� ���� �޴� ���� ȭ���� ����մϴ�.");
		add_FoodBox();
	}
	
	
	public void add_FoodBox() {
		System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() : KIOSK_Client_getMenus_Controller ���� ���� menu ���� ��ŭ FoodBox �� �����մϴ�.");
		int food_box_quantity = menus.size();
		System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() : ���� menu ���� : "+food_box_quantity);
		System.out.println("KIOSK_Client_getMenus_Controller : ===== FoodBox �� �����մϴ�. =====");
		for(int i=0; i<food_box_quantity; i++) {
			KIOSK_FoodBox foodBox = new KIOSK_FoodBox(this, i);
			// �� client_main�� selected_window �� null ���� ��ϴ�.
			// null ���� �ߴ� ��찡 �ְ� �׷��� ���� ���� ��Ÿ���ϴ�.
			System.out.println("KIOSK_Client_getMenus_Controller : add_FoodBox() client_main.getSelected_window() : "+client_main.getSelected_window());
			client_main.getSelected_window().getSelected_window_CenterPanel().add(foodBox);
			
		}
		client_main.getSelected_window().getSelected_window_CenterPanel().updateUI();
		System.out.println("KIOSK_Selelcted_Window_CenterPanel : FoodBox ���� �Ϸ�!");
	
		
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
