package Design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.WriteTextFile_User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class thongtincanhan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					thongtincanhan frame = new thongtincanhan();
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
	public thongtincanhan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1266, 713);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 240, 706);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(248, 11, 988, 34);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setForeground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(491, 0, 101, 34);
		panel_2.add(btnNewButton);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setForeground(new Color(0, 255, 0));
		btnThm.setBounds(602, 0, 89, 34);
		panel_2.add(btnThm);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setForeground(new Color(255, 0, 0));
		btnXa.setBounds(701, 0, 89, 34);
		panel_2.add(btnXa);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.setForeground(new Color(0, 0, 255));
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLu.setBounds(800, 0, 89, 34);
		panel_2.add(btnLu);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setForeground(new Color(255, 0, 0));
		btnThot.setBounds(899, 0, 89, 34);
		panel_2.add(btnThot);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(248, 56, 988, 650);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 217, 50);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã học viên *");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 90, 104, 31);
		panel_3.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(155, 92, 210, 30);
		panel_3.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 147, 210, 30);
		panel_3.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 260, 210, 30);
		panel_3.add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ và tên *");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(20, 145, 100, 30);
		panel_3.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Giới tính");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(20, 200, 104, 30);
		panel_3.add(lblNewLabel_1_2);
		
		String [] gt = {"Nam", "Nữ"};
		JComboBox comboBox = new JComboBox(gt);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(155, 200, 210, 30);
		panel_3.add(comboBox);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại *");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(461, 91, 125, 31);
		panel_3.add(lblNewLabel_1_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(618, 92, 210, 30);
		panel_3.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(618, 147, 210, 30);
		panel_3.add(textField_4);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ngày sinh");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(20, 260, 104, 31);
		panel_3.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Email");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_2.setBounds(461, 145, 104, 30);
		panel_3.add(lblNewLabel_1_3_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(155, 322, 210, 30);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(618, 202, 210, 30);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(618, 260, 210, 30);
		panel_3.add(textField_7);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_1.setBounds(20, 320, 81, 32);
		panel_3.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("Tên phụ huynh *");
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_2.setBounds(461, 200, 125, 30);
		panel_3.add(lblNewLabel_1_3_1_2);
		
		JLabel lblNewLabel_1_3_1_3 = new JLabel("SĐT phụ huynh *");
		lblNewLabel_1_3_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_3.setBounds(461, 258, 125, 30);
		panel_3.add(lblNewLabel_1_3_1_3);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(618, 322, 262, 31);
		panel_3.add(textField_9);
		
		JLabel lblNewLabel_1_3_1_2_1_1 = new JLabel("Ngày đến trung tâm");
		lblNewLabel_1_3_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1_2_1_1.setBounds(461, 320, 143, 31);
		panel_3.add(lblNewLabel_1_3_1_2_1_1);
		
		
		//tạo icon
		
		URL iconURL_thongtin = thongtincanhan.class.getResource("thongtin.icon.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_thongtin);
	    lblNewLabel.setIcon(icon0);
	    
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
	    
	    btnLu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String studentID = textField.getText();
	            String fullName = textField_1.getText();
	            String gender = comboBox.getSelectedItem().toString();
	            String dob = textField_2.getText();
	            String phoneNumber = textField_3.getText();
	            String email = textField_4.getText();
	            String address = textField_5.getText();
	            String parentName = textField_6.getText();
	            String parentPhoneNumber = textField_7.getText();
	            String joinDate = textField_9.getText();

	            WriteTextFile_User.writeToFile(studentID, fullName, gender, dob, phoneNumber, email, address, parentName, parentPhoneNumber, joinDate);
	        }
	    });
	}
}
