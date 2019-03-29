package kisok_Client_Product_Description_Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kiosk_FoodBox.KIOSK_FoodBox;

public class KIOSK_Product_Description_Window_Product_Img extends JPanel{
	private int KIOSK_Product_Description_Window_Product_Img_WIDTH;
	private final  int KIOSK_Product_Description_Window_Product_Img_HEIGHT = 170;
	private KIOSK_Client_Main client_main;
	private KIOSK_FoodBox food_box;
	private KIOSK_Client_getMenus_Controller client_getMenus_controller;
	private KIOSK_Product_Description_Window product_descriptiton_window;
	
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image product_img;
	private String img_name;
	
	
	// ���� ��ǰ�� ���� �� �ֹ����� â���� ��ǰ�� �̹����� ǥ���ϴ� Class �Դϴ�.
	public KIOSK_Product_Description_Window_Product_Img(KIOSK_Client_Main client_main, KIOSK_FoodBox food_box, KIOSK_Client_getMenus_Controller client_getMenus_controller, KIOSK_Product_Description_Window product_descriptiton_window) {
		System.out.println("KIOSK_Product_Description_Window_Product_Img : ===== KIOSK_Product_Description_Window_Product_Img() ȣ�� =====");
		this.client_main = client_main;
		this.food_box = food_box;
		this.client_getMenus_controller = client_getMenus_controller;
		this.product_descriptiton_window = product_descriptiton_window;
		
		System.out.println("KIOSK_Product_Description_Window_Product_Img : product_descriptiton_window �� width ���� ���� Ŭ���� (JPanel) �� width �� ����");
		KIOSK_Product_Description_Window_Product_Img_WIDTH = this.product_descriptiton_window.getKIOSK_Product_Description_Window_WIDTH();
		
		System.out.println("KIOSK_Product_Description_Window_Product_Img : client_getMenus_controller ���� menus List �� �����ɴϴ�.");
		List menus = this.client_getMenus_controller.getMenus();
		System.out.println("KIOSK_Product_Description_Window_Product_Img : ������ menus List ���� �ش� FoodBox �� Data �� �����ɴϴ�.");
		String[] menu = (String[]) menus.get(food_box.getFoodBox_num());
		System.out.println("KIOSK_Product_Description_Window_Product_Img : menus List ���� ������ ArrayData ���� img_name �Ӽ��� data �� �����մϴ�.");
		img_name = menu[4];
		
		this.setPreferredSize(new Dimension(KIOSK_Product_Description_Window_Product_Img_WIDTH, KIOSK_Product_Description_Window_Product_Img_HEIGHT));
		this.setBackground(Color.GRAY);
		this.setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics g) {
		product_img = kit.getImage(getClass().getClassLoader().getResource(img_name));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, KIOSK_Product_Description_Window_Product_Img_WIDTH, KIOSK_Product_Description_Window_Product_Img_HEIGHT);
		g.drawImage(product_img, 150, 0, 200, KIOSK_Product_Description_Window_Product_Img_HEIGHT, this);
	}


	
	
	
	public int getKIOSK_Product_Description_Window_Product_Img_WIDTH() {
		return KIOSK_Product_Description_Window_Product_Img_WIDTH;
	}


	public void setKIOSK_Product_Description_Window_Product_Img_WIDTH(
			int kIOSK_Product_Description_Window_Product_Img_WIDTH) {
		KIOSK_Product_Description_Window_Product_Img_WIDTH = kIOSK_Product_Description_Window_Product_Img_WIDTH;
	}


	public int getKIOSK_Product_Description_Window_Product_Img_HEIGHT() {
		return KIOSK_Product_Description_Window_Product_Img_HEIGHT;
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


	public Toolkit getKit() {
		return kit;
	}


	public void setKit(Toolkit kit) {
		this.kit = kit;
	}


	public Image getProduct_img() {
		return product_img;
	}


	public void setProduct_img(Image product_img) {
		this.product_img = product_img;
	}


	public String getImg_name() {
		return img_name;
	}


	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	
	
	
}
