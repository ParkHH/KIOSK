package kiosk_Selected_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import kiosk_ClientMain.KIOSK_Client_Main;

public class KIOSK_Selected_Window_Button_SelectedCancel extends JButton{
	private final int Button_SelectedCancel_width = 300;
	private final int Button_SelectedCancel_height = 95;
	private KIOSK_Client_Main client_main;
	
	public KIOSK_Selected_Window_Button_SelectedCancel(KIOSK_Client_Main client_main) {
		System.out.println("KIOSK_Button_SelectedCancel : ===== KIOSK_Button_SelectedCancel() ȣ�� =====");
		this.client_main = client_main;
		this.setText("���� ���");
		this.setFont(new Font("�޸ո���ü", Font.BOLD, 35));
		this.setPreferredSize(new Dimension(Button_SelectedCancel_width, Button_SelectedCancel_height));
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Button_SelectedCancel : ===== KIOSK_Button_SelectedCancel Clicked =====");
				List cart = client_main.getCart();
				System.out.println("KIOSK_Button_SelectedCancel : client_main ���� ������ cart List �� ��� ��Ҹ� �����մϴ�.");
				cart.clear();
				System.out.println("KIOSK_Button_SelectedCancel : ��� ������ client_main ���� ������ cart List �� ���� : "+client_main.getCart().size());
				client_main.setCart(cart);
				System.out.println("KIOSK_Button_SelectedCancel : ��� ��Ҹ� ������ List �� client_main �� cart List �� �����մϴ�.");
				System.out.println("KIOSK_Button_SelectedCancel : client_main �� cart List �� ���� : "+KIOSK_Selected_Window_Button_SelectedCancel.this.client_main.getCart().size());
				client_main.getSelected_window().getSelected_window_BottomPanel().getButton_payment().setText("���� ����");
				System.out.println("KIOSK_Button_SelectedCancel : ���� ���� ��ư�� ������ ǥ�ø� �����մϴ�.");
			}
		});
	}
}
