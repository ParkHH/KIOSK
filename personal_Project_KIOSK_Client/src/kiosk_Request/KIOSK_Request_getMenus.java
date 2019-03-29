package kiosk_Request;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;

public class KIOSK_Request_getMenus {
	private KIOSK_Client_Main client_main;
	private KIOSK_ClientThread client_Thread;
	private BufferedWriter buffw;
	private String request_msg;
	
	public KIOSK_Request_getMenus(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Request_getMenus : ===== KIOSK_Request_getMenus() ȣ�� =====");
		this.client_main = client_main;
		this.client_Thread = client_main.getClient_Thread();
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"RequestType\" : \"getMenus\"");
		sb.append("}");
		request_msg = sb.toString();
		System.out.println("KIOSK_Request_getMenus : request_getMenus msg Json -> "+request_msg);
		send_request_msg_to_Server();
	}
	
	public void send_request_msg_to_Server() {
		System.out.println("KIOSK_Request_getMenus : ===== send_to_Server() method ȣ��  ===== ");
		try {
			client_Thread.setClient_main(client_main);
			System.out.println("KIOSK_Request_getMenus : request_msg �� Server �� ����(��û) �մϴ�.");
			buffw = client_main.getClient_Thread().getBuffw();
			System.out.println("KIOSK_Request_getMenus : kiosk_main �� �ִ� ClientThread �� ���� BufferedWriter �� �����ɴϴ� : "+buffw);
			buffw.write(request_msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	

	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public BufferedWriter getBuffw() {
		return buffw;
	}

	public void setBuffw(BufferedWriter buffw) {
		this.buffw = buffw;
	}

	public String getRequest_msg() {
		return request_msg;
	}

	public void setRequest_msg(String request_msg) {
		this.request_msg = request_msg;
	}
	
	
	
	
}
