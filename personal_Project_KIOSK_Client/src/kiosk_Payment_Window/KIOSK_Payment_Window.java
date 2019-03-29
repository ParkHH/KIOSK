package kiosk_Payment_Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Selected_Window.KIOSK_Selected_Window_Button_Payment;

public class KIOSK_Payment_Window extends JFrame {
	public static final int KIOSK_Payment_Window_WIDTH = KIOSK_Client_Main.KIOSK_Client_Main_WIDTH;
	public static final int KIOSK_Payment_Window_HEIGHT = KIOSK_Client_Main.KIOSK_Client_Main_HEIGHT;
	private final Color Payment_Window_background = Color.YELLOW;
	private KIOSK_Client_Main client_main;
	private List cart;

	private KIOSK_Payment_Window_PaymentList p_payment_list;
	private KIOSK_Payment_Window_PaymentMethod p_payment_method;
	private KIOSK_Payment_Window_PaymentButtons p_payment_buttons;
	private KIOSK_Selected_Window_Button_Payment selected_window_button_payment;
	private boolean payment_window_open_flag;
	private String payment_method ="";

	public KIOSK_Payment_Window(KIOSK_Client_Main client_main,
			KIOSK_Selected_Window_Button_Payment selected_window_button_payment) {
		System.out.println("\nKIOSK_Payment_Window : ===== KIOSK_Payment_Window() »£√‚ =====");
		this.client_main = client_main;
		this.cart = this.client_main.getCart();
		this.selected_window_button_payment = selected_window_button_payment;
		this.payment_window_open_flag = selected_window_button_payment.isPayment_window_open_flag();
		
		p_payment_list = new KIOSK_Payment_Window_PaymentList(client_main, cart, this);
		p_payment_method = new KIOSK_Payment_Window_PaymentMethod(client_main, cart, this);
		p_payment_buttons = new KIOSK_Payment_Window_PaymentButtons(client_main, cart, this);
		this.add(p_payment_list, BorderLayout.NORTH);
		this.add(p_payment_method, BorderLayout.CENTER);
		this.add(p_payment_buttons, BorderLayout.SOUTH);
		this.setSize(KIOSK_Payment_Window_WIDTH, KIOSK_Payment_Window_HEIGHT);
		this.setBackground(Payment_Window_background);
		this.setUndecorated(true);
		this.setLocationRelativeTo(client_main);
		this.setTitle("Payment_window");
		this.setVisible(true);

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

	public KIOSK_Payment_Window_PaymentList getP_payment_list() {
		return p_payment_list;
	}

	public void setP_payment_list(KIOSK_Payment_Window_PaymentList p_payment_list) {
		this.p_payment_list = p_payment_list;
	}

	public KIOSK_Payment_Window_PaymentMethod getP_payment_method() {
		return p_payment_method;
	}

	public void setP_payment_method(KIOSK_Payment_Window_PaymentMethod p_payment_method) {
		this.p_payment_method = p_payment_method;
	}

	public KIOSK_Payment_Window_PaymentButtons getP_payment_buttons() {
		return p_payment_buttons;
	}

	public void setP_payment_buttons(KIOSK_Payment_Window_PaymentButtons p_payment_buttons) {
		this.p_payment_buttons = p_payment_buttons;
	}

	public static int getKioskPaymentWindowWidth() {
		return KIOSK_Payment_Window_WIDTH;
	}

	public static int getKioskPaymentWindowHeight() {
		return KIOSK_Payment_Window_HEIGHT;
	}

	public Color getPayment_Window_background() {
		return Payment_Window_background;
	}

	public KIOSK_Selected_Window_Button_Payment getSelected_window_button_payment() {
		return selected_window_button_payment;
	}

	public void setSelected_window_button_payment(KIOSK_Selected_Window_Button_Payment selected_window_button_payment) {
		this.selected_window_button_payment = selected_window_button_payment;
	}

	public boolean isPayment_window_open_flag() {
		return payment_window_open_flag;
	}

	public void setPayment_window_open_flag(boolean payment_window_open_flag) {
		this.payment_window_open_flag = payment_window_open_flag;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

}
