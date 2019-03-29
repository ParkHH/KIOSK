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
		System.out.println("KIOSK_Button_SelectedCancel : ===== KIOSK_Button_SelectedCancel() 호출 =====");
		this.client_main = client_main;
		this.setText("선택 취소");
		this.setFont(new Font("휴먼매직체", Font.BOLD, 35));
		this.setPreferredSize(new Dimension(Button_SelectedCancel_width, Button_SelectedCancel_height));
		this.setBackground(Color.GRAY);
		this.setForeground(Color.WHITE);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Button_SelectedCancel : ===== KIOSK_Button_SelectedCancel Clicked =====");
				List cart = client_main.getCart();
				System.out.println("KIOSK_Button_SelectedCancel : client_main 에서 가져온 cart List 의 모든 요소를 삭제합니다.");
				cart.clear();
				System.out.println("KIOSK_Button_SelectedCancel : 요소 삭제후 client_main 에서 가져온 cart List 의 길이 : "+client_main.getCart().size());
				client_main.setCart(cart);
				System.out.println("KIOSK_Button_SelectedCancel : 모든 요소를 삭제한 List 를 client_main 의 cart List 로 설정합니다.");
				System.out.println("KIOSK_Button_SelectedCancel : client_main 의 cart List 의 길이 : "+KIOSK_Selected_Window_Button_SelectedCancel.this.client_main.getCart().size());
				client_main.getSelected_window().getSelected_window_BottomPanel().getButton_payment().setText("결제 진행");
				System.out.println("KIOSK_Button_SelectedCancel : 결제 진행 버튼의 아이콘 표시를 삭제합니다.");
			}
		});
	}
}
