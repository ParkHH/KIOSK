package kiosk_FoodBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;

// FoodBox ���ο��� ��ǰ�� �̹����� ǥ���ϴ� JPanel Class �Դϴ�.
public class KIOSK_FoodBox_Product_Image extends JPanel{
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image product_img;
	private String img_name;
	private KIOSK_Client_getMenus_Controller client_getMenus_controller;
	private KIOSK_FoodBox food_box;
	
	public final static int FOOD_BOX_WIDTH = 200;
	public final static int FOOD_BOX_HEIGHT = 180;
	
	// FoodBox �� �⺻���� ǥ���� �������� ��ǰ�� �̹����� ǥ���ϴ� Class �Դϴ�.
	public KIOSK_FoodBox_Product_Image(KIOSK_Client_getMenus_Controller client_getMenus_controller, KIOSK_FoodBox food_box) {
		System.out.println("\nKIOSK_FoodBox_Product_Image : ===== KIOSK_FoodBox_Product_Image() ȣ�� =====");
		this.client_getMenus_controller = client_getMenus_controller;
		System.out.println("KIOSK_FoodBox_Product_Image : client_getMenus_controller ���� menus List �� ���ɴϴ�.");
		List menus = this.client_getMenus_controller.getMenus();
		System.out.println("KIOSK_FoodBox_Product_Image : ���� List ���� �����Ǵ� FoodBox �� num �� �ش��ϴ� �ε����� �迭�� �����ϴ�.");
		String[] menu = (String[]) menus.get(food_box.getFoodBox_num());
		
		System.out.println("KIOSK_FoodBox_Product_Image : ���� �迭�� 4�� �ε��� (product_img) �� ���� ������ �����մϴ�.");
		img_name = menu[4];
		System.out.println("KIOSK_FoodBox_Product_Image : List ���� �迭���� ���� img_name �� : "+img_name);
		this.setPreferredSize(new Dimension(FOOD_BOX_WIDTH, FOOD_BOX_HEIGHT));
		this.setBackground(Color.YELLOW);
		this.setVisible(true);
		food_box.add(this);
	}
	
	@Override
	public void paint(Graphics g) {
		product_img = kit.getImage(getClass().getClassLoader().getResource(img_name));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, FOOD_BOX_WIDTH, FOOD_BOX_HEIGHT);
		g.drawImage(product_img, 0, 0, FOOD_BOX_WIDTH, FOOD_BOX_HEIGHT, this);
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

	public KIOSK_Client_getMenus_Controller getClient_getMenus_controller() {
		return client_getMenus_controller;
	}

	public void setClient_getMenus_controller(KIOSK_Client_getMenus_Controller client_getMenus_controller) {
		this.client_getMenus_controller = client_getMenus_controller;
	}
	
	
	
	
	
}
