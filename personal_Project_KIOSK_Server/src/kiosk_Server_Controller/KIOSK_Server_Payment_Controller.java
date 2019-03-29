package kiosk_Server_Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kiosk_DataBase_Closer.DML_Closer;
import kiosk_Server.KIOSK_ServerThread;

public class KIOSK_Server_Payment_Controller {
	KIOSK_ServerThread serverThread;
	JSONObject obj;
	List payment_menus = new ArrayList();
	
	public KIOSK_Server_Payment_Controller(KIOSK_ServerThread serverThread, JSONObject obj) {
		System.out.println("KIOSK_Server_Payment_Controller : ===== KIOSK_Server_Payment_Controller() 호출 =====");
		this.serverThread = serverThread;
		this.obj = obj;
		System.out.println("KIOSK_Server_Payment_Controller : KIOSK_Server_Msg_Distributer 에서 받은 serverThread : "+this.serverThread);
		System.out.println("KIOSK_Server_Payment_Controller : KIOSK_Server_Msg_Distributer 에서 받은 obj : "+this.obj);
		parsing_obj();
		insert_Data();
	}
	
	public void parsing_obj() {
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() 호출");
		JSONArray jsonArray = new JSONArray();
		jsonArray = (JSONArray) obj.get("payment_list");
		
	
		for(int i=0; i<jsonArray.size(); i++) {
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_list : "+jsonArray.get(i));
			
			// 배열에는 여전히 "key" : "value" 형태로 data 가 저장되어있기 때문에 value 들만 추출하여 List 에 담아준다.
			JSONObject tmp = (JSONObject) jsonArray.get(i);
			String product_name = tmp.get("product_name").toString();
			String product_kind = tmp.get("product_kind").toString();
			String product_price = tmp.get("product_price").toString();
			String beverage = tmp.get("beverage").toString();
			String side_dish = tmp.get("side_dish").toString();
			String payment_method = tmp.get("payment_method").toString();
			
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : parsing 한 배열의 내용을 확인");
			String[] payment_list = new String[6];							// JSON 을 Parsing 하여 JSON 내부 배열 데이터를 담을 배열
			payment_list[0] = product_name;
			payment_list[1] = product_kind;
			payment_list[2] = product_price;
			payment_list[3] = beverage;
			payment_list[4] = side_dish;
			payment_list[5] = payment_method;
			
			
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_menus List 에 담을 payment_list 배열 : "+payment_list);
			System.out.println("===============================");
			for(int a=0; a<payment_list.length; a++) {
				System.out.println("payment_list 배열의 내용 : "+payment_list[a]);
			}
			System.out.println("===============================");
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_menus List 에 payment_list 의 모든 Data 를 담아놓습니다.");
			payment_menus.add(payment_list);
		}
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() payment_menus List 의 길이 : "+payment_menus.size());
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() payment_menus List 안의 내용을 모두 출력하여 확인");
		for(int i=0; i<payment_menus.size(); i++) {
			String[] payment_list= (String[]) payment_menus.get(i);
			System.out.println("payment_menus List 에서 가져오는 Data 정보 : "+payment_list);
			System.out.println("==============================");
			for(int a=0; a<payment_list.length; a++) {
				System.out.println(payment_list[a]);
			}
			System.out.println("==============================");
		}
	}
	
	
	public void insert_Data() {
		System.out.println("KIOSK_Server_Payment_Controller : insert_Data() 호출");
		String sql = "insert into transaction_history(seq_transaction_history, product_name, product_kind, product_price, beverage, side_dish, payment_method, transaction_time)"
				+ " values(seq_transaction_history.nextval, ?,?,?,?,?,?, systimestamp)";
		Connection conn = serverThread.getServer_Main().getDataBase_connection().getConn();
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : DataBase 에 insert 를 시작합니다.");
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<payment_menus.size(); i++) {
				String[] payment_menu = (String[]) payment_menus.get(i);
				pstmt.setString(1, payment_menu[0]);
				pstmt.setString(2, payment_menu[1]);
				pstmt.setInt(3, Integer.parseInt(payment_menu[2]));
				pstmt.setString(4, payment_menu[3]);
				pstmt.setString(5, payment_menu[4]);
				pstmt.setString(6, payment_menu[5]);
				
				result = pstmt.executeUpdate();
			}
			if(result != 0) {
				System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : Data 입력 성공!!");
				payment_menus.clear();
				System.out.println("KIOSK_Server_Payment_Controller : 입력성공 후 payment_menus 의 길이 : "+payment_menus.size());
			}else {
				System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : Data 입력 실패!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DML_Closer dml_closer = new DML_Closer();
		}
	}
}
