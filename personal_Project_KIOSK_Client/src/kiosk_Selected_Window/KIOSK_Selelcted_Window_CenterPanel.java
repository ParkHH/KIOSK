package kiosk_Selected_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kiosk_FoodBox.KIOSK_FoodBox;

// 상품의 목록을 표시할 Panel
public class KIOSK_Selelcted_Window_CenterPanel  extends JPanel{
	private final int selected_window_CenterPanel_width = KIOSK_Client_Main.WIDTH;
	private final int selected_window_CenterPanel_height = 600;
	private final Color selected_window_CenterPanel_background = Color.RED;
	private KIOSK_Client_Main client_main;
	private KIOSK_Client_getMenus_Controller getMenus_controller;
	
	public KIOSK_Selelcted_Window_CenterPanel(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Selelcted_Window_CenterPanel : ===== KIOSK_Selelcted_Window_CenterPanel() 호출 =====");
		this.client_main = client_main;
		
		//this.setLayout(new GridLayout(3,3));
		this.setPreferredSize(new Dimension(selected_window_CenterPanel_width, selected_window_CenterPanel_height));
		this.setBackground(selected_window_CenterPanel_background);
		this.setVisible(true);
	}
	
	
}
