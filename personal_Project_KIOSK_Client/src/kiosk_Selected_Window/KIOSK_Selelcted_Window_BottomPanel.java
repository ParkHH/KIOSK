package kiosk_Selected_Window;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.Border;

import kiosk_ClientMain.KIOSK_Client_Main;

// 결제 및 취소 버튼위 위치할 Panel
public class KIOSK_Selelcted_Window_BottomPanel extends JPanel{
	private final int selected_window_BottomPanel_width = KIOSK_Client_Main.WIDTH;
	private final int selected_window_BottomPanel_height = 150;
	private final Color selected_window_BottomPanel_background = Color.YELLOW;
	
	private KIOSK_Client_Main client_main;
	private KIOSK_Selected_Window_Button_Payment button_payment;
	private KIOSK_Selected_Window_Button_SelectedCancel button_selectedCancel;
	
	public KIOSK_Selelcted_Window_BottomPanel(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Selelcted_Window_BottomPanel : ===== KIOSK_Selelcted_Window_BottomPanel() 호출 =====");
		this.client_main = client_main;
		this.setPreferredSize(new Dimension(selected_window_BottomPanel_width, selected_window_BottomPanel_height));
		this.setBackground(selected_window_BottomPanel_background);
		create_buttons();
		this.setVisible(true);
	}
	

	// button_payment, button_selectedCancel 을 생성하고 KIOSK_Selelcted_Window_BottomPanel 에 붙힘
	public void create_buttons() {
		button_payment = new KIOSK_Selected_Window_Button_Payment(client_main);
		button_selectedCancel = new KIOSK_Selected_Window_Button_SelectedCancel(client_main);
		KIOSK_Selelcted_Window_BottomPanel.this.add(button_payment);
		KIOSK_Selelcted_Window_BottomPanel.this.add(button_selectedCancel);
	}


	public KIOSK_Selected_Window_Button_Payment getButton_payment() {
		return button_payment;
	}


	public void setButton_payment(KIOSK_Selected_Window_Button_Payment button_payment) {
		this.button_payment = button_payment;
	}


	public KIOSK_Selected_Window_Button_SelectedCancel getButton_selectedCancel() {
		return button_selectedCancel;
	}


	public void setButton_selectedCancel(KIOSK_Selected_Window_Button_SelectedCancel button_selectedCancel) {
		this.button_selectedCancel = button_selectedCancel;
	}


	public int getSelected_window_BottomPanel_width() {
		return selected_window_BottomPanel_width;
	}


	public int getSelected_window_BottomPanel_height() {
		return selected_window_BottomPanel_height;
	}


	public Color getSelected_window_BottomPanel_background() {
		return selected_window_BottomPanel_background;
	}


	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}


	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}
	
	
	
	
}
