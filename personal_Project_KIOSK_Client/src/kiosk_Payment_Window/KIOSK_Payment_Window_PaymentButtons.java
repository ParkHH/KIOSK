package kiosk_Payment_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Selected_Window.KIOSK_Selected_Window_Button_Payment;

public class KIOSK_Payment_Window_PaymentButtons extends JPanel{
	private int KIOSK_Payment_Window_PaymentList_WIDTH = KIOSK_Client_Main.KIOSK_Client_Main_WIDTH;
	private int KIOSK_Payment_Window_PaymentList_HEIGHT= KIOSK_Client_Main.KIOSK_Client_Main_HEIGHT/6;
	private KIOSK_Client_Main client_main;
	private List cart;
	private JPanel p_buttons;
	private JButton bt_payment;
	private JButton bt_cancel;
	private KIOSK_Payment_Window payment_window;
	private KIOSK_Selected_Window_Button_Payment selected_window_button_payment;
	private KIOSK_ClientThread client_thread;
	
	
	public KIOSK_Payment_Window_PaymentButtons(KIOSK_Client_Main client_main, List cart, KIOSK_Payment_Window payment_window) {
		System.out.println("\nKIOSK_Payment_Window_PaymentList : ===== KIOSK_Payment_Window_PaymentList() 호출 =====");
		this.client_main = client_main;
		this.cart = this.client_main.getCart();
		this.payment_window = payment_window;
		this.selected_window_button_payment = this.payment_window.getSelected_window_button_payment();
		this.client_thread = this.client_main.getClient_Thread();
		
		TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.setBorder(border);
		this.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentList_WIDTH, KIOSK_Payment_Window_PaymentList_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		
		create_Buttons();
	}
	
	public void create_Buttons() {
		System.out.println("KIOSK_Payment_Window_PaymentButtons : create_Buttons()");
		Dimension bt_size = new Dimension(300, 100);
		Font bt_font = new Font("휴먼매직체", Font.BOLD, 40);
		
		p_buttons = new JPanel();
		bt_payment = new JButton("결제");
		bt_cancel = new JButton("결제 취소");
		
		p_buttons.setPreferredSize(new Dimension(700, 130));
		bt_payment.setPreferredSize(bt_size);
		bt_cancel.setPreferredSize(bt_size);
		
		bt_payment.setFont(bt_font);
		bt_cancel.setFont(bt_font);
		
		p_buttons.setBackground(Color.WHITE);
		bt_payment.setBackground(Color.WHITE);
		bt_cancel.setBackground(Color.WHITE);
		
		bt_payment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Payment_Window_PaymentButtons bt_payment Clicked!!!!");
				String selected_payment_method = payment_window.getPayment_method();
				if(selected_payment_method.equals("2")) {
					KIOSK_Payment_Window_Paying_CreditCard paying_creditCard = new KIOSK_Payment_Window_Paying_CreditCard(payment_window);
				}else if(selected_payment_method.equals("3")){
					KIOSK_Payment_Window_Paying_Cash paying_cash = new KIOSK_Payment_Window_Paying_Cash(payment_window, client_thread);
				}else if(selected_payment_method.equals("")){
					JOptionPane.showMessageDialog(payment_window, "결제 수단을 선택하세요!");
				}
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Payment_Window_PaymentButtons bt_cancel Clicked!!!!");
				selected_window_button_payment.setPayment_window_open_flag(false);
				payment_window.setPayment_window_open_flag(false);
				System.out.println("KIOSK_Payment_Window_PaymentButtons : payment_window 닫은 후 KIOSK_Selected_Window_Button_Payment 의 payment_window_open_flag : "+selected_window_button_payment.isPayment_window_open_flag());
				System.out.println("KIOSK_Payment_Window_PaymentButtons : payment_window 닫은 후 KIOSK_Payment_Window 의 payment_window_open_flag : "+payment_window.isPayment_window_open_flag());
				payment_window.dispose();
			}
		});
		
		p_buttons.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		p_buttons.add(bt_payment);
		p_buttons.add(bt_cancel);
		this.add(p_buttons);
	}
}
