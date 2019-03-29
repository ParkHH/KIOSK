package kiosk_Payment_Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Request.KIOSK_Request_Insert_PaymentData;
import kiosk_Selected_Window.KIOSK_Selected_Window_Button_Payment;

public class KIOSK_Payment_Window_Paying_Cash extends JFrame{
	private int KIOSK_Payment_Window_Paying_Cash_WIDTH = 500;
	private int KIOSK_Payment_Window_Paying_Cash_HEIGHT = 400;
	KIOSK_Payment_Window payment_window;
	KIOSK_Payment_Window_PaymentList payment_list;
	KIOSK_ClientThread client_thread;
	int total_price;
	JPanel p_guide;
	JLabel la_guide;
	JPanel p_cash_info;
	JLabel la_price;
	JLabel la_insert_money;
	JLabel la_change;
	JPanel p_buttons;
	JButton bt_payment;
	JButton bt_cancel;
	
	JLabel la_price_guide;
	JLabel la_insertMoney_guide;
	JLabel la_change_guide;
	int insert_money;
	int change;
	public KIOSK_Payment_Window_Paying_Cash(KIOSK_Payment_Window payment_window, KIOSK_ClientThread client_thread) {
		System.out.println("KIOSK_Payment_Window_Paying_Cash : ===== KIOSK_Payment_Window_Paying_Cash() 호출 =====");
		this.payment_window = payment_window;
		this.payment_list = this.payment_window.getP_payment_list();
		this.client_thread = this.payment_window.getClient_main().getClient_Thread();
		this.total_price = this.payment_list.getTotal_price();	
		create_Guide();
		create_Components();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_Z) {
					insert_money += 10000;
					System.out.println("KIOSK_Payment_Window_Paying_Cash : insert_money : "+insert_money);
					la_insert_money.setText(insert_money+"     원          ");
					if(insert_money-total_price<0) {
						la_change.setText("     원          ");
					}else {
						la_change.setText(insert_money-total_price+"     원          ");
					}
				}else if(keyCode == KeyEvent.VK_X) {
					insert_money += 5000;
					la_insert_money.setText(insert_money+"     원          ");
					if(insert_money-total_price<0) {
						la_change.setText("     원          ");
					}else {
						la_change.setText(insert_money-total_price+"     원          ");
					}
				}else if(keyCode == KeyEvent.VK_C) {
					insert_money += 1000;
					la_insert_money.setText(insert_money+"     원          ");
					if(insert_money-total_price<0) {
						la_change.setText("     원          ");
					}else {
						la_change.setText(insert_money-total_price+"     원          ");
					}
				}
			}
		});
		this.setUndecorated(true);
		this.setSize(KIOSK_Payment_Window_Paying_Cash_WIDTH, KIOSK_Payment_Window_Paying_Cash_HEIGHT);
		this.setLocationRelativeTo(this.payment_window);
		this.setVisible(true);
	}
	
	
	public void create_Guide() {
		p_guide = new JPanel();
		p_guide.setBackground(Color.RED);
		p_guide.setPreferredSize(new Dimension(480, 30));
		p_guide.setVisible(true);
		
		la_guide = new JLabel("현금을 넣으세요...");
		la_guide.setFont(new Font(null, Font.BOLD, 15));
		la_guide.setHorizontalAlignment(SwingConstants.CENTER);
		
		p_guide.add(la_guide);
		this.add(p_guide, BorderLayout.NORTH);
	}
	
	
	public void create_Components() {
		p_cash_info = new JPanel();
		p_cash_info.setPreferredSize(new Dimension(KIOSK_Payment_Window_Paying_Cash_WIDTH, KIOSK_Payment_Window_Paying_Cash_HEIGHT-250));
		p_cash_info.setLayout(new GridLayout(3, 2));
		p_cash_info.setVisible(true);
		
		Font la_font = new Font(null, Font.BOLD, 20);
		Font bt_font = new Font("휴먼매직체", Font.BOLD, 20);
		Color la_color = new Color(220, 220, 220);
		
		la_price = new JLabel();
		la_price.setText(Integer.toString(total_price)+"     원          ");
		la_price.setFont(la_font);
		la_price.setOpaque(true);
		la_price.setBackground(la_color);
		la_price.setHorizontalAlignment(SwingConstants.RIGHT);
		
		la_insert_money = new JLabel();
		la_insert_money.setFont(la_font);
		la_insert_money.setOpaque(true);
		la_insert_money.setBackground(la_color);
		la_insert_money.setHorizontalAlignment(SwingConstants.RIGHT);
		la_insert_money.setText("     원          ");
		
		la_change = new JLabel();
		la_change.setFont(la_font);
		la_change.setOpaque(true);
		la_change.setBackground(la_color);
		la_change.setHorizontalAlignment(SwingConstants.RIGHT);
		la_change.setText("     원          ");
		
		la_price_guide = new JLabel();
		la_price_guide.setText("총액");
		la_price_guide.setFont(la_font);
		la_price_guide.setOpaque(true);
		la_price_guide.setBackground(la_color);
		la_price_guide.setHorizontalAlignment(SwingConstants.CENTER);
		
		la_insertMoney_guide = new JLabel();
		la_insertMoney_guide.setText("투입 금액");
		la_insertMoney_guide.setFont(la_font);
		la_insertMoney_guide.setOpaque(true);
		la_insertMoney_guide.setBackground(la_color);
		la_insertMoney_guide.setHorizontalAlignment(SwingConstants.CENTER);
		
		la_change_guide = new JLabel();
		la_change_guide.setText("거스름돈");
		la_change_guide.setFont(la_font);
		la_change_guide.setOpaque(true);
		la_change_guide.setBackground(la_color);
		la_change_guide.setHorizontalAlignment(SwingConstants.CENTER);
		
		p_buttons = new JPanel();
		p_buttons.setPreferredSize(new Dimension(KIOSK_Payment_Window_Paying_Cash_WIDTH, 85));
		p_buttons.setVisible(true);
		p_buttons.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		bt_payment = new JButton("결제 진행");
		bt_payment.setPreferredSize(new Dimension(200, 60));
		bt_payment.setBackground(Color.WHITE);
		bt_payment.setFont(bt_font);
		bt_payment.setFocusable(false);
		bt_payment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(insert_money-total_price>0) {
					System.out.println("KIOSK_Payment_Window_Paying_Cash : bt_payment Clicked");
					KIOSK_Request_Insert_PaymentData insert_paymentData = new KIOSK_Request_Insert_PaymentData(payment_window, client_thread);
					KIOSK_Payment_Window_Paying_Cash.this.dispose();
					payment_window.dispose();
					resetAll();
				}else {
					JOptionPane.showMessageDialog(payment_window, "투입금액을 확인바랍니다.");
				}
			}
		});
		
		bt_cancel = new JButton("취소");
		bt_cancel.setPreferredSize(new Dimension(200, 60));
		bt_cancel.setBackground(Color.WHITE);
		bt_cancel.setFont(bt_font);
		bt_cancel.setFocusable(false);
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Payment_Window_Paying_Cash : 현금 결제장 닫힘");
				insert_money = 0;
				KIOSK_Payment_Window_Paying_Cash.this.dispose();
			}
		});
		
		p_buttons.add(bt_payment);
		p_buttons.add(bt_cancel);
		
		p_cash_info.add(la_price_guide);
		p_cash_info.add(la_price);
		p_cash_info.add(la_insertMoney_guide);
		p_cash_info.add(la_insert_money);
		p_cash_info.add(la_change_guide);
		p_cash_info.add(la_change);
		
		this.add(p_cash_info, BorderLayout.CENTER);
		this.add(p_buttons, BorderLayout.SOUTH);
	}
	
	
	
	
	public void resetAll() {
		payment_window.setPayment_window_open_flag(false);
		payment_window.getSelected_window_button_payment().setPayment_window_open_flag(false);
		System.out.println("KIOSK_Payment_Window_Paying_Cash : 결제 완료 후 KIOSK_Payment_Window 의 payment_window_open_flag : "+this.payment_window.isPayment_window_open_flag());
		System.out.println("KIOSK_Payment_Window_Paying_Cash : 결제 완료 후 KIOSK_Selected_Window_Button_Payment 의 payment_window_open_flag : "+this.payment_window.getSelected_window_button_payment().isPayment_window_open_flag());
		KIOSK_Selected_Window_Button_Payment payment_button = payment_window.getClient_main().getSelected_window().getSelected_window_BottomPanel().getButton_payment();
		payment_button.setText("결제 진행");
		payment_window.getClient_main().getSelected_window().getSelected_window_BottomPanel().setButton_payment(payment_button);
		List client_main_cart = payment_window.getClient_main().getCart();
		List payment_window_cart = payment_window.getCart();
		client_main_cart.clear();
		payment_window_cart.clear();
		payment_window.getClient_main().setCart(client_main_cart);
		payment_window.setCart(payment_window_cart);
		System.out.println("KIOSK_Payment_Window_Paying_Cash : 결제 완료 후 KIOSK_Client_Main 의 cart 크기 : "+payment_window.getClient_main().getCart().size());
		System.out.println("KIOSK_Payment_Window_Paying_Cash : 결제 완료 후 KIOSK_Payment_Window 의 cart 크기 : "+payment_window.getCart().size());
	}
}
