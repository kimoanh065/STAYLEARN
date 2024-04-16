package Design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class trangchuhocsinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trangchuhocsinh frame = new trangchuhocsinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public trangchuhocsinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1280, 750);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(224, 255, 255));
		menu.setBounds(0, 0, 0, 750);
		panel.add(menu);
		menu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ANH NGỮ STAYLEARN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 189, 66);
		menu.add(lblNewLabel);
		
		JLabel lbtrangchu = new JLabel("TRANG CHỦ");
		lbtrangchu.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbtrangchu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbtrangchu.setBounds(0, 88, 244, 42);
		menu.add(lbtrangchu);
		
		JLabel lbthongtin = new JLabel("THÔNG TIN CÁ NHÂN");
		lbthongtin.setHorizontalAlignment(SwingConstants.CENTER);
		lbthongtin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbthongtin.setBounds(0, 130, 244, 42);
		menu.add(lbthongtin);
		
		
		JLabel lblNewLabel_3 = new JLabel("LỊCH HỌC");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(0, 171, 244, 42);
		menu.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("HỌC PHÍ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(0, 214, 244, 42);
		menu.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CẤU HÌNH");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(0, 257, 244, 42);
		menu.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("TRỢ GIÚP");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(0, 301, 244, 42);
		menu.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("CHÍNH SÁCH");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(0, 344, 244, 42);
		menu.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("ĐĂNG XUẤT");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(0, 387, 244, 42);
		menu.add(lblNewLabel_8);
		
		JLabel lbclose = new JLabel("");
		lbclose.setIcon(new ImageIcon(menu.class.getResource("/Design/cancel.jpg")));;
		lbclose.setBounds(209, 0, 24, 30);
		menu.add(lbclose);
		
		JLabel lbmenu = new JLabel("");
		lbmenu.setBounds(21, 21, 34, 24);
		int width = 244;
		int height = 750;
		

		lbmenu.setIcon(new ImageIcon(menu.class.getResource("/Design/menu.jpg")));
		panel.add(lbmenu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 255));
		panel_1.setBounds(0, 65, 236, 655);
		panel.add(panel_1);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(0, 0, 85, 81);
		panel_1.add(lblNewLabel_11);
		lblNewLabel_11.setIcon(new ImageIcon(menu.class.getResource("/Design/avatar.png")));
		
		
		JPanel container = new JPanel();
		container.setBounds(0, 67, 1280, 683);
		panel.add(container);
		container.setLayout(new CardLayout(0, 0));
		
		
		JPanel home = new JPanel();
		home.setBackground(SystemColor.window);
		container.add(home, "home");
		home.setLayout(null);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(237, 0, 1033, 650);
		panel_4.setBackground(Color.WHITE);
		panel_4.setOpaque(false);
		home.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(23, 0, 1000, 650);
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("/Design/hoctap3.jpg")));
		
		JButton btemail = new JButton("");
		btemail.setBackground(new Color(255, 255, 255));
		btemail.setBounds(1056, 11, 34, 30);
		btemail.setIcon(new ImageIcon(menu.class.getResource("/Design/email.png")));
		panel.add(btemail);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(1106, 11, 34, 30);
		btnNewButton_1.setIcon(new ImageIcon(menu.class.getResource("/Design/thongbao.png")));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(1156, 11, 34, 30);
		btnNewButton_2.setIcon(new ImageIcon(menu.class.getResource("/Design/cake.png")));
		panel.add(btnNewButton_2);
		
		lbmenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Thread(() -> {
		            for (int i = 0; i<width; i++){
		                menu.setSize(i, height);
		            }
		            try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		        }).start();
			}
		});
		
		lbclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Thread(() -> {
					for (int i = width; i>0; i--){
		                menu.setSize(i, height);
		            }
		            try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		        }).start();
			}
		});
		
		
		lbtrangchu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout)(container.getLayout());
				c1.show(container, "home");
				menu.setSize(0,750);
			}
		});
		
		lbthongtin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout)(container.getLayout());
				c1.show(container, "account");
				menu.setSize(0,750);
			}
		});
		
		setTitle("STAYLEARN");
		setSize(1280,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
