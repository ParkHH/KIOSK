package kiosk_ClientThread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Msg_Distributer.KIOSK_Client_Msg_Distributer;
import kiosk_login.KIOSK_Client_LoginForm;
import kisosk_Server_Connection.KIOSK_Client_Server_Connecter;

public class KIOSK_ClientThread extends Thread{
	private KIOSK_Client_LoginForm client_loginForm;
	private KIOSK_Client_Main client_main;
	private KIOSK_Client_Server_Connecter server_connecter;
	private KIOSK_Client_Msg_Distributer client_Msg_distributer;
	private BufferedWriter buffw;
	private BufferedReader buffr;
	private boolean clientThread_run_flag = true;		// ClientThread 의 listen() 의 무한 반복을 제어하는 변수
	private Socket client;
	String msg;
	
	
	
	public KIOSK_ClientThread(KIOSK_Client_LoginForm client_loginForm, KIOSK_Client_Server_Connecter server_connecter, Socket client) {
		System.out.println("KIOSK_ClientThread : ===== KIOSK_ClientThread() 호출 =====");
		this.client_loginForm = client_loginForm;
		this.server_connecter = this.client_loginForm.getServer_connect();		
			// server_conecter 객체는 client_main 이 생성되면서 생성이되고 client_Thread 는 server_connecter 가 생성되기 전에 생성이 된다.
			// 따라서 client_Thread 는 client_main 을 통해 Socket 을 받을 수 없다는 말이 된다.
			// 결론적으로 Socket 이 만들어지는 시점 (server_connecter 내부) 인 server_connecter 를 매개변수로 받아와 server_connecter 를 통해 가져와야한다.
		client_Msg_distributer = new KIOSK_Client_Msg_Distributer(client_loginForm);
		this.client = client;
		try {
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Server 로 부터 수신되는 모든 message 를 감지하는 method
	public void listen() {
		System.out.println("KIOSK_ClientThread : ===== ClientThread listen() method 동작 =====");
		msg = null;;
		try {
			msg = buffr.readLine();
			System.out.println("KIOSK_ClientThread : Server 로 부터 받은 msg : "+msg);
			System.out.println("KIOSK_ClientThread : responseType 에 알맞는 Controller 로 보내주는 Client_Distributer 를 호출하고 값을 넘겨줍니다.");
			// Server 로 부터 수신된 message 를 해석하여 응답 종류에 따라 Controller 에게 분배하는 KIOSK_Client_Msg_Distributer 를 호출
			//client_Msg_distributer = new KIOSK_Client_Msg_Distributer(client_loginForm, msg);
			client_Msg_distributer.distribute_Msg(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		while(clientThread_run_flag) {
			listen();
		}
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

	public KIOSK_Client_Server_Connecter getServer_connecter() {
		return server_connecter;
	}

	public void setServer_connecter(KIOSK_Client_Server_Connecter server_connecter) {
		this.server_connecter = server_connecter;
	}

	public KIOSK_Client_Msg_Distributer getClient_Msg_distributer() {
		return client_Msg_distributer;
	}

	public void setClient_Msg_distributer(KIOSK_Client_Msg_Distributer client_Msg_distributer) {
		this.client_Msg_distributer = client_Msg_distributer;
	}

	public BufferedWriter getBuffw() {
		return buffw;
	}

	public void setBuffw(BufferedWriter buffw) {
		this.buffw = buffw;
	}

	public BufferedReader getBuffr() {
		return buffr;
	}

	public void setBuffr(BufferedReader buffr) {
		this.buffr = buffr;
	}

	public boolean isClientThread_run_flag() {
		return clientThread_run_flag;
	}

	public void setClientThread_run_flag(boolean clientThread_run_flag) {
		this.clientThread_run_flag = clientThread_run_flag;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	
	
	
	
	


	
	
}
