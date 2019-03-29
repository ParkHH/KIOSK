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
	
	// �� Class (JButton) �� �������� payment_window �� �ߺ� ������ ���� ClientMain ��ġ���� ���� �����ϰ� �ϱ� ���� flag ����
	// �Ʒ� ������ �� Ŭ���� (payment_window �� ����) �� KIOSK_Payment_Window_PaymentButtons Ŭ���� (payment_window �� �ݱ�) �� �����ϰ� ������
	// ���� �Ǵ� �ݱ⸦ �����Ͽ����� �� Ŭ������ flag ���� �����ϰ� ����Ǿ���Ѵ�.
	private boolean payment_window_open_flag;		
	
	public KIOSK_Selected_Window_Button_Payment(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Button_Payment : ===== KIOSK_Button_Payment() ȣ�� =====");
		this.client_main = client_main;
		this.payment_window = this.client_main.getPayment_window();
		this.setText("���� ����");
		this.setFont(new Font("�޸ո���ü", Font.BOLD, 35));
		this.setPreferredSize(new Dimension(Button_Payment_width, Button_Payment_height));
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(payment_window_open_flag == false) {
					System.out.println("KIOSK_Button_Payment : addActionListener : client_main �� cart List �� �����ɴϴ�.");
					List cart = KIOSK_Selected_Window_Button_Payment.this.client_main.getCart();
					System.out.println("KIOSK_Button_Payment : addActionListener : ������ cart List �� ���� : "+cart.size()+"\n (�� 0 : ��ٱ��� ����� �˸� ��� /�� 1 : KIOSK_Payment_Window ȣ��");
					if(cart.size()>0) {
						System.out.println("KIOSK_Button_Payment : ===== KIOSK_Button_Payment Clicked =====");
						System.out.println("KIOSK_Button_Payment : ������ �����մϴ�.");
						payment_window = new KIOSK_Payment_Window(KIOSK_Selected_Window_Button_Payment.this.client_main, KIOSK_Selected_Window_Button_Payment.this);
						payment_window_open_flag = true;
						payment_window.setPayment_window_open_flag(payment_window_open_flag);
						System.out.println("KIOSK_Selected_Window_Button_Payment : payment_window â ���� �� KIOSK_Payment_Window �� payment_window_open_flag : "+payment_window_open_flag);
						System.out.println("KIOSK_Selected_Window_Button_Payment : payment_window â ���� �� KIOSK_Payment_Window �� payment_window_open_flag : "+payment_window.isPayment_window_open_flag());
					}else {
						JOptionPane.showMessageDialog(KIOSK_Selected_Window_Button_Payment.this.client_main, "��ٱ��ϰ� ������ϴ�.");
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
