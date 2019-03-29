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
		System.out.println("KIOSK_Server_Payment_Controller : ===== KIOSK_Server_Payment_Controller() ȣ�� =====");
		this.serverThread = serverThread;
		this.obj = obj;
		System.out.println("KIOSK_Server_Payment_Controller : KIOSK_Server_Msg_Distributer ���� ���� serverThread : "+this.serverThread);
		System.out.println("KIOSK_Server_Payment_Controller : KIOSK_Server_Msg_Distributer ���� ���� obj : "+this.obj);
		parsing_obj();
		insert_Data();
	}
	
	public void parsing_obj() {
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() ȣ��");
		JSONArray jsonArray = new JSONArray();
		jsonArray = (JSONArray) obj.get("payment_list");
		
	
		for(int i=0; i<jsonArray.size(); i++) {
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_list : "+jsonArray.get(i));
			
			// �迭���� ������ "key" : "value" ���·� data �� ����Ǿ��ֱ� ������ value �鸸 �����Ͽ� List �� ����ش�.
			JSONObject tmp = (JSONObject) jsonArray.get(i);
			String product_name = tmp.get("product_name").toString();
			String product_kind = tmp.get("product_kind").toString();
			String product_price = tmp.get("product_price").toString();
			String beverage = tmp.get("beverage").toString();
			String side_dish = tmp.get("side_dish").toString();
			String payment_method = tmp.get("payment_method").toString();
			
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : parsing �� �迭�� ������ Ȯ��");
			String[] payment_list = new String[6];							// JSON �� Parsing �Ͽ� JSON ���� �迭 �����͸� ���� �迭
			payment_list[0] = product_name;
			payment_list[1] = product_kind;
			payment_list[2] = product_price;
			payment_list[3] = beverage;
			payment_list[4] = side_dish;
			payment_list[5] = payment_method;
			
			
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_menus List �� ���� payment_list �迭 : "+payment_list);
			System.out.println("===============================");
			for(int a=0; a<payment_list.length; a++) {
				System.out.println("payment_list �迭�� ���� : "+payment_list[a]);
			}
			System.out.println("===============================");
			System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() : payment_menus List �� payment_list �� ��� Data �� ��Ƴ����ϴ�.");
			payment_menus.add(payment_list);
		}
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() payment_menus List �� ���� : "+payment_menus.size());
		System.out.println("KIOSK_Server_Payment_Controller : parsing_obj() payment_menus List ���� ������ ��� ����Ͽ� Ȯ��");
		for(int i=0; i<payment_menus.size(); i++) {
			String[] payment_list= (String[]) payment_menus.get(i);
			System.out.println("payment_menus List ���� �������� Data ���� : "+payment_list);
			System.out.println("==============================");
			for(int a=0; a<payment_list.length; a++) {
				System.out.println(payment_list[a]);
			}
			System.out.println("==============================");
		}
	}
	
	
	public void insert_Data() {
		System.out.println("KIOSK_Server_Payment_Controller : insert_Data() ȣ��");
		String sql = "insert into transaction_history(seq_transaction_history, product_name, product_kind, product_price, beverage, side_dish, payment_method, transaction_time)"
				+ " values(seq_transaction_history.nextval, ?,?,?,?,?,?, systimestamp)";
		Connection conn = serverThread.getServer_Main().getDataBase_connection().getConn();
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : DataBase �� insert �� �����մϴ�.");
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
				System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : Data �Է� ����!!");
				payment_menus.clear();
				System.out.println("KIOSK_Server_Payment_Controller : �Է¼��� �� payment_menus �� ���� : "+payment_menus.size());
			}else {
				System.out.println("KIOSK_Server_Payment_Controller : insert_Data() : Data �Է� ����!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DML_Closer dml_closer = new DML_Closer();
		}
	}
}
