package Design;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.toedter.calendar.JDateChooser;

import Controller.Client;
import Controller.WriteTextFile_User;

import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JCheckBox;


public class User_Home extends JFrame {
	public static JTextField tf_mahocvien;
	public static JTextField tf_hovaten;
	public static JDateChooser tf_ngaysinh;
	public static JTextField tf_sdt;
	public static JTextField tf_email;
	public static JTextField tf_diachi;
	public static JTextField tf_tenphuhuynh;
	public static JTextField tf_sdtph;
	public static JDateChooser tf_datett;
	Vector vT, vD;
	private final ExecutorService executorService = Executors.newFixedThreadPool(10);
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField tf_username;
	public static JComboBox cbb_gioitinh;
	JScrollPane thanhcuon1;
	private JTextArea tf_chat;
	private JTextPane textPane;
	private JButton bt_gui;
	private static Socket socket;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static JTable table;
	public static JTable table_2;
	private JTextField tf_nguoithu;
	
	
	private DefaultTableModel model;
    private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxChngTrnhPh;
    private JCheckBox chckbxChngTrnhNng;
    private JDateChooser tf_ngaythu;
    Client client;
    
    public static String tenNguoiNop;
    public static String tenKhoaHoc;
    public static int soTien;
    public static String hinhThucThanhToan;
    public static Date ngayThu;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Home frame = new User_Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadDataToTable2() {
		executorService.submit(() -> {
			try {
	        	DefaultTableModel model = (DefaultTableModel) table_2.getModel();
	            client.sendtoServer("/loaddatatotable2", "");
	            String response = client.readMessage();
	            System.out.println("Received data from server: ");  // In dữ liệu sau khi nhận

	            Vector<Vector<String>> data = deserializeVector(response);
	            model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

	            for (Vector<String> row : data) {
	                model.addRow(row);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		});
	}
	
	public void load_data() {
		executorService.submit(() -> {
			try {
		        client.sendtoServer("/loadDataStudent", tf_username.getText());
		        String response = client.readMessage();
		        String[] data = response.split(";;;");
		        if (data.length >= 10) {
		            tf_mahocvien.setText(data[0]);
		            tf_hovaten.setText(data[1]);
		            cbb_gioitinh.setSelectedItem(data[2]);
		            if (data[3] != null && !data[3].isEmpty()) {
		                try {
		                    Date ngaySinhDate = sdf.parse(data[3]);
		                    tf_ngaysinh.setDate(ngaySinhDate);
		                } catch (ParseException ex) {
		                    ex.printStackTrace();
		                }
		            }
		            tf_diachi.setText(data[4]);
		            tf_sdt.setText(data[5]);
		            tf_email.setText(data[6]);
		            tf_tenphuhuynh.setText(data[7]);
		            tf_sdtph.setText(data[8]);
		            if (data[9] != null && !data[9].isEmpty()) {
		                try {
		                    Date ngaydenDate = sdf.parse(data[9]);
		                    tf_datett.setDate(ngaydenDate);
		                } catch (ParseException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		});
	}

	
	public User_Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		URL url_hhd = User_Home.class.getResource("logo.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);
		
		JPanel pn_home = new JPanel();
		pn_home.setBackground(new Color(250, 250, 210));
		pn_home.setBounds(0, 0, 1280, 750);
		contentPane.add(pn_home);
		pn_home.setLayout(null);
		
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(253, 245, 230));
		menu.setBounds(0, 0, 0, 750);
		pn_home.add(menu);
		menu.setLayout(null);
		
		JPanel pn_admin = new JPanel();
		pn_admin.setBackground(new Color(189, 183, 107));
		pn_admin.setBounds(0, 67, 236, 653);
		pn_home.add(pn_admin);
		pn_admin.setLayout(null);
		
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
		
		JLabel lb_dkct = new JLabel("CHƯƠNG TRÌNH HỌC");
		lb_dkct.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dkct.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_dkct.setBounds(0, 257, 244, 42);
		menu.add(lb_dkct);
		
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
		
		JLabel lb_message = new JLabel("TRÒ CHUYỆN");
		lb_message.setHorizontalAlignment(SwingConstants.CENTER);
		lb_message.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_message.setBounds(0, 387, 244, 42);
		menu.add(lb_message);
		
		JLabel lb_dangxuat = new JLabel("ĐĂNG XUẤT");
		lb_dangxuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Form_Login loginForm = new Form_Login();
		        loginForm.setVisible(true);
			}
		});
		
		client = Form_Login.runClient(Form_Login.userName);
		
		lb_dangxuat.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dangxuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_dangxuat.setBounds(0, 425, 244, 42);
		menu.add(lb_dangxuat);
		
		JLabel lb_close = new JLabel("");
		lb_close.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/cancel.jpg")));;
		lb_close.setBounds(209, 0, 24, 30);
		menu.add(lb_close);
		
		JLabel lb_menu = new JLabel("");
		lb_menu.setBounds(21, 21, 34, 24);
		int width = 244;
		int height = 750;
		

		lb_menu.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/menu.jpg")));
		pn_home.add(lb_menu);
		
		
		JLabel lb_avatar = new JLabel("");
		lb_avatar.setBounds(10, 11, 85, 81);
		pn_admin.add(lb_avatar);
		lb_avatar.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/avatar.png")));
		
		Form_Login dk = new Form_Login();
		String username = Form_Login.userName;
		
		tf_username = new JTextField();
		tf_username.setBackground(new Color(250, 250, 210));
		tf_username.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_username.setText(username);
		tf_username.setBounds(85, 40, 141, 25);
		pn_admin.add(tf_username);
		tf_username.setColumns(10);
		
		
		JPanel container = new JPanel();
		container.setBounds(0, 67, 1280, 683);
		pn_home.add(container);
		container.setLayout(new CardLayout(0, 0));
		
		
		JPanel home = new JPanel();
		home.setBackground(SystemColor.window);
		container.add(home, "home");
		home.setLayout(null);
		
		
		JPanel pn_trangchu = new JPanel();
		pn_trangchu.setBounds(235, 0, 1033, 650);
		pn_trangchu.setBackground(Color.WHITE);
		pn_trangchu.setOpaque(false);
		home.add(pn_trangchu);
		pn_trangchu.setLayout(null);
		
		
		
		JPanel pn_wel = new JPanel();
		pn_wel.setBackground(new Color(255, 255, 255, 100));
		pn_wel.setLayout(null);
		pn_wel.setBounds(320, 400, 750, 100);
		pn_trangchu.add(pn_wel);
		
		JLabel lb_anhnen = new JLabel("");
		lb_anhnen.setBounds(0, 0, 1033, 650);
		pn_trangchu.add(lb_anhnen);
		lb_anhnen.setIcon(new ImageIcon(User_Home.class.getResource("/Design/back_user.jpg")));
		
		JLabel lb_wtts = new JLabel("WELCOME TO STAYLEARN");
		lb_wtts.setHorizontalAlignment(SwingConstants.LEFT);
		lb_wtts.setForeground(new Color(220, 20, 60));
		lb_wtts.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lb_wtts.setBackground(new Color(255, 255, 255, 100));
		lb_wtts.setBounds(10, 25, 650, 49);
		pn_wel.add(lb_wtts);
		
		
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
		bt_quaylai.setBounds(590, 0, 101, 34);
		bt_quaylai.setForeground(new Color(0, 0, 0));
		
		pn_button.add(bt_quaylai);
		
		JButton bt_sua = new JButton("Sửa thông tin");
		bt_sua.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_sua.setBackground(new Color(240, 255, 240));
		bt_sua.setBounds(710, 0, 150, 34);
		bt_sua.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_sua);
		
		JButton bt_in = new JButton("In");
		bt_in.setFont(new Font("Tahoma", Font.BOLD, 11));
		bt_in.setBackground(new Color(240, 255, 240));
		bt_in.setBounds(880, 0, 89, 34);
		bt_in.setForeground(new Color(0, 0, 0));
		pn_button.add(bt_in);
		
		JPanel pn_ttcn = new JPanel();
		pn_ttcn.setBounds(235, 56, 1035, 650);
		pn_ttcn.setBackground(new Color(255, 255, 255));
		account.add(pn_ttcn);
		pn_ttcn.setLayout(null);
		
	    
	    URL iconURL_quaylai = User_Home.class.getResource("quaylai.png");
	    ImageIcon icon1 = new ImageIcon(iconURL_quaylai);
	    bt_quaylai.setIcon(icon1);
	    
	    URL iconURL_xoa = User_Home.class.getResource("xoa.png");
	    ImageIcon icon2 = new ImageIcon(iconURL_xoa);
	    
	    URL iconURL_them = User_Home.class.getResource("them.png");
	    ImageIcon icon3 = new ImageIcon(iconURL_them);
	    bt_sua.setIcon(icon3);
	    
	    URL iconURL_luu = User_Home.class.getResource("luu.png");
	    ImageIcon icon4 = new ImageIcon(iconURL_luu);
	    bt_in.setIcon(icon4);
		
		JLabel lb_ttcn = new JLabel("Thông tin cá nhân");
		lb_ttcn.setBackground(new Color(255, 255, 255));
		lb_ttcn.setBounds(10, 11, 291, 50);
		lb_ttcn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pn_ttcn.add(lb_ttcn);
		

		URL iconURL_thongtin = User_Home.class.getResource("thongtin.icon.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_thongtin);
	    lb_ttcn.setIcon(icon0);
		
		JLabel lb_mahocvien = new JLabel("Mã học viên *");
		lb_mahocvien.setBounds(20, 90, 104, 31);
		lb_mahocvien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_mahocvien);
		
		tf_mahocvien = new JTextField();
		tf_mahocvien.setBounds(155, 92, 210, 30);
		tf_mahocvien.setColumns(10);
		tf_mahocvien.setEditable(false);
		pn_ttcn.add(tf_mahocvien);
		
		tf_hovaten = new JTextField();
		tf_hovaten.setBounds(155, 147, 210, 30);
		tf_hovaten.setColumns(10);
		pn_ttcn.add(tf_hovaten);
		
		tf_ngaysinh = new JDateChooser();
		tf_ngaysinh.setBounds(155, 260, 210, 30);
		
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
		cbb_gioitinh = new JComboBox(gt);
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
		
		tf_datett = new JDateChooser();
		tf_datett.setBounds(618, 322, 210, 31);
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
		lb_ttcn1.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/anhnen2.jpg")));
		
		JButton bt_email = new JButton("");
		bt_email.setBackground(new Color(255, 255, 255));
		bt_email.setBounds(1056, 11, 34, 30);
		bt_email.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/email.png")));
		pn_home.add(bt_email);
		
		JButton bt_thongbao = new JButton("");
		bt_thongbao.setBackground(new Color(255, 255, 255));
		bt_thongbao.setBounds(1106, 11, 34, 30);
		bt_thongbao.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/thongbao.png")));
		pn_home.add(bt_thongbao);
		
		JButton bt_cake = new JButton("");
		bt_cake.setBackground(new Color(255, 255, 255));
		bt_cake.setBounds(1156, 11, 34, 30);
		bt_cake.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/cake.png")));
		pn_home.add(bt_cake);
		
		JPanel message = new JPanel();
		// account.setBackground(new Color(255, 255, 255));
		container.add(message, "message");
		message.setLayout(null);

		tf_chat = new JTextArea();
		tf_chat.setBackground(new Color(128, 255, 255));
		tf_chat.setBounds(271, 580, 900, 50);
		message.add(tf_chat);

		bt_gui = new JButton("GỬI");
		bt_gui.setBounds(1181, 580, 60, 50);
		message.add(bt_gui);

		textPane = new JTextPane();
        textPane.setFont(new Font("Monospaced", Font.BOLD, 18));
        textPane.setEditable(false);
        textPane.setBorder(new LineBorder(Color.GRAY, 1));
        
        JScrollPane scrollPane_1 = new JScrollPane(textPane);
        scrollPane_1.setBounds(271, 61, 900, 447);
        message.add(scrollPane_1);
		
	
		
		JPanel hocphi = new JPanel();
		hocphi.setBackground(new Color(255, 255, 255));
		container.add(hocphi, "hocphi");
		hocphi.setLayout(null);
		
		JPanel hocphi_1 = new JPanel();
		hocphi_1.setBounds(281, 52, 945, 513);
		hocphi.add(hocphi_1);
		hocphi_1.setLayout(null);

		JLabel lb_hdhp = new JLabel("Hóa đơn học phí");
		lb_hdhp.setBounds(384, 26, 220, 45);
		hocphi_1.add(lb_hdhp);
		lb_hdhp.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lb_csnn = new JLabel("CƠ SỞ NGOẠI NGỮ - STAYLEARN ");
		lb_csnn.setBounds(184, 83, 311, 27);
		lb_csnn.setFont(new Font("Tahoma", Font.BOLD, 17));
		hocphi_1.add(lb_csnn);

		JLabel lb_đvtt = new JLabel("Đơn vị thu tiền:");
		lb_đvtt.setBounds(51, 83, 123, 26);
		lb_đvtt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_đvtt);

		JLabel lb_dchp = new JLabel("Địa chỉ:");
		lb_dchp.setBounds(51, 121, 75, 26);
		lb_dchp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_dchp);

		JLabel lb_dchp1 = new JLabel("xx, đường xx, phường xx, quận xx, tỉnh Đà Nẵng");
		lb_dchp1.setBounds(139, 121, 378, 26);
		lb_dchp1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_dchp1);

		JLabel lb_stk = new JLabel("Số tài khoản:");
		lb_stk.setBounds(51, 158, 116, 26);
		lb_stk.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_stk);

		JLabel lb_mst = new JLabel("MST:  .............................................");
		lb_mst.setBounds(467, 158, 220, 26);
		lb_mst.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_mst);

		JLabel lb_stk1 = new JLabel("XXXX0023213");
		lb_stk1.setBounds(171, 158, 173, 26);
		lb_stk1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_stk1);

		JLabel lb_hvtnn = new JLabel("Họ tên người nộp:");
		lb_hvtnn.setBounds(51, 195, 141, 26);
		lb_hvtnn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_hvtnn);

		tf_nguoithu = new JTextField();
		tf_nguoithu.setBounds(197, 195, 256, 26);
		hocphi_1.add(tf_nguoithu);
		tf_nguoithu.setColumns(10);

		JLabel lb_tkh_1 = new JLabel("Tên khóa học:");
		lb_tkh_1.setBounds(51, 232, 123, 26);
		lb_tkh_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_tkh_1);

		JLabel lb_httt = new JLabel("Hình thức thanh toán:");
		lb_httt.setBounds(51, 273, 173, 26);
		lb_httt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_httt);

		JLabel lb_stk_2_1 = new JLabel("Người thu tiền");
		lb_stk_2_1.setBounds(715, 404, 116, 26);
		lb_stk_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hocphi_1.add(lb_stk_2_1);


		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("STT");
//		model.addColumn("Tên người nộp");
//		model.addColumn("Tên khóa học");
//		model.addColumn("Số tiền");
//		model.addColumn("Hình thức thanh toán");
//		model.addColumn("Ngày thu");
		table.setModel(model);
		
		JComboBox ccb_tkh = new JComboBox();
		ccb_tkh.setBounds(171, 232, 282, 27);
		ccb_tkh.addItem("Khóa học cơ bản");
		ccb_tkh.addItem("Khóa học phổ thông");
		ccb_tkh.addItem("Khóa học nâng cao");
		hocphi_1.add(ccb_tkh);
		
		JLabel lb_ngaythu = new JLabel("Ngày thu:");
		lb_ngaythu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lb_ngaythu.setBounds(516, 195, 123, 26);
		hocphi_1.add(lb_ngaythu);
		
		JComboBox cbb_httt = new JComboBox();
		cbb_httt.setBounds(233, 273, 220, 27);
		cbb_httt.addItem("Chuyển khoản");
		cbb_httt.addItem("Tiền mặt");
		hocphi_1.add(cbb_httt);
		
		tf_ngaythu = new JDateChooser();
		tf_ngaythu.setBounds(602, 198, 190, 26);
		hocphi_1.add(tf_ngaythu);
		
		JButton bt_xn = new JButton("Xác nhận");
		bt_xn.setFont(new Font("Tahoma", Font.BOLD, 15));
		bt_xn.setBounds(695, 579, 119, 30);
		hocphi.add(bt_xn);
		
		Map<String, Integer> khoaHocSoTienMap = new HashMap<>();
		khoaHocSoTienMap.put("Khóa học cơ bản", 1700000);
		khoaHocSoTienMap.put("Khóa học phổ thông", 3000000);
		khoaHocSoTienMap.put("Khóa học nâng cao", 5400000);

		bt_xn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy dữ liệu từ các trường nhập liệu
		        String tenNguoiNop = tf_nguoithu.getText();
		        String tenKhoaHoc = ccb_tkh.getSelectedItem().toString();
		        int soTien = khoaHocSoTienMap.get(tenKhoaHoc); // Lấy số tiền từ Map
		        String hinhThucThanhToan = cbb_httt.getSelectedItem().toString();
		         
		        Date ngayThu = tf_ngaythu.getDateEditor().getDate();
		        String username = tf_username.getText();

		        // Xóa trống các trường nhập liệu sau khi thêm dữ liệu thành công (nếu cần)
		        tf_nguoithu.setText("");
		        
		        client.xacnhanhocphi("/hocphi", tenNguoiNop, tenKhoaHoc, soTien, hinhThucThanhToan, ngayThu, username);
		        
		        try {
					String message = client.readMessage();
					if (message.equals("success")){
						System.out.println("Them du lieu thanh cong");
					}
					else {
						System.out.println("Khong thanh cong");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		
		JPanel lichhoc = new JPanel();
		lichhoc.setBackground(SystemColor.window);
		container.add(lichhoc, "lichhoc");
		lichhoc.setLayout(null);

		JLabel titleLabel = new JLabel("Lịch Học", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
		// titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		titleLabel.setBounds(166, 10, 291, 50);
		lichhoc.add(titleLabel);
		
		
		JLabel giohoc1 = new JLabel("Sáng");
		giohoc1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		giohoc1.setBounds(976, 25, 114, 17);
		lichhoc.add(giohoc1);
		int a = 40;
		for (int i = 1; i < 6; i++) {

			JLabel tiet = new JLabel("Tiết " + i + " : " + (i + 6) + "h30");
			tiet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tiet.setBounds(976, a, 114, 17);
			lichhoc.add(tiet);
			
			a = a + 15;
		}
		
		
		JLabel giohoc2 = new JLabel("Chiều");
		giohoc2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		giohoc2.setBounds(1115, 25, 114, 17);
		lichhoc.add(giohoc2);
		int b=40 ;
		for (int i = 6 ;i < 11; i++) {

			JLabel tiet = new JLabel("Tiết " + i + " : " + (i + 7) + "h00");
			tiet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tiet.setBounds(1115, b, 114, 17);
			lichhoc.add(tiet);
			b = b + 15;
		}
		
		String[] columnNames = { "", "THỨ 2", "THỨ 3", "THỨ 4", "THỨ 5", "THỨ 6", "THỨ 7", "CHỦ NHẬT" };
		Object[][] data = { { "Tiết 1", "", "", "", "", "", "" }, { "Tiết 2", "", "", "", "", "", "" },
				{ "Tiết 3", "", "", "", "", "", "" }, { "Tiết 4", "", "", "", "", "", "" },
				{ "Tiết 5", "", "", "", "hi", "", "" }, { "Tiết 6", "", "", "", "", "", "" },
				{ "Tiết 7", "", "", "", "", "", "" }, { "Tiết 8", "", "", "", "", "", "jello" },
				{ "Tiết 9", "", "", "", "", "", "" } };

		// Tạo JTable với DefaultTableModel
		DefaultTableModel bang = new DefaultTableModel();
		bang.addColumn("");
		bang.addColumn("Thứ2");
		bang.addColumn("Thứ3");
		bang.addColumn("Thứ4");
		bang.addColumn("Thứ5");
		bang.addColumn("Thứ6");
		bang.addColumn("Thứ7");
		bang.addColumn("Chủ Nhật");
		
		table_2 = new JTable();
		
		table_2.setModel(bang);
		
		// Tùy chỉnh JTable
		table_2.setRowHeight(50);
		table_2.setFont(new Font("Serif", Font.PLAIN, 16));
		table_2.getTableHeader().setFont(new Font("Serif", Font.BOLD, 18));
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		/// table.setBounds(274, 167, 900, 450);
		// lichhoc.add(table);
		// Thiết lập chiều rộng cho các cột
		for (int i = 0; i < table_2.getColumnCount(); i++) {
			table_2.getColumnModel().getColumn(i).setPreferredWidth(120);
		}

		// Renderer để tùy chỉnh màu sắc
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (row % 2 == 0) {
					cell.setBackground(new Color(220, 220, 220)); // Màu xám nhạt cho các hàng chẵn
				} else {
					cell.setBackground(Color.WHITE); // Màu trắng cho các hàng lẻ
				}
				if (isSelected) {
					cell.setBackground(new Color(184, 207, 229)); // Màu xanh nhạt khi được chọn
				}
				return cell;
			}
		};

		for (int i = 0; i < table_2.getColumnCount(); i++) {
			table_2.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}

		// Tạo JScrollPane bao quanh JTable
		JScrollPane thanhcuon1 = new JScrollPane(table_2);
		thanhcuon1.setBounds(263, 170, 973, 429);
		lichhoc.add(thanhcuon1);
		loadDataToTable2();
	
		JButton diemdanh = new JButton("Điểm Danh");
		diemdanh.setFont(new Font("Tahoma", Font.BOLD, 12));
		diemdanh.setBounds(263, 122, 151, 38);
		
		lichhoc.add( diemdanh);
		
		diemdanh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_2.getSelectedRow();
		        int selectedColumn = table_2.getSelectedColumn();
		        
		        if (selectedRow != -1 && selectedColumn != -1) {
		            Object value = table_2.getValueAt(selectedRow, selectedColumn);
		            
		            if (value == null || value.toString().isEmpty()) {
		                // Nếu giá trị là null hoặc chuỗi rỗng
		                System.out.println("Giá trị của ô là null hoặc rỗng.");
		            } else {
		                // Nếu giá trị không phải là null
		                System.out.println("Giá trị của ô không phải là null." + value);
		            }
		        }
		    }
		});
		
		
		
		bt_in.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String studentID = tf_mahocvien.getText();
	            String fullName = tf_hovaten.getText();
	            String gender = cbb_gioitinh.getSelectedItem().toString();
	            String dob = sdf.format(tf_ngaysinh.getDate());
	            String phoneNumber = tf_sdt.getText();
	            String email = tf_email.getText();
	            String address = tf_diachi.getText();
	            String parentName = tf_tenphuhuynh.getText();
	            String parentPhoneNumber = tf_sdtph.getText();
	            String joinDate = sdf.format(tf_datett.getDate());

	            WriteTextFile_User.writeToFile(studentID, fullName, gender, dob, phoneNumber, email, address, parentName, parentPhoneNumber, joinDate);
	        }
	    });
		
		lb_menu.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int targetWidth = 240; 
		        int step = 10;

		        Timer timer = new Timer(10, new ActionListener() {
		            int menuWidth = 0;

		            @Override
		            public void actionPerformed(ActionEvent e) {
		                menuWidth += step;
		                menu.setSize(menuWidth, height);
		                menu.revalidate(); // Cập nhật lại giao diện

		                if (menuWidth >= targetWidth) {
		                    ((Timer) e.getSource()).stop();
		                }
		            }
		        });

		        timer.start();
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
				load_data();
			}
		});

		
		lb_lichhoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout)(container.getLayout());
				c1.show(container, "lichhoc");
				menu.setSize(0,750);
				
				try {
					loadDataToTable2();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
				
		lb_hocphi.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        CardLayout cl = (CardLayout) container.getLayout();
		        cl.show(container, "hocphi");
		        menu.setSize(0,750);
		        
		    }
		});
		lb_dkct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  CardLayout cl = (CardLayout) container.getLayout();
			        cl.show(container, "dkct");
			        menu.setSize(0,750);
			}
		});
		
		bt_sua.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	executorService.submit(() -> {
		    		try {
			            String name = tf_hovaten.getText();
			            String gender = cbb_gioitinh.getSelectedItem().toString();
			            String dateOfBirth = sdf.format(tf_ngaysinh.getDate());
			            String address = tf_diachi.getText();
			            String phoneNumber = tf_sdt.getText();
			            String email = tf_email.getText();
			            String parentName = tf_tenphuhuynh.getText();
			            String phoneParent = tf_sdtph.getText();
			            String dayArrive = sdf.format(tf_datett.getDate());
			            String username = tf_username.getText();

			            client.capnhapthongtin("/updateStudent", name, gender, dateOfBirth, address, phoneNumber, email, parentName, phoneParent, dayArrive, username);
			            String response = client.readMessage();
			            if (response.equals("success")) {
			                JOptionPane.showMessageDialog(null, "Cập nhập thành công");
			            } else {
			                JOptionPane.showMessageDialog(null, "Cập nhập không thành công");
			            }
			        } catch (Exception e2) {
			            JOptionPane.showMessageDialog(null, "Cập nhập không thành công");
			            e2.printStackTrace();
			        }
				});
		    }
		});
		
		
		bt_gui.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String message = tf_chat.getText();
	            client.sendMessage(message);
	            tf_chat.setText("");
	            System.out.println("Đã gửi tin nhắn: " + message);
	        }
	    });
		
		
		lb_message.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "message");
				menu.setSize(0, 750);
				startListeningForMessages();
			}
		});
		
		
		
		
		setTitle("STAYLEARN");
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	private void startListeningForMessages() {
	    new Thread(() -> {
	        try {
	            String message;
	            while ((message = client.readMessage()) != null) {
	            	if (message.startsWith("CHAT:")) { 
	                    final String chatMessage = message.substring(5).trim(); 
	                    SwingUtilities.invokeLater(() -> appendToPane(textPane, chatMessage + "\n", Color.BLACK));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }).start();
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c) {
        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord, c);
        StyleConstants.setFontFamily(keyWord, "Monospaced");
        StyleConstants.setFontSize(keyWord, 18);
        StyleConstants.setLineSpacing(keyWord, 1.0f); 

        try {
            doc.insertString(doc.getLength(), msg, keyWord);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
	
	private Vector<Vector<String>> deserializeVector(String response) {
	    System.out.println("Deserialize vector called with response: " + response); // Debug thêm
	    Vector<Vector<String>> vector = new Vector<>();
	    if (response == null || response.isEmpty()) {
	        return vector;
	    }
	    String[] rows = response.split("\\|\\|\\|");
	    for (String row : rows) {
	        if (!row.isEmpty()) {
	            Vector<String> innerVector = new Vector<>();
	            String[] elements = row.split(";;;");
	            for (String element : elements) {
	                innerVector.add(element.trim());
	            }
	            vector.add(innerVector);
	        }
	    }
	    System.out.println("Deserialized vector: " + vector); // Debug thêm
	    return vector;
	}

}