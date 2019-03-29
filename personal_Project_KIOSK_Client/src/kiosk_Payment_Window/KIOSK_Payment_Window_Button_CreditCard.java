package kiosk_Payment_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kiosk_ClientMain.KIOSK_Client_Main;

public class KIOSK_Payment_Window_Button_CreditCard extends JPanel{
	private final int Button_CreditCard_width = 320;
	private final int Button_CreditCard_height = 165;
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image bt_img; 
	private KIOSK_Client_Main client_main;
	private KIOSK_Payment_Window payment_window;
	
	public KIOSK_Payment_Window_Button_CreditCard(KIOSK_Client_Main client_main, KIOSK_Payment_Window payment_window) {
		System.out.println("\nKIOSK_Button_PaymentWindow_CreditCard : ===== KIOSK_Button_PaymentWindow_CreditCard() 호출 =====");
		this.client_main = client_main;
		this.payment_window = payment_window;
		TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("KIOSK_Button_PaymentWindow_CreditCard Clicked!!!");
				int payment_creditCard = JOptionPane.showConfirmDialog(KIOSK_Payment_Window_Button_CreditCard.this.payment_window, "신용카드로 결제합니다");
				if(payment_creditCard == JOptionPane.OK_OPTION) {
					KIOSK_Payment_Window_Button_CreditCard.this.payment_window.setPayment_method("2");
					System.out.println("KIOSK_Payment_Window_Button_CreditCard : 신용카드 결제 선택후 payment_window 의 payment_method : "+KIOSK_Payment_Window_Button_CreditCard.this.payment_window.getPayment_method());
				}
			}
		});
		this.setBorder(border);
		this.setPreferredSize(new Dimension(Button_CreditCard_width, Button_CreditCard_height));
		this.setBackground(Color.LIGHT_GRAY);
		this.setFont(new Font("휴먼매직체", Font.BOLD, 35));
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		bt_img = kit.getImage(getClass().getClassLoader().getResource("creditCard.png"));
		g.setColor(Color.LIGHT_GRAY);
		//g.fillRect(60, 30, 180, 140);
		g.fillRect(0, 0, 280, Button_CreditCard_height);
		g.drawImage(bt_img, 0, 0, Button_CreditCard_width, Button_CreditCard_height, this);
	}
}
