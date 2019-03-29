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
		System.out.println("\nKIOSK_Request_Insert_PaymentData : ===== KIOSK_Request_Insert_PaymentData() ȣ�� =====");
		this.payment_window = payment_window;
		this.client_thread = client_thread;
		this.buffw = this.client_thread.getBuffw();
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Request_Insert_PaymentData ���� �޾ƿ� clientThread : "+this.client_thread);
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Request_Insert_PaymentData ���� �޾ƿ� BufferedWriter : "+this.client_thread.getBuffw());
		creaste_reqeustJSON();
	}
	
	public void creaste_reqeustJSON() {
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_reqeustJSON() ȣ��");
		List cart = payment_window.getCart();
		String payment_method = payment_window.getPayment_method();
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Payment_Window ���� ������ cart List : "+cart);
		System.out.println("KIOSK_Request_Insert_PaymentData : KIOSK_Payment_Window ���� ������ payment_method : "+payment_method);
		// cart ���� ������ Ȯ���մϴ�.
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : ������ cart List �� ������ Ȯ���մϴ�.");
		System.out.println("==============================================================");
		for(int i=0; i<cart.size(); i++) {
			String[] cart_list = (String[]) cart.get(i);
			for(int a=0; a<cart_list.length; a++) {
				System.out.println(cart_list[a]);
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
		}
		System.out.println("==============================================================");
		
		
		// JSON �� �ۼ��Ѵ�.
		// ���� ��ǰ �����͸� json �� 2���� �迭���·� ��ȯ�ؾ��Ѵ�.
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : JSON �� �ۼ��մϴ�.");
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
		
		System.out.println("KIOSK_Request_Insert_PaymentData : creaste_JSON() : �ۼ��� JSON �� Ȯ���մϴ�.");
		System.out.println(sb.toString());
		
		try {
			System.out.println("KIOSK_Request_Insert_PaymentData : �ۼ��� JSON �� Server �� �����մϴ�.");
			buffw.write(sb.toString()+"\n");
			buffw.flush();
			System.out.println("KIOSK_Request_Insert_PaymentData : JSON ���ۿϷ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
