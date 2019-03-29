package kiosk_Request;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;

import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Payment_Window.KIOSK_Payment_Window;

public class KIOSK_Request_Insert_PaymentData {
	KIOSK_Payment_Window payment_window;
	KIOSK_ClientThread client_thread;
	BufferedWriter buffw;
	
	public KIOSK_Request_Insert_PaymentData(KIOSK_Payment_Window payment_window, KIOSK_ClientThread client_thread) {
		System.out.println("\nKIOSK_Request_Insert_PaymentData : ===== KIOSK_Request_Insert_PaymentData() 호출 =====");
		this.payment_window = payment_window;
		this.client_thread = client_thread;
		this.buffw = this.client_thread.getBuffw();
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Request_Insert_PaymentData 에서 받아온 clientThread : "+this.client_thread);
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Request_Insert_PaymentData 에서 받아온 BufferedWriter : "+this.client_thread.getBuffw());
		creaste_reqeustJSON();
	}
	
	public void creaste_reqeustJSON() {
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_reqeustJSON() 호출");
		List cart = payment_window.getCart();
		String payment_method = payment_window.getPayment_method();
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Payment_Window 에서 가져온 cart List : "+cart);
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Payment_Window 에서 가져온 payment_method : "+payment_method);
		// cart 안의 내용을 확인합니다.
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : 가져온 cart List 의 내용을 확인합니다.");
		System.out.println("==============================================================");
		for(int i=0; i<cart.size(); i++) {
			String[] cart_list = (String[]) cart.get(i);
			for(int a=0; a<cart_list.length; a++) {
				System.out.println(cart_list[a]);
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
		}
		System.out.println("==============================================================");
		
		
		// JSON 을 작성한다.
		// 현재 상품 데이터를 json 의 2차원 배열형태로 변환해야한다.
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : JSON 을 작성합니다.");
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"RequestType\" : "+"\"payment\",");
		sb.append("\"payment_list\" : [");
		for(int i=0; i<cart.size(); i++) {
			String[] cart_list = (String[]) cart.get(i);
									sb.append("{");
									sb.append("\"product_name\" : "+"\""+cart_list[0]+"\",");
									sb.append("\"product_kind\" : "+"\""+cart_list[1]+"\",");
									sb.append("\"product_price\" : "+"\""+cart_list[2]+"\",");
									sb.append("\"beverage\" : "+"\""+cart_list[3]+"\",");
									sb.append("\"side_dish\" : "+"\""+cart_list[4]+"\",");
									sb.append("\"payment_method\" : "+"\""+payment_method+"\"");
									if(i < cart.size()-1) {
										sb.append("},");
									}else {
										sb.append("}");
									}
		}
		sb.append("]}");
		
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : 작성한 JSON 을 확인합니다.");
		System.out.println(sb.toString());
		
		try {
			System.out.println("KIOSK_Request_Insert_PaymentData : 작성한 JSON 을 Server 에 전송합니다.");
			buffw.write(sb.toString()+"\n");
			buffw.flush();
			System.out.println("KIOSK_Request_Insert_PaymentData : JSON 전송완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
