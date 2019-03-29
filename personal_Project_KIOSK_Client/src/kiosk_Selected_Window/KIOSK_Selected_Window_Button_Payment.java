package kiosk_Selected_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Payment_Window.KIOSK_Payment_Window;

public class KIOSK_Selected_Window_Button_Payment extends JButton{
	private final int Button_Payment_width = 300;
	private final int Button_Payment_height = 95;
	private KIOSK_Client_Main client_main;
	private KIOSK_Payment_Window payment_window;
	
	// 본 Class (JButton) 을 눌렀을때 payment_window 의 중복 생성을 막고 ClientMain 위치에서 새로 생성하게 하기 위한 flag 변수
	// 아래 변수는 본 클래스 (payment_window 의 생성) 와 KIOSK_Payment_Window_PaymentButtons 클래스 (payment_window 의 닫기) 가 보유하고 있으며
	// 열기 또는 닫기를 수행하였을때 두 클래스의 flag 값은 동일하게 변경되어야한다.
	private boolean payment_window_open_flag;		
	
	public KIOSK_Selected_Window_Button_Payment(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Button_Payment : ===== KIOSK_Button_Payment() 호출 =====");
		this.client_main = client_main;
		this.payment_window = this.client_main.getPayment_window();
		this.setText("결재 진행");
		this.setFont(new Font("휴먼매직체", Font.BOLD, 35));
		this.setPreferredSize(new Dimension(Button_Payment_width, Button_Payment_height));
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(payment_window_open_flag == false) {
					System.out.println("KIOSK_Button_Payment : addActionListener : client_main 에 cart List 를 가져옵니다.");
					List cart = KIOSK_Selected_Window_Button_Payment.this.client_main.getCart();
					System.out.println("KIOSK_Button_Payment : addActionListener : 가져온 cart List 의 길이 : "+cart.size()+"\n (▶ 0 : 장바구니 비었음 알림 띄움 /▶ 1 : KIOSK_Payment_Window 호출");
					if(cart.size()>0) {
						System.out.println("KIOSK_Button_Payment : ===== KIOSK_Button_Payment Clicked =====");
						System.out.println("KIOSK_Button_Payment : 결제를 진행합니다.");
						payment_window = new KIOSK_Payment_Window(KIOSK_Selected_Window_Button_Payment.this.client_main, KIOSK_Selected_Window_Button_Payment.this);
						payment_window_open_flag = true;
						payment_window.setPayment_window_open_flag(payment_window_open_flag);
						System.out.println("KIOSK_Selected_Window_Button_Payment : payment_window 창 생성 후 KIOSK_Payment_Window 의 payment_window_open_flag : "+payment_window_open_flag);
						System.out.println("KIOSK_Selected_Window_Button_Payment : payment_window 창 생성 후 KIOSK_Payment_Window 의 payment_window_open_flag : "+payment_window.isPayment_window_open_flag());
					}else {
						JOptionPane.showMessageDialog(KIOSK_Selected_Window_Button_Payment.this.client_main, "장바구니가 비었습니다.");
					}
				}else {
					payment_window.show();
				}
			}
		});
	}

	
	
	
	
	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public boolean isPayment_window_open_flag() {
		return payment_window_open_flag;
	}

	public void setPayment_window_open_flag(boolean payment_window_open_flag) {
		this.payment_window_open_flag = payment_window_open_flag;
	}

	public KIOSK_Payment_Window getPayment_window() {
		return payment_window;
	}

	public void setPayment_window(KIOSK_Payment_Window payment_window) {
		this.payment_window = payment_window;
	}

	public int getButton_Payment_width() {
		return Button_Payment_width;
	}

	public int getButton_Payment_height() {
		return Button_Payment_height;
	}
	
	
}
