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
	private boolean clientThread_run_flag = true;		// ClientThread �� listen() �� ���� �ݺ��� �����ϴ� ����
	private Socket client;
	String msg;
	
	
	
	public KIOSK_ClientThread(KIOSK_Client_LoginForm client_loginForm, KIOSK_Client_Server_Connecter server_connecter, Socket client) {
		System.out.println("KIOSK_ClientThread : ===== KIOSK_ClientThread() ȣ�� =====");
		this.client_loginForm = client_loginForm;
		this.server_connecter = this.client_loginForm.getServer_connect();		
			// server_conecter ��ü�� client_main �� �����Ǹ鼭 �����̵ǰ� client_Thread �� server_connecter �� �����Ǳ� ���� ������ �ȴ�.
			// ���� client_Thread �� client_main �� ���� Socket �� ���� �� ���ٴ� ���� �ȴ�.
			// ��������� Socket �� ��������� ���� (server_connecter ����) �� server_connecter �� �Ű������� �޾ƿ� server_connecter �� ���� �����;��Ѵ�.
		client_Msg_distributer = new KIOSK_Client_Msg_Distributer(client_loginForm);
		this.client = client;
		try {
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Server �� ���� ���ŵǴ� ��� message �� �����ϴ� method
	public void listen() {
		System.out.println("KIOSK_ClientThread : ===== ClientThread listen() method ���� =====");
		msg = null;;
		try {
			msg = buffr.readLine();
			System.out.println("KIOSK_ClientThread : Server �� ���� ���� msg : "+msg);
			System.out.println("KIOSK_ClientThread : responseType �� �˸´� Controller �� �����ִ� Client_Distributer �� ȣ���ϰ� ���� �Ѱ��ݴϴ�.");
			// Server �� ���� ���ŵ� message �� �ؼ��Ͽ� ���� ������ ���� Controller ���� �й��ϴ� KIOSK_Client_Msg_Distributer �� ȣ��
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
