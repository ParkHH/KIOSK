package kiosk_Payment_Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Request.KIOSK_Request_Insert_PaymentData;
import kiosk_Selected_Window.KIOSK_Selected_Window_Button_Payment;

public class KIOSK_Payment_Window_Paying_CreditCard extends JFrame{
	private KIOSK_Payment_Window payment_window;
	private JPanel p_paying_guide;
	private JLabel la_paying_guide;
	private Thread paying_thread;
	private KIOSK_ClientThread client_thread;
	private JPanel p_north;
	private JPanel p_center;
	private JPanel p_south;
	private JButton bt_cancel;
	
	public KIOSK_Payment_Window_Paying_CreditCard(KIOSK_Payment_Window payment_window) {
		System.out.println("\nKIOSK_Payment_Window_Paying_CreditCard : ===== KIOSK_Payment_Window_Paying_CreditCard() 호출 =====");
		this.payment_window = payment_window;
		this.client_thread = this.payment_window.getClient_main().getClient_Thread();
		this.setLayout(new FlowLayout());
		create_Guide();
		paying_thread = new Thread() {
			@Override
			public void run() {
				try {
					KIOSK_Payment_Window_Paying_CreditCard.this.p_paying_guide.remove(bt_cancel);
					la_paying_guide.setText("카드를 읽는 중입니다....");
					this.sleep(2000);
					la_paying_guide.setText("결제가 완료되었습니다!!!\n  카드를 제거해주세요!");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				KIOSK_Payment_Window_Paying_CreditCard.this.dispose();
			}
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_SPACE) {
					System.out.println("KIOSK_Payment_Window_Paying_CreditCard : CreaditCard Inserted");
					paying_thread.start();
					KIOSK_Request_Insert_PaymentData insert_paymentData = new KIOSK_Request_Insert_PaymentData(payment_window, client_thread);
				}else if(keyCode == KeyEvent.VK_BACK_SPACE) {
					KIOSK_Payment_Window_Paying_CreditCard.this.dispose();
					payment_window.dispose();		
					resetAll();
				}
			}
		});
		this.setSize(500, 400);
		this.setForeground(Color.WHITE);
		this.setLocationRelativeTo(payment_window);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	
	public void create_Guide() {
		System.out.println("KIOSK_Payment_Window_Paying_CreditCard : create_Guide() 호출");		
		p_paying_guide=new JPanel();
		p_paying_guide.setPreferredSize(new Dimension(480, 380));
		p_paying_guide.setBackground(new Color(235, 235, 235));
		p_paying_guide.setLayout(new BorderLayout());
		p_paying_guide.setVisible(true);
		
		
		la_paying_guide = new JLabel();
		la_paying_guide.setOpaque(true);
		la_paying_guide.setFont(new Font(null, Font.BOLD, 15));
		la_paying_guide.setText("카드를 넣어주세요.....");
		la_paying_guide.setBackground(new Color(235, 235, 235));
		la_paying_guide.setHorizontalAlignment(SwingConstants.CENTER);
		la_paying_guide.setVisible(true);
		
		bt_cancel = new JButton("돌아가기");
		bt_cancel.setPreferredSize(new Dimension(100, 40));
		bt_cancel.setFont(new Font(null, Font.BOLD, 20));
		bt_cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KIOSK_Payment_Window_Paying_CreditCard.this.dispose();
			}
		});
		bt_cancel.setFocusable(false);
		
		p_paying_guide.add(la_paying_guide, BorderLayout.CENTER);
		p_paying_guide.add(bt_cancel, BorderLayout.SOUTH);
		this.add(p_paying_guide);
	}
	
	public void resetAll() {
		payment_window.setPayment_window_open_flag(false);
		payment_window.getSelected_window_button_payment().setPayment_window_open_flag(false);
		System.out.println("KIOSK_Payment_Window_Paying_CreditCard : 결제 완료 후 KIOSK_Payment_Window 의 payment_window_open_flag : "+KIOSK_Payment_Window_Paying_CreditCard.this.payment_window.isPayment_window_open_flag());
		System.out.println("KIOSK_Payment_Window_Paying_CreditCard : 결제 완료 후 KIOSK_Selected_Window_Button_Payment 의 payment_window_open_flag : "+KIOSK_Payment_Window_Paying_CreditCard.this.payment_window.getSelected_window_button_payment().isPayment_window_open_flag());
		KIOSK_Selected_Window_Button_Payment payment_button = payment_window.getClient_main().getSelected_window().getSelected_window_BottomPanel().getButton_payment();
		payment_button.setText("결제 진행");
		payment_window.getClient_main().getSelected_window().getSelected_window_BottomPanel().setButton_payment(payment_button);
		List client_main_cart = payment_window.getClient_main().getCart();
		List payment_window_cart = payment_window.getCart();
		client_main_cart.clear();
		payment_window_cart.clear();
		payment_window.getClient_main().setCart(client_main_cart);
		payment_window.setCart(payment_window_cart);
		System.out.println("KIOSK_Payment_Window_Paying_CreditCard : 결제 완료 후 KIOSK_Client_Main 의 cart 크기 : "+payment_window.getClient_main().getCart().size());
		System.out.println("KIOSK_Payment_Window_Paying_CreditCard : 결제 완료 후 KIOSK_Payment_Window 의 cart 크기 : "+payment_window.getCart().size());
	}
}
