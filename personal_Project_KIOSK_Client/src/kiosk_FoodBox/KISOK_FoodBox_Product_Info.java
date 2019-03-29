package kiosk_FoodBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;

// 본 Class 는 FoodBox 에 product_name 과 product_price 및 Product_description 을 표현하는 Class 입니다.
public class KISOK_FoodBox_Product_Info extends JFrame{
	private JLabel la_product_name;
	private JLabel la_product_price;
	private JLabel la_product_description;
	private int  KISOK_FoodBox_Product_Info_WIDTH = 200;
	private int KISOK_FoodBox_Product_Info_HEIGHT = 100;
	
	private KIOSK_Client_getMenus_Controller client_getMenus_controller;
	private KIOSK_FoodBox food_box;
	
	
	// 상품에 대한 정보를 나타내는 Class 입니다,
	// FoodBox 에 마우스가 올라갈때 생성되며 FoodBox 에서 마우스가 나갈때는 사라지게 해야합니다.
	
	/*
	 * 190217 0447 : 현재 해당 클래스를 적용하려고 시도하였으나 GUI Design 적인 문제가 발생한다.
	 * 
	 * #증상 
	 * 		1. FoodBox 에 마우스를 Entered 할때 생성은 정상
	 * 		2. FoodBox 에서 마우스가 빠져나갈때 제대로 제거되지가 않음, 연속적인 new 동작이 발생됨
	*/
	public KISOK_FoodBox_Product_Info(KIOSK_Client_getMenus_Controller client_getMenus_controller, KIOSK_FoodBox food_box) {
		System.out.println("\nKISOK_FoodBox_Product_Info : ===== KISOK_FoodBox_Product_Info() 호출 =====");
		this.client_getMenus_controller = client_getMenus_controller;
		this.food_box = food_box;
		
		this.setLayout(new BorderLayout());
		setting_FoodBox_Info();
		this.setSize(KISOK_FoodBox_Product_Info_WIDTH, KISOK_FoodBox_Product_Info_HEIGHT);
		this.setUndecorated(true);
		this.setLocationRelativeTo(food_box);
		this.setVisible(true);
	}
	
	
	public void setting_FoodBox_Info() {
		la_product_name = new JLabel();
		la_product_price = new JLabel();
		la_product_description = new JLabel();
		
		List menus = client_getMenus_controller.getMenus();
		String[] menu = (String[]) menus.get(food_box.getFoodBox_num());
		String product_name = menu[1];
		String product_price = menu[2];
		String product_description = menu[3];
		
		la_product_name.setOpaque(true);
		la_product_price.setOpaque(true);
		la_product_description.setOpaque(true);
		
		la_product_name.setText("▶상품명 : "+product_name);
		la_product_price.setText("▶가격 : "+product_price);
		la_product_description.setText(product_description);
		
		Font tf_font = new Font("휴먼매직체", Font.BOLD, 20);
		la_product_name.setFont(tf_font);
		la_product_price.setFont(tf_font);
		la_product_description.setFont(tf_font);
		
		Dimension tf_size = new Dimension(200, 30);
		la_product_name.setPreferredSize(tf_size);
		la_product_price.setPreferredSize(tf_size);
		la_product_description.setPreferredSize(tf_size);
		
		la_product_name.setHorizontalAlignment(SwingConstants.LEFT);
		la_product_price.setHorizontalAlignment(SwingConstants.LEFT);
		la_product_description.setHorizontalAlignment(SwingConstants.LEFT);
		
		la_product_name.setForeground(Color.WHITE);
		la_product_price.setForeground(Color.WHITE);
		la_product_description.setForeground(Color.WHITE);
		
		la_product_name.setBackground(Color.DARK_GRAY);
		la_product_price.setBackground(Color.DARK_GRAY);
		la_product_description.setBackground(Color.DARK_GRAY);
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("KISOK_FoodBox_Product_Info : mouseEntered() 동작!!");
				food_box.setBackground(Color.LIGHT_GRAY);
			}
		});
		this.add(la_product_name, BorderLayout.NORTH);
		this.add(la_product_price, BorderLayout.CENTER);
		this.add(la_product_description, BorderLayout.SOUTH);
		
	}


	public JLabel getla_product_name() {
		return la_product_name;
	}


	public void setla_product_name(JLabel la_product_name) {
		this.la_product_name = la_product_name;
	}


	public JLabel getla_product_price() {
		return la_product_price;
	}


	public void setla_product_price(JLabel la_product_price) {
		this.la_product_price = la_product_price;
	}


	public JLabel getla_product_description() {
		return la_product_description;
	}


	public void setla_product_description(JLabel la_product_description) {
		this.la_product_description = la_product_description;
	}


	public KIOSK_Client_getMenus_Controller getClient_getMenus_controller() {
		return client_getMenus_controller;
	}


	public void setClient_getMenus_controller(KIOSK_Client_getMenus_Controller client_getMenus_controller) {
		this.client_getMenus_controller = client_getMenus_controller;
	}


	public KIOSK_FoodBox getFood_box() {
		return food_box;
	}


	public void setFood_box(KIOSK_FoodBox food_box) {
		this.food_box = food_box;
	}
	
}
