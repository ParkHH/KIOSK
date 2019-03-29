package kiosk_Selected_Window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import kiosk_ClientMain.KIOSK_Client_Main;

public class KIOSK_Selected_Window extends JPanel{
	public static final int KIOSK_Selected_Window_WIDTH = KIOSK_Client_Main.KIOSK_Client_Main_WIDTH;
	public static final int KIOSK_Selected_Window_HEIGHT = KIOSK_Client_Main.KIOSK_Client_Main_HEIGHT;
	
	// KIOSK_Selected_window 에 붙는 Panel 들
	private KIOSK_Selelcted_Window_TopPanel selected_window_TopPanel;
	private KIOSK_Selelcted_Window_CenterPanel selected_window_CenterPanel;
	private KIOSK_Selelcted_Window_BottomPanel selected_window_BottomPanel;
	private KIOSK_Client_Main client_main;		// client_main 의 변수들을 사용하기 위해 얻어옴
	private JScrollPane scroll;							// selected_window_CenterPanel 의 scroll 적용을 위한 scroll
	

	public KIOSK_Selected_Window(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Selected_Window : ===== KIOSK_Selected_Window() 호출 =====");
		this.client_main = client_main;
		selected_window_TopPanel = new KIOSK_Selelcted_Window_TopPanel(this.client_main);		
		selected_window_CenterPanel = new KIOSK_Selelcted_Window_CenterPanel(this.client_main);
		scroll = new JScrollPane(selected_window_CenterPanel);
		selected_window_BottomPanel = new KIOSK_Selelcted_Window_BottomPanel(this.client_main);
		
		this.setLayout(new BorderLayout());
		this.add(selected_window_TopPanel, BorderLayout.NORTH);
		this.add(scroll);
		this.add(selected_window_BottomPanel, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(KIOSK_Selected_Window_WIDTH, KIOSK_Selected_Window_HEIGHT));
		this.setVisible(true);
	}


	
	
	public KIOSK_Selelcted_Window_TopPanel getSelected_window_TopPanel() {
		return selected_window_TopPanel;
	}


	public void setSelected_window_TopPanel(KIOSK_Selelcted_Window_TopPanel selected_window_TopPanel) {
		this.selected_window_TopPanel = selected_window_TopPanel;
	}


	public KIOSK_Selelcted_Window_CenterPanel getSelected_window_CenterPanel() {
		return selected_window_CenterPanel;
	}


	public void setSelected_window_CenterPanel(KIOSK_Selelcted_Window_CenterPanel selected_window_CenterPanel) {
		this.selected_window_CenterPanel = selected_window_CenterPanel;
	}


	public KIOSK_Selelcted_Window_BottomPanel getSelected_window_BottomPanel() {
		return selected_window_BottomPanel;
	}


	public void setSelected_window_BottomPanel(KIOSK_Selelcted_Window_BottomPanel selected_window_BottomPanel) {
		this.selected_window_BottomPanel = selected_window_BottomPanel;
	}


	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}


	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}


	public static int getKioskSelectedWindowWidth() {
		return KIOSK_Selected_Window_WIDTH;
	}


	public static int getKioskSelectedWindowHeight() {
		return KIOSK_Selected_Window_HEIGHT;
	}
	
	
}
