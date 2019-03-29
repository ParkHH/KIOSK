package kiosk_login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kiosk_Request.KIOSK_Request_login;
import kisosk_Server_Connection.KIOSK_Client_Server_Connecter;

public class KIOSK_Client_LoginForm extends JFrame{
	public static final int KIOSK_Client_LoginForm_WIDTH = 350;
	public static final int KIOSK_Client_LoginForm_HEIGHT = 200;
	public static final int KIOSK_Client_LoginForm_X = 750;
	public static final int KIOSK_Client_LoginForm_Y = 450;
	
	private JPanel p_wrapper;
	private JPanel p_north;
	private JPanel p_center;
	private JPanel p_south;
	private JTextField tf_admin_id;
	private JPasswordField pf_admin_pw;
	private JButton bt_login;
	
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image login_bg_img = kit.getImage(getClass().getClassLoader().getResource("bg.jpg")); 
	
	private KIOSK_Client_Server_Connecter server_connect;
	
	public KIOSK_Client_LoginForm() {
		create_Components();
		server_connect = new KIOSK_Client_Server_Connecter(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("KIOSK_Client_LoginForm : 프로그램을 종료합니다.");
				System.exit(0);
			}
		});
		this.setSize(350, 200);
		this.setLocation(KIOSK_Client_LoginForm_X, KIOSK_Client_LoginForm_Y);
		this.setTitle("MacDonald KIOSK");
		this.setVisible(true);
	}
	
	
	public void create_Components() {
		p_wrapper = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(login_bg_img, 0, 0, KIOSK_Client_LoginForm_WIDTH, KIOSK_Client_LoginForm_HEIGHT, this);
			}
		};
		
		tf_admin_id = new JTextField();
		pf_admin_pw = new JPasswordField();
		pf_admin_pw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_ENTER) {
					KIOSK_Request_login request_login = new KIOSK_Request_login(KIOSK_Client_LoginForm.this);
				}
			}
		});
		bt_login = new JButton("로그인");
		
		p_wrapper.setPreferredSize(new Dimension(KIOSK_Client_LoginForm_WIDTH, KIOSK_Client_LoginForm_HEIGHT));
		p_wrapper.setBackground(Color.RED);
		
		tf_admin_id.setPreferredSize(new Dimension(230, 25));
		pf_admin_pw.setPreferredSize(new Dimension(230, 25));
		bt_login.setPreferredSize(new Dimension(100, 35));
		bt_login.setFont(new Font(null, Font.BOLD, 15));
		
		bt_login.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("KIOSK_Client_LoginForm : bt_login Clicked!!");
				KIOSK_Request_login request_login = new KIOSK_Request_login(KIOSK_Client_LoginForm.this);
			}
		});		
		
		p_wrapper.setBorder(new EmptyBorder(28, 0, 0, 0));
		p_wrapper.add(tf_admin_id);
		p_wrapper.add(pf_admin_pw);
		p_wrapper.add(bt_login);
		p_wrapper.updateUI();
		this.add(p_wrapper);
	}
	
	public static void main(String[] args) {
		new KIOSK_Client_LoginForm();
	}


	
	
	
	
	
	public JPanel getP_wrapper() {
		return p_wrapper;
	}


	public void setP_wrapper(JPanel p_wrapper) {
		this.p_wrapper = p_wrapper;
	}


	public JPanel getP_north() {
		return p_north;
	}


	public void setP_north(JPanel p_north) {
		this.p_north = p_north;
	}


	public JPanel getP_center() {
		return p_center;
	}


	public void setP_center(JPanel p_center) {
		this.p_center = p_center;
	}


	public JPanel getP_south() {
		return p_south;
	}


	public void setP_south(JPanel p_south) {
		this.p_south = p_south;
	}


	public JTextField getTf_admin_id() {
		return tf_admin_id;
	}


	public void setTf_admin_id(JTextField tf_admin_id) {
		this.tf_admin_id = tf_admin_id;
	}


	public JPasswordField getPf_admin_pw() {
		return pf_admin_pw;
	}


	public void setPf_admin_pw(JPasswordField pf_admin_pw) {
		this.pf_admin_pw = pf_admin_pw;
	}


	public JButton getBt_login() {
		return bt_login;
	}


	public void setBt_login(JButton bt_login) {
		this.bt_login = bt_login;
	}


	public Toolkit getKit() {
		return kit;
	}


	public void setKit(Toolkit kit) {
		this.kit = kit;
	}


	public Image getLogin_bg_img() {
		return login_bg_img;
	}


	public void setLogin_bg_img(Image login_bg_img) {
		this.login_bg_img = login_bg_img;
	}


	public KIOSK_Client_Server_Connecter getServer_connect() {
		return server_connect;
	}


	public void setServer_connect(KIOSK_Client_Server_Connecter server_connect) {
		this.server_connect = server_connect;
	}


	public static int getKioskClientLoginformWidth() {
		return KIOSK_Client_LoginForm_WIDTH;
	}


	public static int getKioskClientLoginformHeight() {
		return KIOSK_Client_LoginForm_HEIGHT;
	}


	public static int getKioskClientLoginformX() {
		return KIOSK_Client_LoginForm_X;
	}


	public static int getKioskClientLoginformY() {
		return KIOSK_Client_LoginForm_Y;
	}
	
	
	
	
}
