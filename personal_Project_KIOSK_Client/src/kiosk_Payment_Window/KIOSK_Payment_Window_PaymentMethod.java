package kiosk_Payment_Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kiosk_ClientMain.KIOSK_Client_Main;

public class KIOSK_Payment_Window_PaymentMethod extends JPanel{
	private int KIOSK_Payment_Window_PaymentMethod_WIDTH = KIOSK_Client_Main.KIOSK_Client_Main_WIDTH;
	private int KIOSK_Payment_Window_PaymentMethod_HEIGHT= KIOSK_Client_Main.KIOSK_Client_Main_HEIGHT/5;
	private KIOSK_Client_Main client_main;
	private List cart;
	private KIOSK_Payment_Window payment_window;
	private JPanel p_title;
	private JPanel p_payment_methods;
	private JPanel p_payment_method_creditCard;			// bt_creditCard 의 이미지를 담을 JPanel
	private JPanel p_payment_method_cash;					// bt_cash 의 이미지를 담을 JPanel
	private JLabel la_title;
	private KIOSK_Payment_Window_Button_CreditCard bt_creditCard;		// 신용카드 이미지를 표현하는 Class
	private KIOSK_Payment_Window_Button_Cash bt_Cash;						// 현금 이미지를 표현하는 Class
	
	public KIOSK_Payment_Window_PaymentMethod(KIOSK_Client_Main client_main, List cart, KIOSK_Payment_Window payment_window) {
		System.out.println("KIOSK_Payment_Window_PaymentList : ===== KIOSK_Payment_Window_PaymentList() 호출 =====");
		TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.client_main = client_main;
		this.cart = this.client_main.getCart();
		this.payment_window = payment_window;
		
		this.setLayout(new BorderLayout());
		
		p_title = new JPanel();
		p_payment_methods = new JPanel();
		p_payment_method_creditCard = new JPanel();
		p_payment_method_cash = new JPanel();
		la_title = new JLabel("=============== 결제 수단 ===============");
				
//		p_title.setBackground(Color.RED);
//		p_payment_methods.setBackground(Color.CYAN);
//		p_payment_method_creditCard.setBackground(Color.YELLOW);
//		p_payment_method_cash.setBackground(Color.GREEN);
		
		p_title.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentMethod_WIDTH, KIOSK_Payment_Window_PaymentMethod_HEIGHT/4));
		p_payment_methods.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentMethod_WIDTH, KIOSK_Payment_Window_PaymentMethod_HEIGHT/3));
		p_payment_method_creditCard.setPreferredSize(new Dimension(330, KIOSK_Payment_Window_PaymentMethod_HEIGHT));
		p_payment_method_cash.setPreferredSize(new Dimension(330, KIOSK_Payment_Window_PaymentMethod_HEIGHT));
		
		la_title.setFont(new Font("휴먼매직체", Font.BOLD, 30));
		
		p_title.add(la_title);
		p_payment_methods.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		p_payment_method_creditCard.setBorder(border);
		p_payment_method_cash.setBorder(border);
		
		creaste_Buttons();
		
		this.add(p_title, BorderLayout.NORTH);
		this.add(p_payment_methods, BorderLayout.CENTER);
		
		this.setBorder(border);
		this.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentMethod_WIDTH, KIOSK_Payment_Window_PaymentMethod_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}
	
	
	public void creaste_Buttons() {
		System.out.println("KIOSK_Payment_Window_PaymentMethod : creaste_Buttons() 호출");
		bt_creditCard = new KIOSK_Payment_Window_Button_CreditCard(client_main, payment_window);
		bt_Cash = new KIOSK_Payment_Window_Button_Cash(client_main, payment_window);
		
		p_payment_method_creditCard.add(bt_creditCard);
		p_payment_method_cash.add(bt_Cash);
		p_payment_methods.add(p_payment_method_creditCard);
		p_payment_methods.add(p_payment_method_cash);
	}


	
	
	
	
	
	public int getKIOSK_Payment_Window_PaymentMethod_WIDTH() {
		return KIOSK_Payment_Window_PaymentMethod_WIDTH;
	}


	public void setKIOSK_Payment_Window_PaymentMethod_WIDTH(int kIOSK_Payment_Window_PaymentMethod_WIDTH) {
		KIOSK_Payment_Window_PaymentMethod_WIDTH = kIOSK_Payment_Window_PaymentMethod_WIDTH;
	}


	public int getKIOSK_Payment_Window_PaymentMethod_HEIGHT() {
		return KIOSK_Payment_Window_PaymentMethod_HEIGHT;
	}


	public void setKIOSK_Payment_Window_PaymentMethod_HEIGHT(int kIOSK_Payment_Window_PaymentMethod_HEIGHT) {
		KIOSK_Payment_Window_PaymentMethod_HEIGHT = kIOSK_Payment_Window_PaymentMethod_HEIGHT;
	}


	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}


	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}


	public List getCart() {
		return cart;
	}


	public void setCart(List cart) {
		this.cart = cart;
	}


	public KIOSK_Payment_Window getPayment_window() {
		return payment_window;
	}


	public void setPayment_window(KIOSK_Payment_Window payment_window) {
		this.payment_window = payment_window;
	}


	public JPanel getP_title() {
		return p_title;
	}


	public void setP_title(JPanel p_title) {
		this.p_title = p_title;
	}


	public JPanel getP_payment_methods() {
		return p_payment_methods;
	}


	public void setP_payment_methods(JPanel p_payment_methods) {
		this.p_payment_methods = p_payment_methods;
	}


	public JPanel getP_payment_method_creditCard() {
		return p_payment_method_creditCard;
	}


	public void setP_payment_method_creditCard(JPanel p_payment_method_creditCard) {
		this.p_payment_method_creditCard = p_payment_method_creditCard;
	}


	public JPanel getP_payment_method_cash() {
		return p_payment_method_cash;
	}


	public void setP_payment_method_cash(JPanel p_payment_method_cash) {
		this.p_payment_method_cash = p_payment_method_cash;
	}


	public JLabel getLa_title() {
		return la_title;
	}


	public void setLa_title(JLabel la_title) {
		this.la_title = la_title;
	}


	public KIOSK_Payment_Window_Button_CreditCard getBt_creditCard() {
		return bt_creditCard;
	}


	public void setBt_creditCard(KIOSK_Payment_Window_Button_CreditCard bt_creditCard) {
		this.bt_creditCard = bt_creditCard;
	}


	public KIOSK_Payment_Window_Button_Cash getBt_Cash() {
		return bt_Cash;
	}


	public void setBt_Cash(KIOSK_Payment_Window_Button_Cash bt_Cash) {
		this.bt_Cash = bt_Cash;
	}
	
	
	
	
}
