package kiosk_Payment_List_Box;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Payment_Window.KIOSK_Payment_Window;
import kiosk_Payment_Window.KIOSK_Payment_Window_PaymentList;

public class KIOSK_Payment_List_Box extends JLabel{
	private int KIOSK_Payment_List_Box_WIDTH;
	private int KIOSK_Payment_List_Box_HEIGHT=50;
	KIOSK_Payment_Window payment_window;
	KIOSK_Payment_Window_PaymentList payment_window_list;
	
	public KIOSK_Payment_List_Box(KIOSK_Payment_Window payment_window, KIOSK_Payment_Window_PaymentList payment_list) {
		this.payment_window = payment_window;
		this.payment_window_list = payment_list;
		
		System.out.println("\nKIOSK_Payment_List_Box : ===== KIOSK_Payment_List_Box() 호출 =====");
		System.out.println("KIOSK_Payment_List_Box : KIOSK_Payment_List_Box Class 가 받은 매개변수");
		System.out.println("payment_window : "+this.payment_window);
		System.out.println("payment_window_list : "+this.payment_window_list);
		
		KIOSK_Payment_List_Box_WIDTH = payment_window_list.getKIOSK_Payment_Window_PaymentList_WIDTH()-10;
		
		this.setOpaque(true);
		this.setBackground(new Color(235, 235, 235));
		this.setFont(new Font(null, Font.BOLD, 15));
		this.setPreferredSize(new Dimension(KIOSK_Payment_List_Box_WIDTH, KIOSK_Payment_List_Box_HEIGHT));
		this.setVisible(true);
	}
}
