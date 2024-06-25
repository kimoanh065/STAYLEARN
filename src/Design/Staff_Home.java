package Design;

import java.awt.Container;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import Controller.Client;
import Design.*;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Staff_Home extends JFrame {
	private JTextField tf_manhanvien;
	private JTextField tf_hovaten;
	private JDateChooser tf_ngaysinh;
	private JTextField tf_sdt;
	private JTextField tf_email;
	private JTextField tf_diachi;
	private JComboBox tf_vitri;
	JTextField tf_staffname;
	private JComboBox cbb_gioitinh;
	private JTextArea tf_chat;
	private JTextArea tf_giaodien;
	private JButton bt_gui;
	private static Socket socket;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	JTextPane textPane;
	private static final String url = "172.20.10.2";
	private static final int PORT = 8000;
	private JTable table;
	private JTextField tf_timkiem;
	private JTable table_1;
	private JTable table_2;
	private Client client;
	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public void loadDataToTable() {
		 executorService.submit(() -> {
	        try {
	        	DefaultTableModel model = (DefaultTableModel) table.getModel();
	            client.sendtoServer("/loaddatatotable", "");
	            String response = client.readMessage();
	            System.out.println("Received data from server: ");  // In dữ liệu sau khi nhận

	            Vector<Vector<String>> data = deserializeVector(response);
	            model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

	            for (Vector<String> row : data) {
	                model.addRow(row.toArray(new String[0]));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public void loadDataToTable2() {
		executorService.submit(() -> {
	        try {
	        	DefaultTableModel model = (DefaultTableModel) table_2.getModel();
	            client.sendtoServer("/loaddatatotable2", "");
	            String response = client.readMessage();
	            while(response.startsWith("[CHAT]")){
	                response = client.readMessage();
	            }
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

	public void loadDataToTable1() {
		
		executorService.submit(() -> {
	        try {
	        	DefaultTableModel model = (DefaultTableModel) table_1.getModel();
	            client.sendtoServer("/loadDataToTable1", "");
	            String response = client.readMessage();
	            System.out.println("Received data from server: ");  // In dữ liệu sau khi nhận

	            Vector<Vector<String>> data = deserializeVector(response);
	            model.setRowCount(0);

	            for (Vector<String> row : data) {
	                model.addRow(row.toArray(new String[0]));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	public void load_data() {
		executorService.submit(() -> {
			try {
				client.sendtoServer("/loadDataStaff", tf_staffname.getText());
				String response = client.readMessage();
				while(response.startsWith("[CHAT]")){
                    response = client.readMessage();
                }
		        String[] data = response.split(";;;");
		        if(data.length >= 8) {
		        	tf_manhanvien.setText(data[0]);
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
					String position = data[7];
					tf_vitri.setSelectedItem(position != null ? position : "Giáo viên");
		        }
			} catch (IOException e) {
		        e.printStackTrace();
		    }
		});
	}

	public Staff_Home() {
		Container con = getContentPane();
		getContentPane().setLayout(null);

		URL url_hhd = Staff_Home.class.getResource("logo.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_hhd);
		this.setIconImage(img);

		JPanel pn_home = new JPanel();
		pn_home.setBackground(new Color(192, 192, 192));
		pn_home.setBounds(0, 0, 1280, 750);
		getContentPane().add(pn_home);
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

		JLabel lb_lichday = new JLabel("LỊCH BÁO GIẢNG");
		lb_lichday.setHorizontalAlignment(SwingConstants.CENTER);
		lb_lichday.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_lichday.setBounds(0, 171, 244, 42);
		menu.add(lb_lichday);

		JLabel lb_dshs = new JLabel("DANH SÁCH HỌC SINH");
		lb_dshs.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dshs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_dshs.setBounds(0, 214, 244, 42);
		menu.add(lb_dshs);

		JLabel lb_hocphi = new JLabel("HỌC PHÍ");
		lb_hocphi.setHorizontalAlignment(SwingConstants.CENTER);
		lb_hocphi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_hocphi.setBounds(0, 257, 244, 42);
		menu.add(lb_hocphi);

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
		lb_dangxuat.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dangxuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lb_dangxuat.setBounds(0, 430, 244, 42);
		menu.add(lb_dangxuat);

		JLabel lb_close = new JLabel("");
		lb_close.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/cancel.jpg")));
		;
		lb_close.setBounds(209, 0, 24, 30);
		menu.add(lb_close);

		JLabel lb_menu = new JLabel("");
		lb_menu.setBounds(21, 21, 34, 24);
		int width = 244;
		int height = 750;

		lb_menu.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/menu.jpg")));
		pn_home.add(lb_menu);

		JPanel pn_admin = new JPanel();
		pn_admin.setBackground(new Color(0, 128, 128));
		pn_admin.setBounds(0, 67, 236, 653);
		pn_home.add(pn_admin);
		pn_admin.setLayout(null);

		Form_Login dk = new Form_Login();
		String staffname = Form_Login.staffName;

		tf_staffname = new JTextField();
		tf_staffname.setText(staffname);
		tf_staffname.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_staffname.setColumns(10);
		tf_staffname.setBackground(new Color(102, 205, 170));
		tf_staffname.setBounds(91, 40, 120, 25);
		pn_admin.add(tf_staffname);
		
		client = Form_Login.runClient(tf_staffname.getText());

		JLabel lb_avatar = new JLabel("");
		lb_avatar.setBounds(10, 11, 85, 81);
		pn_admin.add(lb_avatar);
		lb_avatar.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/avatar.png")));

		JPanel container = new JPanel();
		container.setBounds(0, 67, 1280, 683);
		pn_home.add(container);
		container.setLayout(new CardLayout(0, 0));

		JPanel home = new JPanel();
		home.setBackground(SystemColor.window);
		container.add(home, "home");
		home.setLayout(null);

		JPanel pn_qltt = new JPanel();
		pn_qltt.setBounds(244, 56, 1012, 151);
		pn_qltt.setLayout(null);
		pn_qltt.setBackground(Color.WHITE);
		pn_qltt.setOpaque(false);
		home.add(pn_qltt);

		JPanel pn_coso = new JPanel();
		pn_coso.setLayout(null);
		pn_coso.setBackground(new Color(255, 128, 192));
		pn_coso.setBounds(10, 11, 200, 115);
		pn_qltt.add(pn_coso);
		try {
			client.sendtoServer("/countOffice" , "");
			String response = client.readMessage();
			System.out.println("Office = " + response);
			
			JLabel lb_coso_count = new JLabel(response);
			lb_coso_count.setForeground(new Color(255, 255, 255));
			lb_coso_count.setFont(new Font("Tahoma", Font.BOLD, 40));
			lb_coso_count.setBounds(10, 22, 38, 39);
			pn_coso.add(lb_coso_count);
		} catch (Exception e) {
			// TODO: handle exception
		}

		JLabel lb_coso = new JLabel("Cơ sở");
		lb_coso.setForeground(Color.WHITE);
		lb_coso.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_coso.setBounds(10, 65, 123, 39);
		pn_coso.add(lb_coso);

		JLabel lb_anh_coso = new JLabel("");
		lb_anh_coso.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/office.png")));
		lb_anh_coso.setBounds(90, 10, 107, 95);
		pn_coso.add(lb_anh_coso);

		JPanel pn_hocvien = new JPanel();
		pn_hocvien.setLayout(null);
		pn_hocvien.setBackground(new Color(0, 255, 64));
		pn_hocvien.setBounds(269, 11, 200, 115);
		pn_qltt.add(pn_hocvien);

		JLabel lb_hocvien = new JLabel("Học viên");
		lb_hocvien.setForeground(Color.WHITE);
		lb_hocvien.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_hocvien.setBounds(10, 65, 123, 39);
		pn_hocvien.add(lb_hocvien);
		

		JLabel lb_anh_hocvien = new JLabel("");
		lb_anh_hocvien.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/person_add.png")));
		lb_anh_hocvien.setBounds(100, 10, 107, 95);
		pn_hocvien.add(lb_anh_hocvien);
		
		try {
			client.sendtoServer("/countStudent" , "");
			String response = client.readMessage();
			System.out.println("Student = " + response);
	
			JLabel lb_hocvien_count = new JLabel(response);
			lb_hocvien_count.setForeground(new Color(255, 255, 255));
			lb_hocvien_count.setFont(new Font("Tahoma", Font.BOLD, 40));
			lb_hocvien_count.setBounds(10, 22, 70, 39);
			pn_hocvien.add(lb_hocvien_count);
		} catch (Exception e) {}

		JPanel pn_giaovien = new JPanel();
		pn_giaovien.setLayout(null);
		pn_giaovien.setBackground(new Color(255, 128, 0));
		pn_giaovien.setBounds(526, 11, 200, 115);
		pn_qltt.add(pn_giaovien);

		JLabel lb_giaovien = new JLabel("Giáo viên");
		lb_giaovien.setForeground(Color.WHITE);
		lb_giaovien.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_giaovien.setBounds(10, 65, 123, 39);
		pn_giaovien.add(lb_giaovien);
		
		executorService.submit(() -> {
			try {
				client.sendtoServer("/countStaff" , "");
				String response = client.readMessage();
				String countStaff = response;
				JLabel lb_giaovien_count = new JLabel(response);
				lb_giaovien_count.setForeground(Color.WHITE);
				lb_giaovien_count.setFont(new Font("Tahoma", Font.BOLD, 40));
				lb_giaovien_count.setBounds(10, 22, 38, 39);
				pn_giaovien.add(lb_giaovien_count);
			} catch (Exception e) {}
		});
		
		JLabel lb_anh_giaovien = new JLabel("");
		lb_anh_giaovien.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/staff.png")));
		lb_anh_giaovien.setBounds(110, 10, 107, 95);
		pn_giaovien.add(lb_anh_giaovien);

		JPanel pn_lopdanghoc = new JPanel();
		pn_lopdanghoc.setLayout(null);
		pn_lopdanghoc.setBackground(Color.RED);
		pn_lopdanghoc.setBounds(784, 11, 200, 115);
		pn_qltt.add(pn_lopdanghoc);

		JLabel lb_lopdanghoc = new JLabel("Lớp đang học");
		lb_lopdanghoc.setForeground(Color.WHITE);
		lb_lopdanghoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_lopdanghoc.setBounds(10, 65, 146, 39);
		pn_lopdanghoc.add(lb_lopdanghoc);
		
		executorService.submit(() -> {
			try {
				client.sendtoServer("/countClass" , "");
				String response = client.readMessage();
				
				JLabel lb_lop_count_1 = new JLabel(response);
				lb_lop_count_1.setForeground(Color.WHITE);
				lb_lop_count_1.setFont(new Font("Tahoma", Font.BOLD, 40));
				lb_lop_count_1.setBounds(10, 22, 38, 39);
				pn_lopdanghoc.add(lb_lop_count_1);
			} catch (Exception e) {}
		});
		
		

		JLabel lb_anh_giaovien_1 = new JLabel("");
		lb_anh_giaovien_1.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/class.png")));
		lb_anh_giaovien_1.setBounds(110, 10, 107, 95);
		pn_lopdanghoc.add(lb_anh_giaovien_1);

		JLabel lb_qltt = new JLabel("Quản lý trung tâm");
		lb_qltt.setBounds(244, 11, 244, 31);
		lb_qltt.setFont(new Font("Tahoma", Font.BOLD, 25));
		home.add(lb_qltt);

		JPanel pn_qltt1 = new JPanel();
		pn_qltt1.setBounds(0, 0, 1280, 650);
		home.add(pn_qltt1);
		pn_qltt1.setLayout(null);

		JPanel pn_admin1 = new JPanel();
		pn_admin1.setBackground(new Color(0, 128, 128));
		pn_admin1.setBounds(0, 0, 236, 655);
		pn_qltt1.add(pn_admin1);
		pn_admin1.setLayout(null);

		JLabel lblNewLabel_111 = new JLabel("");
		lblNewLabel_111.setBounds(10, 11, 85, 81);
		pn_admin1.add(lblNewLabel_111);
		lblNewLabel_111.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/avatar.png")));

		JPanel pn_anh1 = new JPanel();
		pn_anh1.setBounds(246, 234, 478, 390);
		pn_qltt1.add(pn_anh1);
		pn_anh1.setLayout(null);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(10, 0, 468, 390);
		pn_anh1.add(lblNewLabel_10);
		lblNewLabel_10.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/hoctap1.jpg")));

		JPanel pn_anh2 = new JPanel();
		pn_anh2.setLayout(null);
		pn_anh2.setBounds(759, 234, 478, 390);
		pn_qltt1.add(pn_anh2);

		JLabel lblNewLabel_10_1 = new JLabel("");
		lblNewLabel_10_1.setBounds(0, 0, 490, 390);
		pn_anh2.add(lblNewLabel_10_1);
		lblNewLabel_10_1.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/hoctap2.jpg")));

		JLabel pn_anhnen = new JLabel("");
		pn_anhnen.setBounds(0, 5, 1270, 650);
		pn_qltt1.add(pn_anhnen);
		pn_anhnen.setIcon(new ImageIcon(Staff_Home.class.getResource("/Design/cool.jpg")));

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

		URL iconURL_quaylai = Staff_Home.class.getResource("quaylai.png");
		ImageIcon icon1 = new ImageIcon(iconURL_quaylai);
		bt_quaylai.setIcon(icon1);

		URL iconURL_xoa = Staff_Home.class.getResource("xoa.png");
		ImageIcon icon2 = new ImageIcon(iconURL_xoa);

		URL iconURL_them = Staff_Home.class.getResource("them.png");
		ImageIcon icon3 = new ImageIcon(iconURL_them);
		bt_sua.setIcon(icon3);

		URL iconURL_luu = Staff_Home.class.getResource("luu.png");
		ImageIcon icon4 = new ImageIcon(iconURL_luu);
		bt_in.setIcon(icon4);
		pn_ttcn.setLayout(null);

		JLabel lb_ttcn = new JLabel("Thông tin cá nhân");
		lb_ttcn.setBounds(10, 11, 291, 50);
		lb_ttcn.setBackground(new Color(255, 255, 255));
		lb_ttcn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pn_ttcn.add(lb_ttcn);

		URL iconURL_thongtin = Staff_Home.class.getResource("thongtin.icon.png");
		ImageIcon icon0 = new ImageIcon(iconURL_thongtin);
		lb_ttcn.setIcon(icon0);

		JLabel lb_manhanvien = new JLabel("Mã nhân viên *");
		lb_manhanvien.setBounds(20, 90, 120, 31);
		lb_manhanvien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_manhanvien);

		tf_manhanvien = new JTextField();
		tf_manhanvien.setBounds(155, 92, 210, 30);
		tf_manhanvien.setColumns(10);
		tf_manhanvien.setEditable(false);
		pn_ttcn.add(tf_manhanvien);

		tf_hovaten = new JTextField();
		tf_hovaten.setBounds(155, 147, 210, 30);
		tf_hovaten.setColumns(10);
		pn_ttcn.add(tf_hovaten);

		tf_ngaysinh = new JDateChooser();
		tf_ngaysinh.setBounds(155, 260, 210, 30);
		tf_ngaysinh.setDateFormatString("yyyy-MM-dd");
		pn_ttcn.add(tf_ngaysinh);

		JButton btnGetDate1 = new JButton("Get Selected Date");
		btnGetDate1.addActionListener(e -> {
			// Lấy ngày được chọn và hiển thị trong console
			Date selectedDate1 = tf_ngaysinh.getDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Selected Date: " + dateFormat.format(selectedDate1));
		});

		JLabel lb_hovaten = new JLabel("Họ và tên *");
		lb_hovaten.setBounds(20, 145, 100, 30);
		lb_hovaten.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_hovaten);

		JLabel lb_gt = new JLabel("Giới tính");
		lb_gt.setBounds(20, 200, 104, 30);
		lb_gt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_gt);

		String[] gt = { "Nam", "Nữ" };
		cbb_gioitinh = new JComboBox(gt);
		cbb_gioitinh.setBounds(155, 200, 210, 30);
		cbb_gioitinh.setSelectedIndex(-1);
		pn_ttcn.add(cbb_gioitinh);

		JLabel lb_sdt = new JLabel("Số điện thoại *");
		lb_sdt.setBounds(461, 90, 125, 31);
		lb_sdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_sdt);

		tf_sdt = new JTextField();
		tf_sdt.setBounds(618, 91, 210, 30);
		tf_sdt.setColumns(10);
		pn_ttcn.add(tf_sdt);

		tf_email = new JTextField();
		tf_email.setBounds(618, 146, 210, 30);
		tf_email.setColumns(10);
		pn_ttcn.add(tf_email);

		JLabel lb_ngaysinh = new JLabel("Ngày sinh");
		lb_ngaysinh.setBounds(20, 260, 104, 31);
		lb_ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_ngaysinh);

		JLabel lb_email = new JLabel("Email");
		lb_email.setBounds(461, 144, 104, 30);
		lb_email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_email);

		tf_diachi = new JTextField();
		tf_diachi.setBounds(155, 322, 210, 30);
		tf_diachi.setColumns(10);
		pn_ttcn.add(tf_diachi);

		tf_vitri = new JComboBox(
				new String[] { "Giám đốc", "Giáo viên", "Quản lý", "Trợ lý", "Tư vấn viên", "IT Support", "Kế toán" });
		tf_vitri.setBounds(618, 201, 210, 30);
		pn_ttcn.add(tf_vitri);

		JLabel lb_diachi = new JLabel("Địa chỉ");
		lb_diachi.setBounds(20, 320, 81, 32);
		lb_diachi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_diachi);

		JLabel lb_vitri = new JLabel("Vị trí");
		lb_vitri.setBounds(461, 199, 125, 30);
		lb_vitri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pn_ttcn.add(lb_vitri);

		JPanel pn_ttcn1 = new JPanel();
		pn_ttcn1.setBounds(1, 0, 298, 67);
		pn_ttcn1.setBackground(new Color(192, 192, 192));
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
        
        JScrollPane scrollPane_2 = new JScrollPane(textPane);
        scrollPane_2.setBounds(271, 61, 900, 447);
        message.add(scrollPane_2);

		JPanel dshs = new JPanel();
		container.add(dshs, "dshs");
		dshs.setLayout(null);

		JPanel dshs_1 = new JPanel();
		dshs_1.setBounds(236, 0, 1034, 646);
		dshs.add(dshs_1);
		dshs_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 1014, 530);
		dshs_1.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên");
		model.addColumn("Giới Tính");
		model.addColumn("Ngày Sinh");
		model.addColumn("Địa Chỉ");
		model.addColumn("SĐT");
		model.addColumn("Email");
		model.addColumn("Tên phụ huynh");
		model.addColumn("SĐT phụ huynh");
		model.addColumn("Ngày vào trung tâm");
		model.addColumn("ID lớp");
		model.addColumn("Học phí");

		table.setModel(model);

		loadDataToTable();

		JLabel lblNewLabel = new JLabel("Lớp A");
		lblNewLabel.setBackground(new Color(128, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(26, 11, 70, 23);
		dshs_1.add(lblNewLabel);

		JLabel lblLpB = new JLabel("Lớp B");
		lblLpB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLpB.setBackground(new Color(128, 255, 255));
		lblLpB.setBounds(106, 11, 70, 23);
		dshs_1.add(lblLpB);

		JLabel lblLpC = new JLabel("Lớp C");
		lblLpC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLpC.setBackground(new Color(128, 255, 255));
		lblLpC.setBounds(196, 11, 70, 23);
		dshs_1.add(lblLpC);

		JLabel lblLpD = new JLabel("Lớp D");
		lblLpD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLpD.setBackground(new Color(128, 255, 255));
		lblLpD.setBounds(276, 11, 70, 23);
		dshs_1.add(lblLpD);

		JLabel lblLpE = new JLabel("Lớp E");
		lblLpE.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLpE.setBackground(new Color(128, 255, 255));
		lblLpE.setBounds(356, 11, 70, 23);
		dshs_1.add(lblLpE);

		tf_timkiem = new JTextField();
		tf_timkiem.setBounds(10, 49, 835, 32);
		dshs_1.add(tf_timkiem);
		tf_timkiem.setColumns(10);

		JButton bt_timkiem = new JButton("Tìm kiếm");
		bt_timkiem.setBounds(890, 49, 102, 32);
		dshs_1.add(bt_timkiem);

		JPanel hocphi = new JPanel();
		container.add(hocphi, "hocphi");
		hocphi.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(237, 0, 1021, 649);
		hocphi.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 87, 1001, 551);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane_1.setViewportView(table_1);
		DefaultTableModel model_1 = new DefaultTableModel();
		model_1.addColumn("STT");
		model_1.addColumn("Tên người nộp");
		model_1.addColumn("Tên khóa học");
		model_1.addColumn("Số tiền");
		model_1.addColumn("Hình thức thanh toán");
		model_1.addColumn("Ngày thu");

		table_1.setModel(model_1);
		
		JLabel lblNewLabel_3 = new JLabel("Danh sách nộp học phí");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_3.setBounds(27, 22, 346, 46);
		panel_1.add(lblNewLabel_3);
		
		JButton xuatxml = new JButton("Xuất File");
		xuatxml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.sendMessage("/xuatxml");
				try {
					String response = client.readMessage();
					if (response.equals("Tạo file thành công")) {
						JOptionPane.showMessageDialog(null, "Tạo file thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Tạo file không thành công");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		xuatxml.setFont(new Font("Tahoma", Font.BOLD, 15));
		xuatxml.setBounds(860, 42, 123, 35);
		panel_1.add(xuatxml);
		loadDataToTable1();

		JPanel lichday = new JPanel();
		lichday.setBackground(SystemColor.window);
		container.add(lichday, "lichhoc");
		lichday.setLayout(null);

		JLabel titleLabel = new JLabel("LỊCH BÁO GIẢNG", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
		// titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		titleLabel.setBounds(299, -27, 545, 210);
		lichday.add(titleLabel);

		JLabel giohoc1 = new JLabel("Sáng");
		giohoc1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		giohoc1.setBounds(976, 25, 114, 17);
		lichday.add(giohoc1);
		int a = 40;
		for (int i = 1; i < 6; i++) {

			JLabel tiet = new JLabel("Tiết " + i + " : " + (i + 6) + "h30");
			tiet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tiet.setBounds(976, a, 114, 17);
			lichday.add(tiet);

			// Tùy chỉnh JTable

			a = a + 15;
		}

		JLabel giohoc2 = new JLabel("Chiều");
		giohoc2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		giohoc2.setBounds(1115, 25, 114, 17);
		lichday.add(giohoc2);
		int b = 40;
		for (int i = 6; i < 11; i++) {

			JLabel tiet = new JLabel("Tiết " + i + " : " + (i + 7) + "h00");
			tiet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tiet.setBounds(1115, b, 114, 17);
			lichday.add(tiet);
			b = b + 15;
		}
//		JButton update = new JButton("Update timetable");
///		update.setFont(new Font("Tahoma", Font.BOLD, 12));
//		update.setBounds(263, 120, 151, 38);

//		lichday.add(update);
///		update.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				baohocbu frame = new baohocbu();
////				frame.setVisible(true);
//			}
//		});
		String[] columnNames = { "", "THỨ 2", "THỨ 3", "THỨ 4", "THỨ 5", "THỨ 6", "THỨ 7", "CHỦ NHẬT" };
		Object[][] data = { { "Tiết 1", "", "", "", "", "", "" }, { "Tiết 2", "", "", "", "", "", "" },
				{ "Tiết 3", "", "", "", "", "", "" }, { "Tiết 4", "", "", "", "", "", "" },
				{ "Tiết 5", "", "", "", "hi", "", "" }, { "Tiết 6", "", "", "", "", "", "" },
				{ "Tiết 7", "", "", "", "", "", "" }, { "Tiết 8", "", "", "", "", "", "jello" },
				{ "Tiết 9", "", "", "", "", "", "" } };

		// Tạo JTable với DefaultTableModel
		DefaultTableModel bang = new DefaultTableModel();
		bang.addColumn("");
		bang.addColumn("Monday");
		bang.addColumn("Tuesday");
		bang.addColumn("Wednesday");
		bang.addColumn("Thursday");
		bang.addColumn("Friday");
		bang.addColumn("Saturday");
		bang.addColumn("Sunday");

		table_2 = new JTable();
		table_2.setModel(bang);
		loadDataToTable2();
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
		lichday.add(thanhcuon1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(263, 60, 80, 24);
		lichday.add(lblNewLabel_1);

		bt_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentID = tf_manhanvien.getText();
				String fullName = tf_hovaten.getText();
				String gender = cbb_gioitinh.getSelectedItem().toString();
				String dob = sdf.format(tf_ngaysinh.getDate());
				String address = tf_diachi.getText();
				String phoneNumber = tf_sdt.getText();
				String email = tf_email.getText();

				String position = (String) tf_vitri.getSelectedItem();
				String staffname = tf_staffname.getText();
				client.writetofilestaff(studentID, fullName, gender, dob, address, phoneNumber, email, position, staffname);
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
					for (int i = width; i > 0; i--) {
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
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "home");
				menu.setSize(0, 750);
			}
		});

		lb_thongtin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "account");
				menu.setSize(0, 750);
				load_data();
			}
		});

		lb_lichday.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "lichhoc");
				menu.setSize(0, 750);
			}
		});

		lb_dshs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "dshs");
				menu.setSize(0, 750);
			}
		});

		lb_hocphi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout c1 = (CardLayout) (container.getLayout());
				c1.show(container, "hocphi");
				menu.setSize(0, 750);
			}
		});

		bt_timkiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy giá trị từ tfSearch
				String searchText = tf_timkiem.getText();

				// Thực hiện tìm kiếm trong bảng
				timKiemTrongBang(searchText);
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
			            String position = (String) tf_vitri.getSelectedItem();
			            String staffname = tf_staffname.getText();
			            
			            client.capnhapthongtingv("/updateStaff", name , gender, dateOfBirth, address, phoneNumber, email, position, staffname);
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
		setSize(1280, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_Home frame = new Staff_Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	private void timKiemTrongBang(String searchText) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		int nameColumnIndex = 1;
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
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