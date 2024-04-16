package Design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class trangchuhocsinh extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

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
		
		URL url_hhd = trangchuhocsinh.class.getResource("logo.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
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
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 67, 236, 653);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(10, 11, 85, 81);
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
		
		JLabel lblNewLabel_2 = new JLabel("WELCOM TO "+"THE STAYLEARN");
		lblNewLabel_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setBounds(415, 196, 592, 332);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 1033, 650);
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("/Design/hoctap4.jpg")));
		
		
		JPanel account = new JPanel();
		account.setBackground(new Color(248, 248, 255));
		container.add(account, "account");
		account.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(244, 11, 988, 34);
		panel_2.setBackground(Color.WHITE);
		account.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setBackground(new Color(240, 255, 240));
		btnNewButton.setBounds(491, 0, 101, 34);
		btnNewButton.setForeground(Color.GREEN);
		
		panel_2.add(btnNewButton);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setBackground(new Color(240, 255, 240));
		btnThm.setBounds(602, 0, 89, 34);
		btnThm.setForeground(Color.GREEN);
		panel_2.add(btnThm);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setBackground(new Color(240, 255, 240));
		btnXa.setBounds(701, 0, 89, 34);
		btnXa.setForeground(Color.RED);
		panel_2.add(btnXa);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setBackground(new Color(240, 255, 240));
		btnLu.setBounds(800, 0, 89, 34);
		btnLu.setForeground(Color.CYAN);
		panel_2.add(btnLu);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setBackground(new Color(240, 255, 240));
		btnThot.setBounds(899, 0, 89, 34);
		btnThot.setForeground(Color.RED);
		panel_2.add(btnThot);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(244, 56, 988, 650);
		panel_3.setBackground(Color.WHITE);
		account.add(panel_3);
		panel_3.setLayout(null);
		
	    
	    URL iconURL_quaylai = thongtincanhan.class.getResource("quaylai.png");
	    ImageIcon icon1 = new ImageIcon(iconURL_quaylai);
	    btnNewButton.setIcon(icon1);
	    
	    URL iconURL_xoa = thongtincanhan.class.getResource("xoa.png");
	    ImageIcon icon2 = new ImageIcon(iconURL_xoa);
	    btnXa.setIcon(icon2);
	    
	    URL iconURL_them = thongtincanhan.class.getResource("them.png");
	    ImageIcon icon3 = new ImageIcon(iconURL_them);
	    btnThm.setIcon(icon3);
	    
	    URL iconURL_luu = thongtincanhan.class.getResource("luu.png");
	    ImageIcon icon4 = new ImageIcon(iconURL_luu);
	    btnLu.setIcon(icon4);
		
		JLabel lblThngTinC = new JLabel("Thông tin cá nhân");
		lblThngTinC.setBounds(10, 11, 291, 50);
		lblThngTinC.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(lblThngTinC);
		

		URL iconURL_thongtin = thongtincanhan.class.getResource("thongtin.icon.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_thongtin);
	    lblThngTinC.setIcon(icon0);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã học viên *");
		lblNewLabel_1_1.setBounds(20, 90, 104, 31);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(155, 92, 210, 30);
		textField.setColumns(10);
		panel_3.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 147, 210, 30);
		textField_1.setColumns(10);
		panel_3.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(155, 260, 210, 30);
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Họ và tên *");
		lblNewLabel_1_1_1.setBounds(20, 145, 100, 30);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Giới tính");
		lblNewLabel_1_2.setBounds(20, 200, 104, 30);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_2);
		
		String [] gt = {"Nam", "Nữ"};
		JComboBox comboBox = new JComboBox(gt);
		comboBox.setBounds(155, 200, 210, 30);
		comboBox.setSelectedIndex(-1);
		panel_3.add(comboBox);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại *");
		lblNewLabel_1_3.setBounds(461, 91, 125, 31);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(618, 92, 210, 30);
		textField_3.setColumns(10);
		panel_3.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(618, 147, 210, 30);
		textField_4.setColumns(10);
		panel_3.add(textField_4);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ngày sinh");
		lblNewLabel_1_3_1.setBounds(20, 260, 104, 31);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Email");
		lblNewLabel_1_3_2.setBounds(461, 145, 104, 30);
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_2);
		
		textField_5 = new JTextField();
		textField_5.setBounds(155, 322, 210, 30);
		textField_5.setColumns(10);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(618, 202, 210, 30);
		textField_6.setColumns(10);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(618, 260, 210, 30);
		textField_7.setColumns(10);
		panel_3.add(textField_7);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_3_1_1.setBounds(20, 320, 81, 32);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("Tên phụ huynh *");
		lblNewLabel_1_3_1_2.setBounds(461, 200, 125, 30);
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_1_2);
		
		JLabel lblNewLabel_1_3_1_3 = new JLabel("SĐT phụ huynh *");
		lblNewLabel_1_3_1_3.setBounds(461, 258, 125, 30);
		lblNewLabel_1_3_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_1_3);
		
		textField_8 = new JTextField();
		textField_8.setBounds(618, 322, 210, 31);
		textField_8.setColumns(10);
		panel_3.add(textField_8);
		
		JLabel lblNewLabel_1_3_1_2_1_1 = new JLabel("Ngày đến trung tâm");
		lblNewLabel_1_3_1_2_1_1.setBounds(461, 320, 143, 31);
		lblNewLabel_1_3_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1_3_1_2_1_1);
		
		
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
