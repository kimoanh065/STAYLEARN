package Design;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import control.WriteTextFile;


public class trangchuhocsinh extends JFrame {
	private JTextField tf_mahocvien;
	private JTextField tf_hovaten;
	private JTextField tf_ngaysinh;
	private JTextField tf_sdt;
	private JTextField tf_email;
	private JTextField tf_diachi;
	private JTextField tf_tenphuhuynh;
	private JTextField tf_sdtph;
	private JTextField tf_datett;

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
		
		JPanel pn_home = new JPanel();
		pn_home.setBackground(new Color(128, 128, 128));
		pn_home.setBounds(0, 0, 1280, 750);
		contentPane.add(pn_home);
		pn_home.setLayout(null);
		
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(224, 255, 255));
		menu.setBounds(0, 0, 0, 750);
		pn_home.add(menu);
		menu.setLayout(null);
		
		JLabel lbl_title = new JLabel("ANH NGỮ STAYLEARN");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_title.setBounds(0, 0, 189, 66);
		menu.add(lbl_title);
		
		JLabel lb_trangchu = new JLabel("TRANG CHỦ");
		lb_trangchu.setHorizontalAlignment(SwingConstants.CENTER);
		
		lb_trangchu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_trangchu.setBounds(0, 88, 244, 42);
		menu.add(lb_trangchu);
		
		JLabel lb_thongtin = new JLabel("THÔNG TIN CÁ NHÂN");
		lb_thongtin.setHorizontalAlignment(SwingConstants.CENTER);
		lb_thongtin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_thongtin.setBounds(0, 130, 244, 42);
		menu.add(lb_thongtin);
		
		
		JLabel lb_lichhoc = new JLabel("LỊCH HỌC");
		lb_lichhoc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_lichhoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_lichhoc.setBounds(0, 171, 244, 42);
		menu.add(lb_lichhoc);
		
		JLabel lb_hocphi = new JLabel("HỌC PHÍ");
		lb_hocphi.setHorizontalAlignment(SwingConstants.CENTER);
		lb_hocphi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_hocphi.setBounds(0, 214, 244, 42);
		menu.add(lb_hocphi);
		
		JLabel lb_cauhinh = new JLabel("CẤU HÌNH");
		lb_cauhinh.setHorizontalAlignment(SwingConstants.CENTER);
		lb_cauhinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_cauhinh.setBounds(0, 257, 244, 42);
		menu.add(lb_cauhinh);
		
		JLabel lb_trogiup = new JLabel("TRỢ GIÚP");
		lb_trogiup.setHorizontalAlignment(SwingConstants.CENTER);
		lb_trogiup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_trogiup.setBounds(0, 301, 244, 42);
		menu.add(lb_trogiup);
		
		JLabel lb_chinhsach = new JLabel("CHÍNH SÁCH");
		lb_chinhsach.setHorizontalAlignment(SwingConstants.CENTER);
		lb_chinhsach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_chinhsach.setBounds(0, 344, 244, 42);
		menu.add(lb_chinhsach);
		
		JLabel lb_dangxuat = new JLabel("ĐĂNG XUẤT");
		lb_dangxuat.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dangxuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_dangxuat.setBounds(0, 387, 244, 42);
		menu.add(lb_dangxuat);
		
		JLabel lb_close = new JLabel("");
		lb_close.setIcon(new ImageIcon(menu.class.getResource("/Design/cancel.jpg")));;
		lb_close.setBounds(209, 0, 24, 30);
		menu.add(lb_close);
		
		JLabel lb_menu = new JLabel("");
		lb_menu.setBounds(21, 21, 34, 24);
		int width = 244;
		int height = 750;
		

		lb_menu.setIcon(new ImageIcon(menu.class.getResource("/Design/menu.jpg")));
		pn_home.add(lb_menu);
		
		JPanel pn_admin = new JPanel();
		pn_admin.setBackground(new Color(0, 128, 128));
		pn_admin.setBounds(0, 67, 236, 653);
		pn_home.add(pn_admin);
		pn_admin.setLayout(null);
		
		JLabel lb_avatar = new JLabel("");
		lb_avatar.setBounds(10, 11, 85, 81);
		pn_admin.add(lb_avatar);
		lb_avatar.setIcon(new ImageIcon(menu.class.getResource("/Design/avatar.png")));
		
		
		JPanel container = new JPanel();
		container.setBounds(0, 67, 1280, 683);
		pn_home.add(container);
		container.setLayout(new CardLayout(0, 0));
		
		
		JPanel home = new JPanel();
		home.setBackground(SystemColor.window);
		container.add(home, "home");
		home.setLayout(null);
		
		
		JPanel pn_trangchu = new JPanel();
		pn_trangchu.setBounds(237, 0, 1033, 650);
		pn_trangchu.setBackground(Color.WHITE);
		pn_trangchu.setOpaque(false);
		home.add(pn_trangchu);
		pn_trangchu.setLayout(null);
		
		JLabel lb_wtts = new JLabel("WELCOM TO "+"THE STAYLEARN");
		lb_wtts.setForeground(new Color(255, 255, 0));
		lb_wtts.setFont(new Font("Tahoma", Font.BOLD, 40));
		lb_wtts.setBounds(415, 196, 592, 332);
		pn_trangchu.add(lb_wtts);
		
		JLabel lb_anhnen = new JLabel("");
		lb_anhnen.setBounds(0, 0, 1033, 650);
		pn_trangchu.add(lb_anhnen);
		lb_anhnen.setIcon(new ImageIcon(menu.class.getResource("/Design/hoctap4.jpg")));
		
		
		JPanel account = new JPanel();
		account.setBackground(new Color(255, 255, 255));
		container.add(account, "account");
		account.setLayout(null);
		
		JPanel pn_button = new JPanel();
		pn_button.setBounds(244, 11, 988, 34);
		pn_button.setBackground(Color.WHITE);
		account.add(pn_button);
		pn_button.setLayout(null);
		pn_button.setOpaque(false);
		
		JButton bt_quaylai = new JButton("Quay lại");
		bt_quaylai.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_quaylai.setBackground(new Color(240, 255, 240));
		bt_quaylai.setBounds(491, 0, 101, 34);
		bt_quaylai.setForeground(new Color(0, 0, 0));
		
		pn_button.add(bt_quaylai);
		
		JButton bt_them = new JButton("Thêm");
		bt_them.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_them.setBackground(new Color(240, 255, 240));
		bt_them.setBounds(602, 0, 89, 34);
		bt_them.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_them);
		
		JButton bt_xoa = new JButton("Xóa");
		bt_xoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_xoa.setBackground(new Color(240, 255, 240));
		bt_xoa.setBounds(701, 0, 89, 34);
		bt_xoa.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_xoa);
		
		JButton bt_luu = new JButton("Lưu");
		bt_luu.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_luu.setBackground(new Color(240, 255, 240));
		bt_luu.setBounds(800, 0, 89, 34);
		bt_luu.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_luu);
		
		JButton bt_thoat = new JButton("Thoát");
		bt_thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_thoat.setBackground(new Color(240, 255, 240));
		bt_thoat.setBounds(899, 0, 89, 34);
		bt_thoat.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_thoat);
		
		JPanel pn_ttcn = new JPanel();
		pn_ttcn.setBounds(235, 56, 1035, 650);
		pn_ttcn.setBackground(new Color(255, 255, 255));
		account.add(pn_ttcn);
		pn_ttcn.setLayout(null);
		
	    
	    URL iconURL_quaylai = thongtincanhan.class.getResource("quaylai.png");
	    ImageIcon icon1 = new ImageIcon(iconURL_quaylai);
	    bt_quaylai.setIcon(icon1);
	    
	    URL iconURL_xoa = thongtincanhan.class.getResource("xoa.png");
	    ImageIcon icon2 = new ImageIcon(iconURL_xoa);
	    bt_xoa.setIcon(icon2);
	    
	    URL iconURL_them = thongtincanhan.class.getResource("them.png");
	    ImageIcon icon3 = new ImageIcon(iconURL_them);
	    bt_them.setIcon(icon3);
	    
	    URL iconURL_luu = thongtincanhan.class.getResource("luu.png");
	    ImageIcon icon4 = new ImageIcon(iconURL_luu);
	    bt_luu.setIcon(icon4);
		
		JLabel lb_ttcn = new JLabel("Thông tin cá nhân");
		lb_ttcn.setBackground(new Color(255, 255, 255));
		lb_ttcn.setBounds(10, 11, 291, 50);
		lb_ttcn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pn_ttcn.add(lb_ttcn);
		

		URL iconURL_thongtin = thongtincanhan.class.getResource("thongtin.icon.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_thongtin);
	    lb_ttcn.setIcon(icon0);
		
		JLabel lb_mahocvien = new JLabel("Mã học viên *");
		lb_mahocvien.setBounds(20, 90, 104, 31);
		lb_mahocvien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_mahocvien);
		
		tf_mahocvien = new JTextField();
		tf_mahocvien.setBounds(155, 92, 210, 30);
		tf_mahocvien.setColumns(10);
		pn_ttcn.add(tf_mahocvien);
		
		tf_hovaten = new JTextField();
		tf_hovaten.setBounds(155, 147, 210, 30);
		tf_hovaten.setColumns(10);
		pn_ttcn.add(tf_hovaten);
		
		tf_ngaysinh = new JTextField();
		tf_ngaysinh.setBounds(155, 260, 210, 30);
		tf_ngaysinh.setColumns(10);
		pn_ttcn.add(tf_ngaysinh);
		
		JLabel lb_hovaten = new JLabel("Họ và tên *");
		lb_hovaten.setBounds(20, 145, 100, 30);
		lb_hovaten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_hovaten);
		
		JLabel lb_gt = new JLabel("Giới tính");
		lb_gt.setBounds(20, 200, 104, 30);
		lb_gt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_gt);
		
		String [] gt = {"Nam", "Nữ"};
		JComboBox cbb_gioitinh = new JComboBox(gt);
		cbb_gioitinh.setBounds(155, 200, 210, 30);
		cbb_gioitinh.setSelectedIndex(-1);
		pn_ttcn.add(cbb_gioitinh);
		
		JLabel lb_sdt = new JLabel("Số điện thoại *");
		lb_sdt.setBounds(461, 91, 125, 31);
		lb_sdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_sdt);
		
		tf_sdt = new JTextField();
		tf_sdt.setBounds(618, 92, 210, 30);
		tf_sdt.setColumns(10);
		pn_ttcn.add(tf_sdt);
		
		tf_email = new JTextField();
		tf_email.setBounds(618, 147, 210, 30);
		tf_email.setColumns(10);
		pn_ttcn.add(tf_email);
		
		JLabel lb_ngaysinh = new JLabel("Ngày sinh");
		lb_ngaysinh.setBounds(20, 260, 104, 31);
		lb_ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_ngaysinh);
		
		JLabel lb_email = new JLabel("Email");
		lb_email.setBounds(461, 145, 104, 30);
		lb_email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_email);
		
		tf_diachi = new JTextField();
		tf_diachi.setBounds(155, 322, 210, 30);
		tf_diachi.setColumns(10);
		pn_ttcn.add(tf_diachi);
		
		tf_tenphuhuynh = new JTextField();
		tf_tenphuhuynh.setBounds(618, 202, 210, 30);
		tf_tenphuhuynh.setColumns(10);
		pn_ttcn.add(tf_tenphuhuynh);
		
		tf_sdtph = new JTextField();
		tf_sdtph.setBounds(618, 260, 210, 30);
		tf_sdtph.setColumns(10);
		pn_ttcn.add(tf_sdtph);
		
		JLabel lb_diachi = new JLabel("Địa chỉ");
		lb_diachi.setBounds(20, 320, 81, 32);
		lb_diachi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_diachi);
		
		JLabel lb_tenphuhuynh = new JLabel("Tên phụ huynh *");
		lb_tenphuhuynh.setBounds(461, 200, 125, 30);
		lb_tenphuhuynh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_tenphuhuynh);
		
		JLabel lb_sdtph = new JLabel("SĐT phụ huynh *");
		lb_sdtph.setBounds(461, 258, 125, 30);
		lb_sdtph.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_sdtph);
		
		tf_datett = new JTextField();
		tf_datett.setBounds(618, 322, 210, 31);
		tf_datett.setColumns(10);
		pn_ttcn.add(tf_datett);
		
		JLabel lb_datett = new JLabel("Ngày đến trung tâm");
		lb_datett.setBounds(461, 320, 143, 31);
		lb_datett.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_datett);
		
		JPanel pn_ttcn1 = new JPanel();
		pn_ttcn1.setBackground(new Color(192, 192, 192));
		pn_ttcn1.setBounds(1, 0, 298, 67);
		pn_ttcn.add(pn_ttcn1);
		
		JLabel lb_ttcn1 = new JLabel("");
		lb_ttcn1.setBounds(1, 0, 1053, 602);
		pn_ttcn.add(lb_ttcn1);
		lb_ttcn1.setIcon(new ImageIcon(menu.class.getResource("/Design/anhnen2.jpg")));
		
		JButton bt_email = new JButton("");
		bt_email.setBackground(new Color(255, 255, 255));
		bt_email.setBounds(1056, 11, 34, 30);
		bt_email.setIcon(new ImageIcon(menu.class.getResource("/Design/email.png")));
		pn_home.add(bt_email);
		
		JButton bt_thongbao = new JButton("");
		bt_thongbao.setBackground(new Color(255, 255, 255));
		bt_thongbao.setBounds(1106, 11, 34, 30);
		bt_thongbao.setIcon(new ImageIcon(menu.class.getResource("/Design/thongbao.png")));
		pn_home.add(bt_thongbao);
		
		JButton bt_cake = new JButton("");
		bt_cake.setBackground(new Color(255, 255, 255));
		bt_cake.setBounds(1156, 11, 34, 30);
		bt_cake.setIcon(new ImageIcon(menu.class.getResource("/Design/cake.png")));
		pn_home.add(bt_cake);
		
		bt_luu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String studentID = tf_mahocvien.getText();
	            String fullName = tf_hovaten.getText();
	            String gender = cbb_gioitinh.getSelectedItem().toString();
	            String dob = tf_ngaysinh.getText();
	            String phoneNumber = tf_sdt.getText();
	            String email = tf_email.getText();
	            String address = tf_diachi.getText();
	            String parentName = tf_tenphuhuynh.getText();
	            String parentPhoneNumber = tf_sdtph.getText();
	            String joinDate = tf_datett.getText();

	            WriteTextFile.writeToFile(studentID, fullName, gender, dob, phoneNumber, email, address, parentName, parentPhoneNumber, joinDate);
	        }
	    });
		
		lb_menu.addMouseListener(new MouseAdapter() {
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
		
		lb_close.addMouseListener(new MouseAdapter() {
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
		
		
		lb_trangchu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout)(container.getLayout());
				c1.show(container, "home");
				menu.setSize(0,750);
			}
		});
		
		lb_thongtin.addMouseListener(new MouseAdapter() {
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
