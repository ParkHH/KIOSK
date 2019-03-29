package kiosk_Selected_Window;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kiosk_ClientMain.KIOSK_Client_Main;

// ���� ǥ���� Panel
public class KIOSK_Selelcted_Window_TopPanel extends JPanel{
	private final int selected_window_TopPanel_width = KIOSK_Client_Main.WIDTH;
	private final int selected_window_TopPanel_height = 150;
	private final Color selected_window_TopPanel_background = Color.YELLOW;
	Thread img_change_thread;
	Toolkit kit = Toolkit.getDefaultToolkit();					// ���� ���� �̹����� �׸��� ���� ���� ȣ��
	Image img; 		
	KIOSK_Client_Main client_main;								// KIOSK_Selelcted_Window_TopPanel �� Container
	List images = new ArrayList();
	
	public KIOSK_Selelcted_Window_TopPanel(KIOSK_Client_Main client_main) {
		this.client_main = client_main;
		images.add("macmarketing.png");
		images.add("macmarketing2.png");
		img_change_thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					for(int i=0; i<images.size(); i++) {
						try {
							img = kit.getImage(getClass().getClassLoader().getResource((String) images.get(i)));  // ���� ���ʰ� ��ġ�ϴ� resourceFolder ���� �̹����� ����
							KIOSK_Selelcted_Window_TopPanel.this.repaint();
							this.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		img_change_thread.start();
		System.out.println("KIOSK_Selelcted_Window_TopPanel : ===== KIOSK_Selelcted_Window_TopPanel() ȣ�� =====");
		this.setPreferredSize(new Dimension(selected_window_TopPanel_width, selected_window_TopPanel_height));
		this.setBackground(selected_window_TopPanel_background);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int key = JOptionPane.showConfirmDialog(KIOSK_Selelcted_Window_TopPanel.this, "�� ��ǰ �ȳ� �������� �̵��մϴ�");
				if(key == JOptionPane.OK_OPTION) {
					try {
						Desktop.getDesktop().browse(new URL("http://www.mcdonalds.co.kr/www/kor/main/main.do").toURI());
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.setVisible(true);
	}
	
	
	// ���� ���� �̹����� �� Ŭ����(JPanel) �� �׸��� method
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, selected_window_TopPanel_width, selected_window_TopPanel_height);
		//System.out.println("�� selected_window_topPanel ���� ����ϱ� ���� �ҷ��� �̹��� : "+img);
		g.drawImage(img, 0, 0, 800, selected_window_TopPanel_height, this);							// �̹����� width ���� �����ڰ� �����Ͽ� �������־�� �̹����� ��µ�
	}
}
