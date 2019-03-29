package kiosk_ClientMain;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Client_Controller.KIOSK_Client_login_Controller;
import kiosk_Payment_Window.KIOSK_Payment_Window;
import kiosk_Request.KIOSK_Request_getMenus;
import kiosk_Selected_Window.KIOSK_Selected_Window;
import kisosk_Server_Connection.KIOSK_Client_Server_Connecter;

public class KIOSK_Client_Main extends JFrame{
	// KIOSK_Main �� ��µ� ȭ��(JPanel)
	private KIOSK_Selected_Window selected_window;
	private KIOSK_Payment_Window payment_window;
	private KIOSK_Client_Server_Connecter server_connect;
	private String[][] menus_info_from_database;
	private KIOSK_Request_getMenus request_getMenus;
	private Socket client;
	private KIOSK_ClientThread client_Thread;
	private KIOSK_Client_login_Controller client_login_Controller;
	
	// KIOSK_Main �� ���� ũ�� �� ��ġ ����
	public static final int KIOSK_Client_Main_WIDTH = 800;
	public static final int KIOSK_Client_Main_HEIGHT = 900;
	public static final int KIOSK_Client_Main_X = 550;
	public static final int KIOSK_Client_Main_Y = 100;
	
	// KIOSK_Product_Description_Window_Product_buttons ���� ��ٱ��ϴ�� ��ư�� �������� ������������ �迭�� ������ �� List �� ����
	private List<String[]> cart = new ArrayList<String[]>();
	
	
	public KIOSK_Client_Main(KIOSK_Client_Server_Connecter server_connect, Socket client, KIOSK_ClientThread client_Thread, KIOSK_Client_login_Controller client_login_Controller) {
		System.out.println("\nClient_Main : ===== KIOSK_Main() ȣ�� =====");
		// Server �� ������ �õ��ϴ� Class �� ����(new)
		//server_connect = new KIOSK_Client_Server_Connecter(this);
		// serverConnecter �� ���� Socket �� ClientThread �� ���� ������ ����
		this.client = server_connect.getClient();
		this.client_Thread = server_connect.getClientThread();
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// client_Thread �� client_Msg_distributer �� client_main ������ ���� �־��ֱ� ���� ����
		System.out.println("Client_Main : client_Thread �� Client_Msg_distributer �� client_main �� �� class �ν��Ͻ����� �־��ݴϴ�.");
		this.client_login_Controller = client_login_Controller;
		this.client_login_Controller.getClient_Thread().setClient_main(this);
		this.client_login_Controller.getClient_Msg_distributer().setClient_main(this);
		System.out.println("Client_Main : client_Thread �� client_main : "+this.client_login_Controller.getClient_Thread().getClient_main());
		System.out.println("Client_Main : Client_Msg_distributer �� client_main : "+this.client_login_Controller.getClient_Msg_distributer().getClient_main());
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Server �� ������ �õ��� �� ��ü �޴��� �������� ��û�� �ϴ� Class �� ����(new)
		request_getMenus = new KIOSK_Request_getMenus(this);
		KIOSK_Selected_Windows_Add();
		this.setLocation(KIOSK_Client_Main_X, KIOSK_Client_Main_Y);
		this.setSize(new Dimension(KIOSK_Client_Main_WIDTH, KIOSK_Client_Main_HEIGHT));
		this.setLayout(new FlowLayout());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("KIOSK_Main : ===== Client PGM ���� =====");
				KIOSK_Client_Main.this.dispose();
				System.exit(0);
			}
		});
		
		this.setTitle("Mac Order Main");
		this.setVisible(true);
	}
	
	public void KIOSK_Selected_Windows_Add() {
		System.out.println("KIOSK_Client_Main : KIOSK_Selected_Windows_Add() : ClientMain �� selected_window �� �����ϴ�.");
		selected_window = new KIOSK_Selected_Window(this);
		this.add(selected_window);
	}

	
	
	
	
	public KIOSK_Selected_Window getSelected_window() {
		return selected_window;
	}

	public void setSelected_window(KIOSK_Selected_Window selected_window) {
		this.selected_window = selected_window;
	}

	public KIOSK_Payment_Window getPayment_window() {
		return payment_window;
	}

	public void setPayment_window(KIOSK_Payment_Window payment_window) {
		this.payment_window = payment_window;
	}

	public KIOSK_Client_Server_Connecter getServer_connect() {
		return server_connect;
	}

	public void setServer_connect(KIOSK_Client_Server_Connecter server_connect) {
		this.server_connect = server_connect;
	}

	public String[][] getMenus_info_from_database() {
		return menus_info_from_database;
	}

	public void setMenus_info_from_database(String[][] menus_info_from_database) {
		this.menus_info_from_database = menus_info_from_database;
	}

	public KIOSK_Request_getMenus getRequest_getMenus() {
		return request_getMenus;
	}

	public void setRequest_getMenus(KIOSK_Request_getMenus request_getMenus) {
		this.request_getMenus = request_getMenus;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public KIOSK_ClientThread getClient_Thread() {
		return client_Thread;
	}

	public void setClient_Thread(KIOSK_ClientThread client_Thread) {
		this.client_Thread = client_Thread;
	}

	public List<String[]> getCart() {
		return cart;
	}

	public void setCart(List<String[]> cart) {
		this.cart = cart;
	}

	public static int getKioskClientMainWidth() {
		return KIOSK_Client_Main_WIDTH;
	}

	public static int getKioskClientMainHeight() {
		return KIOSK_Client_Main_HEIGHT;
	}

	public static int getKioskClientMainX() {
		return KIOSK_Client_Main_X;
	}

	public static int getKioskClientMainY() {
		return KIOSK_Client_Main_Y;
	}
	
	

	
}
