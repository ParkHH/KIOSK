package kisok_Client_Product_Description_Window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kiosk_FoodBox.KIOSK_FoodBox;

public class KIOSK_Product_Description_Window extends JFrame{
	private final int KIOSK_Product_Description_Window_WIDTH = 500;
	private final int KIOSK_Product_Description_Window_HEIGHT = 600;
	private KIOSK_Client_Main client_main;
	private KIOSK_FoodBox food_box;
	private KIOSK_Client_getMenus_Controller client_getMenus_controller;
	
	private KIOSK_Product_Description_Window_Product_Img product_description_window_product_img;					// 이미지를 표현하는 JPanel
	private KIOSK_Product_Description_Window_Product_info product_description_window_product_info;					// 상품 주문 상세 설정을 표현할 JPanel
	private KIOSK_Product_Description_Window_Product_buttons product_description_window_product_info_buttons;	// 결제, 취소 JButton 을 표현할 JPanel
	private JPanel p_buttons;
	private JButton bt_go_cart;
	private JButton bt_cancel;
	
	
	// 상품 (FoodBox) 클릭시 생성되는 상세주문정보창을 표현하는 Class 입니다.
	public KIOSK_Product_Description_Window(KIOSK_Client_Main client_main, KIOSK_FoodBox food_box, KIOSK_Client_getMenus_Controller client_getMenus_controller) {
		System.out.println("KIOSK_Product_Description_Window : ===== KIOSK_Product_Description_Window() 호출 =====");
		this.client_main = client_main;
		this.food_box = food_box;
		this.client_getMenus_controller = client_getMenus_controller;
		product_description_window_product_img = new KIOSK_Product_Description_Window_Product_Img(client_main, food_box, client_getMenus_controller, this);
		product_description_window_product_info = new KIOSK_Product_Description_Window_Product_info(client_main, food_box, client_getMenus_controller, this);
		product_description_window_product_info_buttons = new KIOSK_Product_Description_Window_Product_buttons(client_main, food_box, client_getMenus_controller, this);
		this.add(product_description_window_product_img, BorderLayout.NORTH);
		this.add(product_description_window_product_info);
		this.add(product_description_window_product_info_buttons, BorderLayout.SOUTH);
		this.setSize(KIOSK_Product_Description_Window_WIDTH, KIOSK_Product_Description_Window_HEIGHT);
		this.setLocationRelativeTo(client_main);
		this.setBackground(Color.GREEN);
		this.setTitle("Product_Description");
		this.setUndecorated(true);			// 본 Class (JFrame) 의 titleBar 를 없앱니다.
		this.setVisible(true);
	}

	
	
	
	
	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public KIOSK_FoodBox getFood_box() {
		return food_box;
	}

	public void setFood_box(KIOSK_FoodBox food_box) {
		this.food_box = food_box;
	}

	public KIOSK_Client_getMenus_Controller getClient_getMenus_controller() {
		return client_getMenus_controller;
	}

	public void setClient_getMenus_controller(KIOSK_Client_getMenus_Controller client_getMenus_controller) {
		this.client_getMenus_controller = client_getMenus_controller;
	}

	public KIOSK_Product_Description_Window_Product_Img getProduct_description_window_product_img() {
		return product_description_window_product_img;
	}

	public void setProduct_description_window_product_img(
			KIOSK_Product_Description_Window_Product_Img product_description_window_product_img) {
		this.product_description_window_product_img = product_description_window_product_img;
	}

	public KIOSK_Product_Description_Window_Product_info getProduct_description_window_product_info() {
		return product_description_window_product_info;
	}

	public void setProduct_description_window_product_info(
			KIOSK_Product_Description_Window_Product_info product_description_window_product_info) {
		this.product_description_window_product_info = product_description_window_product_info;
	}

	public JPanel getP_buttons() {
		return p_buttons;
	}

	public void setP_buttons(JPanel p_buttons) {
		this.p_buttons = p_buttons;
	}

	public JButton getBt_go_cart() {
		return bt_go_cart;
	}

	public void setBt_go_cart(JButton bt_go_cart) {
		this.bt_go_cart = bt_go_cart;
	}

	public JButton getBt_cancel() {
		return bt_cancel;
	}

	public void setBt_cancel(JButton bt_cancel) {
		this.bt_cancel = bt_cancel;
	}

	public int getKIOSK_Product_Description_Window_WIDTH() {
		return KIOSK_Product_Description_Window_WIDTH;
	}

	public int getKIOSK_Product_Description_Window_HEIGHT() {
		return KIOSK_Product_Description_Window_HEIGHT;
	}

	public KIOSK_Product_Description_Window_Product_buttons getProduct_description_window_product_info_buttons() {
		return product_description_window_product_info_buttons;
	}

	public void setProduct_description_window_product_info_buttons(
			KIOSK_Product_Description_Window_Product_buttons product_description_window_product_info_buttons) {
		this.product_description_window_product_info_buttons = product_description_window_product_info_buttons;
	}
	
	
	
	
}	
