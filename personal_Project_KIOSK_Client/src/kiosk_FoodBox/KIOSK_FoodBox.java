package kiosk_FoodBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_ClientThread.KIOSK_ClientThread;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kisok_Client_Product_Description_Window.KIOSK_Product_Description_Window;

public class KIOSK_FoodBox extends JPanel{
	private int foodBox_num;
	private int click_num;
	
	private JPanel product_img;
	private JLabel product_name;
	private JLabel product_price;										
	private KIOSK_Client_getMenus_Controller client_getMenus_controller;
	private KISOK_FoodBox_Product_Info product_info;									// 상품에 대한 간략한 정보를 표현하는 팝업창
	private KIOSK_Product_Description_Window product_description_window;		// 상품 클릭했을시에 나타나는 상품 상세 주문 정보창
	private KIOSK_Client_Main client_main;
	private KIOSK_ClientThread client_thread;
	private boolean product_info_flag;															// 팝업창 중복생성을 방지하기 위한 수단
	private boolean product_descriptiton_window_open_flag;							// 주문 상세 설정 중복 생성을 방지하기 위한 수단
		
	
	// menu 에 대한 이미지, 이름, 가격, 상세정보 등을 표시할 Class 입니다.
	public KIOSK_FoodBox(KIOSK_Client_getMenus_Controller client_getMenus_controller, int foodBox_num) {
		System.out.println("\nKIOSK_FoodBox : ===== KIOSK_FoodBox() 호출 =====");
		this.client_getMenus_controller = client_getMenus_controller;
		this.foodBox_num = foodBox_num;
		this.client_main = this.client_getMenus_controller.getClient_main();
		this.client_thread = this.client_getMenus_controller.getClientThread();
		
		//this.setLayout(new BorderLayout());
		System.out.println("KIOSK_FoodBox : FoodBox 에 표현될 이미지를 호출합니다.");
		product_img = new KIOSK_FoodBox_Product_Image(this.client_getMenus_controller, this);
		this.setPreferredSize(new Dimension(700/3, 580/3));
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("KIOSK_FoodBox : ▶ "+foodBox_num+" 번 FoodBox Clicked");
				System.out.println("KIOSK_FoodBox : product_descriptiton_window_open_flag 상태 (false - 열리지않음 / true : 열림) : "+product_descriptiton_window_open_flag);
				if(product_descriptiton_window_open_flag == false) {
					product_description_window = new KIOSK_Product_Description_Window(client_main, KIOSK_FoodBox.this, client_getMenus_controller);
					product_descriptiton_window_open_flag = true;
					System.out.println("KIOSK_FoodBox : product_descriptiton_window_open_flag 상태 (false - 열리지않음 / true : 열림) : "+product_descriptiton_window_open_flag);
				}else {
					product_description_window.show();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				product_info_flag = false;
				System.out.println("KIOSK_FoodBox : product_info 창 띄우기 전의 product_info_flag : "+product_info_flag+" (창 안만들어짐)");
				if(product_info_flag == false) {
					KIOSK_FoodBox.this.setBackground(Color.LIGHT_GRAY);
					//KIOSK_FoodBox.this.remove(product_img);
					System.out.println("KIOSK_FoodBox : KISOK_FoodBox_Product_Info 를 생성해 상품에 대한 정보를 띄웁니다.");
					product_info = new KISOK_FoodBox_Product_Info(KIOSK_FoodBox.this.client_getMenus_controller, KIOSK_FoodBox.this);
					product_info_flag = true;
					System.out.println("KIOSK_FoodBox : product_info 창을 띄운후의 product_info_flag : "+product_info_flag+" (창 만들어짐)");
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("KIOSK_FoodBox : mouseExited() 동작!!");
				KIOSK_FoodBox.this.setBackground(Color.WHITE);
				product_info.dispose();
			}
		});
		this.setVisible(true);
	}

	
	
	
	
	
	

	public KISOK_FoodBox_Product_Info getProduct_info() {
		return product_info;
	}

	public void setProduct_info(KISOK_FoodBox_Product_Info product_info) {
		this.product_info = product_info;
	}

	public KIOSK_Client_getMenus_Controller getClient_getMenus_controller() {
		return client_getMenus_controller;
	}

	public void setClient_getMenus_controller(KIOSK_Client_getMenus_Controller client_getMenus_controller) {
		this.client_getMenus_controller = client_getMenus_controller;
	}

	public int getFoodBox_num() {
		return foodBox_num;
	}

	public void setFoodBox_num(int foodBox_num) {
		foodBox_num = foodBox_num;
	}

	public int getClick_num() {
		return click_num;
	}

	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}

	public JPanel getProduct_img() {
		return product_img;
	}

	public void setProduct_img(JPanel product_img) {
		this.product_img = product_img;
	}

	public JLabel getProduct_name() {
		return product_name;
	}

	public void setProduct_name(JLabel product_name) {
		this.product_name = product_name;
	}

	public JLabel getProduct_price() {
		return product_price;
	}

	public void setProduct_price(JLabel product_price) {
		this.product_price = product_price;
	}

	public JFrame getProduct_description_window() {
		return product_description_window;
	}
	
	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public KIOSK_ClientThread getClient_thread() {
		return client_thread;
	}

	public void setClient_thread(KIOSK_ClientThread client_thread) {
		this.client_thread = client_thread;
	}
	
	public boolean isProduct_info_flag() {
		return product_info_flag;
	}

	public void setProduct_info_flag(boolean product_info_flag) {
		this.product_info_flag = product_info_flag;
	}

	public boolean isProduct_descriptiton_window_open_flag() {
		return product_descriptiton_window_open_flag;
	}

	public void setProduct_descriptiton_window_open_flag(boolean product_descriptiton_window_open_flag) {
		this.product_descriptiton_window_open_flag = product_descriptiton_window_open_flag;
	}
	
	
	
}
