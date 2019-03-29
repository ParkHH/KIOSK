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

public class KIOSK_Payment_Window_Button_Cash extends JPanel{
	private final int button_cash_width = 320;
	private final int button_cash_height = 165;
	
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image bt_img;
	private KIOSK_Client_Main client_main;
	private KIOSK_Payment_Window payment_window;
	
	public KIOSK_Payment_Window_Button_Cash(KIOSK_Client_Main client_main, KIOSK_Payment_Window payment_window) {
		System.out.println("\nKIOSK_Button_PaymentWindow_Cash : ===== KIOSK_Button_PaymentWindow_Cash() 호출 =====");
		this.client_main = client_main;
		this.payment_window = payment_window;
		TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.setBorder(border);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("KIOSK_Button_PaymentWindow_Cash Clicked!!!");
				int payment_cash = JOptionPane.showConfirmDialog(KIOSK_Payment_Window_Button_Cash.this.payment_window, "현금으로 결제합니다");
				if(payment_cash == JOptionPane.OK_OPTION) {
					KIOSK_Payment_Window_Button_Cash.this.payment_window.setPayment_method("3");
					System.out.println("KIOSK_Payment_Window_Button_Cash : 현금 결제 선택후 payment_window 의 payment_method : "+KIOSK_Payment_Window_Button_Cash.this.payment_window.getPayment_method());
				}
			}
		});		
		this.setPreferredSize(new Dimension(button_cash_width, button_cash_height));
		this.setBackground(Color.LIGHT_GRAY);
		this.setFont(new Font("휴먼매직체", Font.BOLD, 35));
		this.setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics g) {
		bt_img = kit.getImage(getClass().getClassLoader().getResource("cash.png"));
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, button_cash_width, button_cash_height);
		g.drawImage(bt_img, 0, 0, button_cash_width, button_cash_height, this);
	}
}
