package kiosk_Payment_Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kiosk_ClientMain.KIOSK_Client_Main;
import kiosk_Payment_List_Box.KIOSK_Payment_List_Box;

public class KIOSK_Payment_Window_PaymentList extends JPanel{
	private int KIOSK_Payment_Window_PaymentList_WIDTH = KIOSK_Client_Main.KIOSK_Client_Main_WIDTH;
	private int KIOSK_Payment_Window_PaymentList_HEIGHT= KIOSK_Client_Main.KIOSK_Client_Main_HEIGHT/2;
	private KIOSK_Client_Main client_main;
	private List cart;
	private KIOSK_Payment_Window payment_window;
	private JPanel p_title;
	private JPanel p_payment_List;
	private JPanel p_total_price;
	private JLabel la_title;
	private JLabel la_total_price;
	int total_price;
	
	public KIOSK_Payment_Window_PaymentList(KIOSK_Client_Main client_main, List cart, KIOSK_Payment_Window payment_window) {
		System.out.println("\nKIOSK_Payment_Window_PaymentList : ===== KIOSK_Payment_Window_PaymentList() 호출 =====");
		TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK, 3, true));
		this.client_main = client_main;
		this.cart = this.client_main.getCart();
		this.payment_window = payment_window;
		this.setLayout(new BorderLayout());
		this.setBorder(border);
		this.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentList_WIDTH, KIOSK_Payment_Window_PaymentList_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		
		create_Components();
	}
	
	public void create_Components() {
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("KIOSK_Payment_Window_PaymentList : create_Components() 호출");
		System.out.println("KIOSK_Payment_Window_PaymentList : client_main 에서 가져온 cart List 의 정보를 확인합니다.");
		System.out.println("KIOSK_Payment_Window_PaymentList : cart List 의 길이 : "+cart.size());
		System.out.println("=========================================================");
		for(int i=0; i<cart.size(); i++) {
			String[] menu = (String[]) cart.get(i);
			for(int a=0; a<menu.length; a++) {
				System.out.println("KIOSK_Payment_Window_PaymentList : cart List 내부 data 를 조회합니다 : "+menu[a]);
			}
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
		System.out.println("=========================================================");
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 장바구니 물품의 total price 를 구합니다
		total_price = 0;
		for(int i=0; i<cart.size(); i++) {
			String[] menu_price = (String[]) cart.get(i);
			total_price += Integer.parseInt(menu_price[2]);
		}
		System.out.println("KIOSK_Payment_Window_PaymentList : 선택한 menu 들의 합산 가격 : "+total_price);
		
		Font la_font = new Font("휴먼매직체", Font.BOLD, 35);
		
		p_title = new JPanel();
		p_payment_List = new JPanel();
		create_Payment_List();
		p_total_price = new JPanel();
		la_title = new JLabel("========== 결제 목록 ==========");
		la_total_price = new JLabel("총 금액 : "+total_price+" 원");
		
		p_title.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentList_WIDTH, 50));
		p_payment_List.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentList_WIDTH, 350));
		p_total_price.setPreferredSize(new Dimension(KIOSK_Payment_Window_PaymentList_WIDTH, 50));
		
		la_title.setFont(la_font);
		la_total_price.setFont(la_font);
		
		p_title.setBackground(new Color(235, 235, 235));
		p_payment_List.setBackground(Color.WHITE);
		p_total_price.setBackground(new Color(235, 235, 235));
		
		p_title.add(la_title);
		p_total_price.add(la_total_price);
		
		this.add(p_title, BorderLayout.NORTH);
		this.add(p_payment_List, BorderLayout.CENTER);
		this.add(p_total_price, BorderLayout.SOUTH);
	}
	
	public void create_Payment_List() {
		System.out.println("KIOSK_Payment_Window_PaymentList : KIOSK_Payment_List_Box 를 생성합니다.");
		for(int i=0; i<cart.size(); i++) {
			String[] cart_menus = (String[]) cart.get(i);
			KIOSK_Payment_List_Box payment_list_box = new KIOSK_Payment_List_Box(payment_window, this);
			payment_list_box.setText("     "+cart_menus[0]+"     /     "+cart_menus[1]+"     /     "+cart_menus[2]+"     /     "+cart_menus[3]+"     /     "+cart_menus[4]);
			p_payment_List.add(payment_list_box);
		}
	}
	
	
	
	
	

	public int getKIOSK_Payment_Window_PaymentList_WIDTH() {
		return KIOSK_Payment_Window_PaymentList_WIDTH;
	}

	public void setKIOSK_Payment_Window_PaymentList_WIDTH(int kIOSK_Payment_Window_PaymentList_WIDTH) {
		KIOSK_Payment_Window_PaymentList_WIDTH = kIOSK_Payment_Window_PaymentList_WIDTH;
	}

	public int getKIOSK_Payment_Window_PaymentList_HEIGHT() {
		return KIOSK_Payment_Window_PaymentList_HEIGHT;
	}

	public void setKIOSK_Payment_Window_PaymentList_HEIGHT(int kIOSK_Payment_Window_PaymentList_HEIGHT) {
		KIOSK_Payment_Window_PaymentList_HEIGHT = kIOSK_Payment_Window_PaymentList_HEIGHT;
	}

	public KIOSK_Client_Main getClient_main() {
		return client_main;
	}

	public void setClient_main(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
	}

	public List getCart() {
		return cart;
	}

	public void setCart(List cart) {
		this.cart = cart;
	}

	public KIOSK_Payment_Window getPayment_window() {
		return payment_window;
	}

	public void setPayment_window(KIOSK_Payment_Window payment_window) {
		this.payment_window = payment_window;
	}

	public JPanel getP_title() {
		return p_title;
	}

	public void setP_title(JPanel p_title) {
		this.p_title = p_title;
	}

	public JPanel getP_payment_menus() {
		return p_payment_List;
	}

	public void setP_payment_menus(JPanel p_payment_List) {
		this.p_payment_List = p_payment_List;
	}

	public JPanel getP_total_price() {
		return p_total_price;
	}

	public void setP_total_price(JPanel p_total_price) {
		this.p_total_price = p_total_price;
	}

	public JLabel getLa_title() {
		return la_title;
	}

	public void setLa_title(JLabel la_title) {
		this.la_title = la_title;
	}

	public JLabel getLa_total_price() {
		return la_total_price;
	}

	public void setLa_total_price(JLabel la_total_price) {
		this.la_total_price = la_total_price;
	}

	public JPanel getP_payment_List() {
		return p_payment_List;
	}

	public void setP_payment_List(JPanel p_payment_List) {
		this.p_payment_List = p_payment_List;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	
	
	
}
