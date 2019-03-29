package kisok_Client_Product_Description_Window;

import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Client_Controller.KIOSK_Client_getMenus_Controller;
import kiosk_FoodBox.KIOSK_FoodBox;

public class KIOSK_Product_Description_Window_Product_info extends JPanel{
	private KIOSK_Client_Main client_main;
	private KIOSK_FoodBox food_box;
	private KIOSK_Client_getMenus_Controller client_getMenus_Controller;
	private KIOSK_Product_Description_Window product_descriptiton_window;
	
	private int KIOSK_Product_Description_Window_Product_info_WIDTH;
	private int KIOSK_Product_Description_Window_Product_info_HEIGHT=210;

	private JPanel p_product_name;
	private JPanel p_product_type;
	private JPanel p_product_price;
	private JPanel p_select_beverage;
	private JPanel p_select_side_dish;
	
	private JLabel la_product_name;
	private JLabel la_product_type;
	private JLabel la_product_price;
	private JLabel la_select_beverage;
	private JLabel la_select_side_dish;
	
	private Choice ch_product_type;
	private Choice ch_select_beverage;
	private Choice ch_select_side_dish;
	private String product_kind;					// ��ǰ�� ���п� ���� �Ʒ� �׸���� �޸��ϱ� ���� ���� ���� ( 3 : �ܹ��� / 4 : ���� )
	
	//�� �Ʒ��� ��ٱ��Ͽ� ��� ��ư�� Ŭ��������� ������ �����͵�
	//======================================================
	private String product_name;									// ��ǰ�� �̸�
	private String product_price;									// ��ǰ�� ��ǰ ����
	private String selected_product_type = "��ǰ";			// ��ǰ ���� 
	private String setMenu_product_price;						// ��ǰ�� ��Ʈ�޴� ����
	private String large_set_product_price;						// ��ǰ�� ������Ʈ�޴��� ����
	private String selected_beverage = "------";				// ������ ����
	private String selected_side_dish = "------";				// ������ �� �޴�
	//======================================================
	
	List menus = new ArrayList();
	
	// ������ ��ǰ�� ���� �� �ֹ� ������ ǥ���ϴ� Class �Դϴ�.
	public KIOSK_Product_Description_Window_Product_info(KIOSK_Client_Main client_main, KIOSK_FoodBox food_box, KIOSK_Client_getMenus_Controller client_getMenus_Controller, KIOSK_Product_Description_Window product_descriptiton_window) {
		System.out.println("\nKIOSK_Product_Description_Window_Product_info : ===== KIOSK_Product_Description_Window_Product_info() ȣ�� =====");
		this.client_main = client_main;
		this.food_box = food_box;
		this.client_getMenus_Controller = client_getMenus_Controller;
		this.product_descriptiton_window = product_descriptiton_window;
		
		System.out.println("KIOSK_Product_Description_Window_Product_info : product_description_window �� width ���� �� Ŭ����(JPanel) �� width �� �����մϴ�.");
		this.KIOSK_Product_Description_Window_Product_info_WIDTH = this.product_descriptiton_window.getKIOSK_Product_Description_Window_WIDTH();
		
		this.setLayout(new GridLayout(5,1));
		
		TitledBorder panel_border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.setBorder(panel_border);
		create_Product_Info();
		
		this.setPreferredSize(new Dimension(KIOSK_Product_Description_Window_Product_info_WIDTH, KIOSK_Product_Description_Window_Product_info_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}
	
	
	
	public void create_Product_Info() {
		System.out.println("KIOSK_Product_Description_Window_Product_info : create_Product_Info() ȣ�� : ��ǰ �� �ֹ� ������ ���ϴ�.");
		getMenus();
		
		Dimension panels_size = new Dimension(KIOSK_Product_Description_Window_Product_info_WIDTH, KIOSK_Product_Description_Window_Product_info_HEIGHT/6); 
		Dimension label_size = new Dimension(KIOSK_Product_Description_Window_Product_info_WIDTH, KIOSK_Product_Description_Window_Product_info_HEIGHT/6);
		Dimension ch_label_size = new Dimension(150, KIOSK_Product_Description_Window_Product_info_HEIGHT/6);
		Font components_font = new Font("�޸ո���ü", Font.BOLD, 20);
		
		p_product_name = new JPanel();
		p_product_type = new JPanel();
		p_product_price = new JPanel();
		p_select_beverage = new JPanel();
		p_select_side_dish = new JPanel();
		
		// �� Component ���� Design ��  �����մϴ�.
		p_product_name.setPreferredSize(panels_size);
		p_product_type.setPreferredSize(panels_size);
		p_product_price.setPreferredSize(panels_size);
		p_select_beverage.setPreferredSize(panels_size);
		p_select_side_dish.setPreferredSize(panels_size);
		
		p_product_name.setBackground(Color.WHITE);
		p_product_type.setBackground(Color.WHITE);
		p_product_price.setBackground(Color.WHITE);
		p_select_beverage.setBackground(Color.WHITE);
		p_select_side_dish.setBackground(Color.WHITE);
		 
		la_product_name = new JLabel("����ǰ�� :            "+product_name);
		la_product_type = new JLabel("������ : ");
		la_product_price = new JLabel("����ǰ���� :          "+product_price+" ��");
		la_select_beverage = new JLabel("�����ἱ�� : ");
		la_select_side_dish = new JLabel("�����̵� �޴� ���� : ");
		
		ch_product_type = new Choice();
		ch_select_beverage = new Choice();
		ch_select_side_dish = new Choice();
		
		if(product_kind.equals("3")) {
			ch_product_type.add("��ǰ");
			ch_product_type.add("��Ʈ �޴�");
			ch_product_type.add("���� ��Ʈ");
		}else if(product_kind.equals("4")) {
			ch_product_type.add("��ǰ");
		}
		
		ch_select_beverage.add("------");
		ch_select_side_dish.add("------");

		
		ch_product_type.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("KIOSK_Product_Description_Window_Product_info : ch_product_type.addItemListener() ����!!");
				if(product_kind.equals("3")) {
					getHambuger_Selected_product_types();
				}
//				}else if(product_kind.equals("4")) {
//					getSnack_product_type();
//				}
			}
		});
		
		ch_select_beverage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("KIOSK_Product_Description_Window_Product_info : ch_select_beverage.addItemListener() ����!!");
				getSelected_beverages();
			}
		});
		
		ch_select_side_dish.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("KIOSK_Product_Description_Window_Product_info : ch_select_side_dish.addItemListener() ����!!");
				getSelected_side_dishes();
			}
		});
		
		la_product_name.setOpaque(true);
		la_product_type.setOpaque(true);
		la_product_price.setOpaque(true);
		la_select_beverage.setOpaque(true);
		la_select_side_dish.setOpaque(true);
		
		la_product_name.setHorizontalAlignment(SwingConstants.LEFT);
		la_product_type.setHorizontalAlignment(SwingConstants.LEFT);
		la_product_price.setHorizontalAlignment(SwingConstants.LEFT);
		la_select_beverage.setHorizontalAlignment(SwingConstants.LEFT);
		la_select_side_dish.setHorizontalAlignment(SwingConstants.LEFT);
		
		la_product_name.setBackground(Color.WHITE);
		la_product_type.setBackground(Color.WHITE);
		la_product_price.setBackground(Color.WHITE);
		la_select_beverage.setBackground(Color.WHITE);
		la_select_side_dish.setBackground(Color.WHITE);
		
		la_product_name.setPreferredSize(new Dimension(350, KIOSK_Product_Description_Window_Product_info_HEIGHT/6));
		la_product_type.setPreferredSize(new Dimension(190, KIOSK_Product_Description_Window_Product_info_HEIGHT/6));
		la_product_price.setPreferredSize(new Dimension(350, KIOSK_Product_Description_Window_Product_info_HEIGHT/6));
		la_select_beverage.setPreferredSize(new Dimension(190, KIOSK_Product_Description_Window_Product_info_HEIGHT/6));
		la_select_side_dish.setPreferredSize(new Dimension(190, KIOSK_Product_Description_Window_Product_info_HEIGHT/6));
		
		ch_product_type.setPreferredSize(ch_label_size);
		ch_select_beverage.setPreferredSize(ch_label_size);
		ch_select_side_dish.setPreferredSize(ch_label_size);
		
		la_product_name.setFont(components_font);
		la_product_type.setFont(components_font);
		la_product_price.setFont(components_font);
		la_select_beverage.setFont(components_font);
		la_select_side_dish.setFont(components_font);
	
		p_product_name.add(la_product_name);
		p_product_type.add(la_product_type);
		p_product_type.add(ch_product_type);
		p_product_price.add(la_product_price);
		p_select_beverage.add(la_select_beverage);
		p_select_beverage.add(ch_select_beverage);
		p_select_side_dish.add(la_select_side_dish);
		p_select_side_dish.add(ch_select_side_dish);
		
		this.add(p_product_name);
		this.add(p_product_type);
		this.add(p_product_price);
		this.add(p_select_beverage);
		this.add(p_select_side_dish);
		
	}
	
	
	
	
	public void getMenus() {
		System.out.println("KIOSK_Product_Description_Window_Product_info : Controller ���� ��ǰ ������ ���ɴϴ�.");
		System.out.println("KIOSK_Product_Description_Window_Product_info : getMenus() : client_getMenus_Controller ���� menus List �� ���ɴϴ�.");
		List menus = client_getMenus_Controller.getMenus();
		System.out.println("KIOSK_Product_Description_Window_Product_info : getMenus() : ���� List ���� ������ FoodBox �� ���õ� ��ǰ ������ �����ɴϴ�.");
		String[] menu = (String[]) menus.get(food_box.getFoodBox_num());
		product_name = menu[1];
		product_price = menu[2];
		product_kind = menu[5];
		
		System.out.println("KIOSK_Product_Description_Window_Product_info : getMenus() : product_name : "+product_name);
		System.out.println("KIOSK_Product_Description_Window_Product_info : getMenus() : product_price : "+product_price);
	}
	
	
	// ������ FoodBox �� ��ǰ�� �ܹ���(product_kind : 3) �϶� �ܹ��ſ� ���� �� �޴� ���� ������ ǥ���ϴ� method
	public void getHambuger_Selected_product_types() {
		System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_product_type() ���� : ������ ch_product_type �� ���� �����ɴϴ�.");
		String selected_product_type = ch_product_type.getSelectedItem();
		System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_product_types() : ������ menu �� ��ǰ ���� "+product_kind);
		if(selected_product_type.equals("��ǰ")) {
			this.selected_product_type = selected_product_type;
			la_product_price.setText("����ǰ���� :          "+this.product_price+" ��");
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_product_type() : "+this.selected_product_type+" �޴� �� �����߽��ϴ�. ������ ����˴ϴ� : "+this.product_price);
			// ��ǰ ���ý� ����� ���̵�޴��� ������ �� ������ �����մϴ�.
			//===============================
			ch_select_beverage.removeAll();
			ch_select_beverage.add("------");
			ch_select_side_dish.removeAll();
			ch_select_side_dish.add("------");
			selected_beverage="------";
			selected_side_dish="------";
			//===============================
		}else if(selected_product_type.equals("��Ʈ �޴�")) {
			this.selected_product_type = selected_product_type;
			int product_price = Integer.parseInt(this.product_price);
			product_price+=1000;
			setMenu_product_price = Integer.toString(product_price);
			la_product_price.setText("����ǰ���� :          "+setMenu_product_price +" ��");
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_product_type() : "+this.selected_product_type+" �� �����߽��ϴ�. ������ ����˴ϴ� : "+setMenu_product_price);
			// ��Ʈ�޴� ���ý� �ڵ����� ����� ���̵� �޴��� �����˴ϴ�
			//===============================
			ch_select_beverage.removeAll();
			ch_select_beverage.add("��ī�ݶ�");
			ch_select_beverage.add("��ī�ݶ� ����");
			ch_select_beverage.add("��������Ʈ");
			ch_select_beverage.add("ȯŸ");
			ch_select_side_dish.removeAll();
			ch_select_side_dish.add("����ġ ������");
			ch_select_side_dish.add("��Ͼ�");
			ch_select_side_dish.add("�ؽ� ����");
			selected_beverage="��ī�ݶ�";
			selected_side_dish="����ġ ������";
			//===============================
		}else if(selected_product_type.equals("���� ��Ʈ")) {
			this.selected_product_type = selected_product_type;
			int product_price = Integer.parseInt(this.product_price);
			product_price+=1500;
			large_set_product_price = Integer.toString(product_price);
			la_product_price.setText("����ǰ���� :          "+large_set_product_price+" ��");
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_product_type() : "+this.selected_product_type+" �� �����߽��ϴ�. ������ ����˴ϴ� : "+large_set_product_price);
			// ��Ʈ�޴� ���ý� �ڵ����� ����� ���̵� �޴��� �����˴ϴ�
			//===============================
			ch_select_beverage.removeAll();
			ch_select_beverage.add("��ī�ݶ�");
			ch_select_beverage.add("��ī�ݶ� ����");
			ch_select_beverage.add("��������Ʈ");
			ch_select_beverage.add("ȯŸ");
			ch_select_side_dish.removeAll();
			ch_select_side_dish.add("����ġ ������");
			ch_select_side_dish.add("��Ͼ�");
			ch_select_side_dish.add("�ؽ� ����");
			selected_beverage="��ī�ݶ�";
			selected_side_dish="����ġ ������";
			//===============================
		}
	}
	
	
	// ������ FoodBox �� ��ǰ�� ���� (product_kind : 4) �϶� ������ ���� �� �޴� ���� ������ ǥ���ϴ� method
//	public void getSnack_product_type(){
//		ch_product_type.removeAll();
//		ch_product_type.add("��ǰ");
//		// ������ ��ǰ �޴��� �����Ƿ� ����� ���̵�޴��� ���� ������ �����մϴ�.
//		//===============================
//		ch_select_beverage.removeAll();
//		ch_select_beverage.add("------");
//		ch_select_side_dish.removeAll();
//		ch_select_side_dish.add("------");
//		selected_beverage="------";
//		selected_side_dish="------";
//		//===============================
//	}
	
	
	
	public void getSelected_beverages() {
		System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_beverage() ���� : ������ ch_select_beverage �� ���� �����ɴϴ�.");
		String selected_beverage = ch_select_beverage.getSelectedItem();
		if(selected_beverage.equals("��ī�ݶ�")) {
			this.selected_beverage = selected_beverage;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_beverage() : "+this.selected_beverage+" �� �����ϼ̽��ϴ�.");
		}else if(selected_beverage.equals("��ī�ݶ� ����")) {
			this.selected_beverage = selected_beverage;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_beverage() : "+this.selected_beverage+" �� �����ϼ̽��ϴ�.");
		}else if(selected_beverage.equals("��������Ʈ")) {
			this.selected_beverage = selected_beverage;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_beverage() : "+this.selected_beverage+" �� �����ϼ̽��ϴ�.");
		}else if(selected_beverage.equals("ȯŸ")) {
			this.selected_beverage = selected_beverage;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_beverage() : "+this.selected_beverage+" �� �����ϼ̽��ϴ�.");
		}
	}
	
	
	public void getSelected_side_dishes() {
		System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_side_dish() ���� : ������ ch_select_side_dish �� ���� �����ɴϴ�.");
		String selected_side_dish = ch_select_side_dish.getSelectedItem();
		if(selected_side_dish.equals("����ġ ������")) {
			this.selected_side_dish = selected_side_dish;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_side_dish() : "+this.selected_side_dish+" �� �����ϼ̽��ϴ�.");
		}else if(selected_side_dish.equals("��Ͼ�")) {
			this.selected_side_dish = selected_side_dish;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_side_dish() : "+this.selected_side_dish+" �� �����ϼ̽��ϴ�.");
		}else if(selected_side_dish.equals("�ؽ� ����")) {
			this.selected_side_dish = selected_side_dish;
			System.out.println("KIOSK_Product_Description_Window_Product_info : getSelected_side_dish() : "+this.selected_side_dish+" �� �����ϼ̽��ϴ�.");
		}
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



	public KIOSK_Client_getMenus_Controller getClient_getMenus_Controller() {
		return client_getMenus_Controller;
	}



	public void setClient_getMenus_Controller(KIOSK_Client_getMenus_Controller client_getMenus_Controller) {
		this.client_getMenus_Controller = client_getMenus_Controller;
	}



	public KIOSK_Product_Description_Window getProduct_descriptiton_window() {
		return product_descriptiton_window;
	}



	public void setProduct_descriptiton_window(KIOSK_Product_Description_Window product_descriptiton_window) {
		this.product_descriptiton_window = product_descriptiton_window;
	}



	public int getKIOSK_Product_Description_Window_Product_info_WIDTH() {
		return KIOSK_Product_Description_Window_Product_info_WIDTH;
	}



	public void setKIOSK_Product_Description_Window_Product_info_WIDTH(
			int kIOSK_Product_Description_Window_Product_info_WIDTH) {
		KIOSK_Product_Description_Window_Product_info_WIDTH = kIOSK_Product_Description_Window_Product_info_WIDTH;
	}



	public int getKIOSK_Product_Description_Window_Product_info_HEIGHT() {
		return KIOSK_Product_Description_Window_Product_info_HEIGHT;
	}



	public void setKIOSK_Product_Description_Window_Product_info_HEIGHT(
			int kIOSK_Product_Description_Window_Product_info_HEIGHT) {
		KIOSK_Product_Description_Window_Product_info_HEIGHT = kIOSK_Product_Description_Window_Product_info_HEIGHT;
	}



	public JPanel getP_product_name() {
		return p_product_name;
	}



	public void setP_product_name(JPanel p_product_name) {
		this.p_product_name = p_product_name;
	}



	public JPanel getP_product_type() {
		return p_product_type;
	}



	public void setP_product_type(JPanel p_product_type) {
		this.p_product_type = p_product_type;
	}


	public JPanel getP_product_price() {
		return p_product_price;
	}



	public void setP_product_price(JPanel p_product_price) {
		this.p_product_price = p_product_price;
	}



	public JPanel getP_select_beverage() {
		return p_select_beverage;
	}



	public void setP_select_beverage(JPanel p_select_beverage) {
		this.p_select_beverage = p_select_beverage;
	}



	public JPanel getP_select_side_dish() {
		return p_select_side_dish;
	}



	public void setP_select_side_dish(JPanel p_select_side_dish) {
		this.p_select_side_dish = p_select_side_dish;
	}



	public JLabel getLa_product_name() {
		return la_product_name;
	}



	public void setLa_product_name(JLabel la_product_name) {
		this.la_product_name = la_product_name;
	}



	public JLabel getLa_product_type() {
		return la_product_type;
	}



	public void setLa_product_type(JLabel la_product_type) {
		this.la_product_type = la_product_type;
	}



	public Choice getCh_product_type() {
		return ch_product_type;
	}



	public void setCh_product_type(Choice ch_product_type) {
		this.ch_product_type = ch_product_type;
	}


	public JLabel getLa_product_price() {
		return la_product_price;
	}



	public void setLa_product_price(JLabel la_product_price) {
		this.la_product_price = la_product_price;
	}



	public JLabel getLa_select_beverage() {
		return la_select_beverage;
	}



	public void setLa_select_beverage(JLabel la_select_beverage) {
		this.la_select_beverage = la_select_beverage;
	}



	public Choice getCh_select_beverage() {
		return ch_select_beverage;
	}



	public void setCh_select_beverage(Choice ch_select_beverage) {
		this.ch_select_beverage = ch_select_beverage;
	}



	public JLabel getLa_select_side_dish() {
		return la_select_side_dish;
	}



	public void setLa_select_side_dish(JLabel la_select_side_dish) {
		this.la_select_side_dish = la_select_side_dish;
	}



	public Choice getCh_select_side_dish() {
		return ch_select_side_dish;
	}



	public void setCh_select_side_dish(Choice ch_select_side_dish) {
		this.ch_select_side_dish = ch_select_side_dish;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	public String getProduct_price() {
		return product_price;
	}



	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}



	public String getSetMenu_product_price() {
		return setMenu_product_price;
	}



	public void setSetMenu_product_price(String setMenu_product_price) {
		this.setMenu_product_price = setMenu_product_price;
	}



	public String getLarge_set_product_price() {
		return large_set_product_price;
	}



	public void setLarge_set_product_price(String large_set_product_price) {
		this.large_set_product_price = large_set_product_price;
	}


	public String getSelected_product_type() {
		return selected_product_type;
	}
	
	public void setSelected_product_type(String selected_product_type) {
		this.selected_product_type = selected_product_type;
	}



	public void setSelected_beverage(String selected_beverage) {
		this.selected_beverage = selected_beverage;
	}



	public void setSelected_side_dish(String selected_side_dish) {
		this.selected_side_dish = selected_side_dish;
	}



	public String getSelected_beverage() {
		return selected_beverage;
	}



	public String getSelected_side_dish() {
		return selected_side_dish;
	}



	
	
	
	
	
}
