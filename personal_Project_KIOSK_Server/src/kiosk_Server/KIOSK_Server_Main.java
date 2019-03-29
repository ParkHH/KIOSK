package kiosk_Server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kiosk_DataBase.DataBase_Connection;

public class KIOSK_Server_Main extends JFrame{
	public static final int KIOSK_Server_WIDTH = 400;
	public static final int KIOSK_Server_HEIGHT = 500;
	
	private JPanel server_top_panel;
	private JPanel server_bottom_panel;
	private JTextField server_tf_port;
	private JButton server_bt_runServer;
	private JTextArea server_ta_log;
	private JScrollPane server_scroll;
	
	private ServerSocket serverSocket;
	private Socket client;
	private int port = 9999;
	private Thread run_ServerThread;
	
	private Vector<KIOSK_ServerThread> serverThread_list = new Vector();
	private DataBase_Connection dataBase_connection;
	
	public KIOSK_Server_Main() {
		System.out.println("\nKIOSK_Server_Main : ===== KIOSK_Server_Main() 호출 =====");
		server_top_panel = new JPanel();
		server_bottom_panel = new JPanel();
		server_tf_port = new JTextField();
		server_bt_runServer = new JButton("Server activate");
		server_ta_log = new JTextArea();
		server_scroll = new JScrollPane(server_ta_log);
		run_ServerThread = new Thread() {
			@Override
			public void run() {
				System.out.println("KIOSK_Server_Main : ===== 접속자 감지 Thread 동작 시작 =====");
				runServer();
			}
		};
		
		server_top_panel.setPreferredSize(new Dimension(KIOSK_Server_WIDTH, 40));
		server_bottom_panel.setPreferredSize(new Dimension(KIOSK_Server_WIDTH, 460));
		server_tf_port.setPreferredSize(new Dimension(170, 33));
		server_bt_runServer.setPreferredSize(new Dimension(150, 30));
		server_scroll.setPreferredSize(new Dimension(380, 410));
		
		server_tf_port.setText(Integer.toString(port));
		
		server_top_panel.add(server_tf_port);
		server_top_panel.add(server_bt_runServer);
		
		server_bottom_panel.add(server_scroll);
		server_bt_runServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Server_Main : ===== server_bt_runServer Clicked =====");
				run_ServerThread.start();
				dataBase_connection = new DataBase_Connection();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("KIOSK_Server_Main : ==== Server 를 종료합니다. =====");
				System.exit(0);
			}
		});
		this.add(server_top_panel, BorderLayout.NORTH);
		this.add(server_bottom_panel);
		this.setSize(KIOSK_Server_WIDTH, KIOSK_Server_HEIGHT);
		this.setTitle("KIOSK_Server");
		this.setVisible(true);
		
		//runServer();		//주석을 풀면 현 클래스 실행시 바로 서버가 가동된다.
	}
	
	
	public void runServer() {
		port = Integer.parseInt(server_tf_port.getText());
		try {
			serverSocket = new ServerSocket(port);
			server_ta_log.append("KIOSK_Server_Main : ===== Server 가동 시작 =====\n");
			
			while(true) {
				Socket client = serverSocket.accept();
				String ip = client.getInetAddress().getHostAddress();
				server_ta_log.append("KIOSK_Server_Main : ▶ 접속 감지, 접속자 ip : "+ip+"\n");
				KIOSK_ServerThread serverThread = new KIOSK_ServerThread(this, client);
				serverThread.start();
				serverThread_list.add(serverThread);
				server_ta_log.append("KIOSK_Server_Main : "+serverThread_list.size()+" 명 접속중입니다.\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new KIOSK_Server_Main();
	}


	
	
	
	
	public JPanel getServer_top_panel() {
		return server_top_panel;
	}


	public void setServer_top_panel(JPanel server_top_panel) {
		this.server_top_panel = server_top_panel;
	}


	public JPanel getServer_bottom_panel() {
		return server_bottom_panel;
	}


	public void setServer_bottom_panel(JPanel server_bottom_panel) {
		this.server_bottom_panel = server_bottom_panel;
	}


	public JTextField getServer_tf_port() {
		return server_tf_port;
	}


	public void setServer_tf_port(JTextField server_tf_port) {
		this.server_tf_port = server_tf_port;
	}


	public JButton getServer_bt_runServer() {
		return server_bt_runServer;
	}


	public void setServer_bt_runServer(JButton server_bt_runServer) {
		this.server_bt_runServer = server_bt_runServer;
	}


	public JTextArea getServer_ta_log() {
		return server_ta_log;
	}


	public void setServer_ta_log(JTextArea server_ta_log) {
		this.server_ta_log = server_ta_log;
	}


	public JScrollPane getServer_scroll() {
		return server_scroll;
	}


	public void setServer_scroll(JScrollPane server_scroll) {
		this.server_scroll = server_scroll;
	}


	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	public Socket getClient() {
		return client;
	}


	public void setClient(Socket client) {
		this.client = client;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public Thread getRun_ServerThread() {
		return run_ServerThread;
	}


	public void setRun_ServerThread(Thread run_ServerThread) {
		this.run_ServerThread = run_ServerThread;
	}


	public Vector<KIOSK_ServerThread> getServerThread_list() {
		return serverThread_list;
	}


	public void setServerThread_list(Vector<KIOSK_ServerThread> serverThread_list) {
		this.serverThread_list = serverThread_list;
	}


	public static int getKioskServerWidth() {
		return KIOSK_Server_WIDTH;
	}


	public static int getKioskServerHeight() {
		return KIOSK_Server_HEIGHT;
	}


	public DataBase_Connection getDataBase_connection() {
		return dataBase_connection;
	}


	public void setDataBase_connection(DataBase_Connection dataBase_connection) {
		this.dataBase_connection = dataBase_connection;
	}
	
}
