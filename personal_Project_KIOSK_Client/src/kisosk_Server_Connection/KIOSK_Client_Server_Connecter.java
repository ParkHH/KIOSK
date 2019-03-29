package kisosk_Server_Connection;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_login.KIOSK_Client_LoginForm;

public class KIOSK_Client_Server_Connecter {
	private InetAddress server_ip;
	private String server_IP;
	private int server_port = 9999;
	private Socket client;
	private KIOSK_Client_LoginForm client_loginForm;
	private KIOSK_ClientThread clientThread;
	
	
	public KIOSK_Client_Server_Connecter(KIOSK_Client_LoginForm client_loginForm) {
		System.out.println("KIOSK_Server_Connect : ===== KIOSK_Server_Connect() ȣ�� =====");
		this.client_loginForm = client_loginForm;
		getServerIP();
		server_Connection();
	}
	
	// ������ �����ϱ� ���� ip�� ���ϴ� method
	// ���� �ϳ��� ��ǻ�Ϳ��� Client, Server �� ���������� �� LocalHost �� IP �� ���Ѵ�.
	public void getServerIP() {
		try {
			server_ip = server_ip.getLocalHost();
			server_IP = server_ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("KIOSK_Server_Connect : �� KIOSK_Client_PGM ���� ������ IP : "+server_IP);
	}
	
	
	// ������ �����ϴ� method
	public void server_Connection() {
		try {
			System.out.println("KIOSK_Server_Connect : ===== Server �� �����մϴ� =====");
			client = new Socket(server_IP, server_port);
			clientThread = new KIOSK_ClientThread(client_loginForm, this, client);
			System.out.println("KIOSK_Server_Connect : ===== KIOSK_ClientThread �� �����մϴ� =====");
			clientThread.start();
			System.out.println("KIOSK_Server_Connect : ===== KIOSK_ClientThread ������ �����մϴ� =====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	public InetAddress getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(InetAddress server_ip) {
		this.server_ip = server_ip;
	}

	public String getServer_IP() {
		return server_IP;
	}

	public void setServer_IP(String server_IP) {
		this.server_IP = server_IP;
	}

	public int getServer_port() {
		return server_port;
	}

	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public KIOSK_ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(KIOSK_ClientThread clientThread) {
		this.clientThread = clientThread;
	}
	
}
