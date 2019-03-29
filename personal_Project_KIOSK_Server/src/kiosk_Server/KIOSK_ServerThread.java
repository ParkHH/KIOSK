package kiosk_Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import kiosk_Server_Msg_Distributer.KIOSK_Server_Msg_Distributer;

public class KIOSK_ServerThread extends Thread{
	private BufferedWriter buffw;
	private BufferedReader buffr;
	private KIOSK_Server_Main server_Main;
	private boolean serverThread_run_flag = true;
	private Socket client;
	
	public KIOSK_ServerThread(KIOSK_Server_Main server_Main, Socket client) {
		System.out.println("KIOSK_ServerThread : ===== KIOSK_ServerThread() 호출 =====");
		this.server_Main = server_Main;
		this.client = client;
		try {
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void listen() {
		System.out.println("KIOSK_ServerThread : ===== ServerThread listen() method 동작 =====");
		Vector<KIOSK_ServerThread> serverThread_list = server_Main.getServerThread_list();
		try {
			String msg = buffr.readLine();
			System.out.println("KIOSK_ServerThread : Client 로 부터 받은 msg -> "+ msg);
			KIOSK_Server_Msg_Distributer msg_Distributer = new KIOSK_Server_Msg_Distributer(this, msg);
		} catch (IOException e) {
			e.printStackTrace();
			server_Main.getServerThread_list().remove(this);
			server_Main.getServer_ta_log().append("KIOSK_ServerThread : 접속자가 퇴장하였습니다.\n");
			server_Main.getServer_ta_log().append("KIOSK_ServerThread : 남은 접속자 수 : "+serverThread_list.size()+"\n");
			serverThread_run_flag = false;
		}
	}
	
	
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		while(serverThread_run_flag) {
			listen();
		}
	}

	
	
	
	

	public Socket getClient() {
		return client;
	}


	public void setClient(Socket client) {
		this.client = client;
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


	public KIOSK_Server_Main getServer_Main() {
		return server_Main;
	}


	public void setServer_Main(KIOSK_Server_Main server_Main) {
		this.server_Main = server_Main;
	}


	public boolean isServerThread_run_flag() {
		return serverThread_run_flag;
	}


	public void setServerThread_run_flag(boolean serverThread_run_flag) {
		this.serverThread_run_flag = serverThread_run_flag;
	}
	
	
	
	
}
