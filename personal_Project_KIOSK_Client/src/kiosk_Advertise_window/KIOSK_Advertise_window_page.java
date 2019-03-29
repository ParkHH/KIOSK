package kiosk_Advertise_window;

import javax.swing.JFrame;

import kiosk_ClientMain.KIOSK_Client_Main;

public class KIOSK_Advertise_window_page extends JFrame{
	private KIOSK_Client_Main client_main;
	
	public KIOSK_Advertise_window_page(KIOSK_Client_Main client_main) {
		System.out.println("===== KIOSK_Advertise_window_page() »£√‚ =====");
		this.client_main = client_main;
		this.setLocation(650, 180);
		this.setSize(550, 650);
		this.setTitle("Mac Advertise");
		this.setLocationRelativeTo(client_main);
		this.setVisible(true);
	}

	
	
	
	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}
}
