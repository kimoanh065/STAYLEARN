package Design;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import Controller.DBController;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiaoDienDki extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JTextField tfdangnhaptk;
	private JTextField tfdangnhapmk;
	private JTextField tfdangnhaptk1;
	private JTextField tfdangnhapmk1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienDki frame = new GiaoDienDki();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void hocsinh() {
		Connection con = new DBController().getConnection();
		String sql = "Select * from sang.thongtindangki WHERE username = ? AND password = ?";
		PreparedStatement stm;

		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, tfdangnhaptk.getText());
			stm.setString(2, tfdangnhapmk.getText());
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công");

				new User_Home();
			}

		} catch (SQLException e1) {
			// TODO: handle exception
		}
		// Code thực thi trong luồng
		System.out.println("Luồng được thực thi!");
	}

	public void giaovien() {
		Connection con = new DBController().getConnection();
		String sql = "Select * from sang.giaovien WHERE tk = ? AND mk = ?";
		PreparedStatement stm;

		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, tfdangnhaptk1.getText());
			stm.setString(2, tfdangnhapmk1.getText());
			ResultSet rs = stm.executeQuery();
			System.out.println(5);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công");

				new Staff_Home();
			} else
				JOptionPane.showMessageDialog(null, "Tài khoản và mật khẩu không đúng");
		} catch (SQLException e1) {
			// TODO: handle exception
		}
		// Code thực thi trong luồng
		System.out.println("Luồng được thực thi!");
	}

	public GiaoDienDki() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel pnmain = new JPanel();
		pnmain.setBounds(450, 200, 400, 300);
		pnmain.setOpaque(false);
		contentPane.add(pnmain);
		pnmain.setLayout(new CardLayout(0, 0));

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(255, 255, 255, 200));
		pnmain.add(controlPanel, "1");
		controlPanel.setLayout(null);

		JLabel lbuser_login = new JLabel("STUDENT LOGIN");
		lbuser_login.setForeground(new Color(255, 0, 0));
		lbuser_login.setBounds(120, 73, 200, 25);
		lbuser_login.setFont(new Font("Consolas", Font.BOLD, 21));
		controlPanel.add(lbuser_login);

		JLabel lbuser_user = new JLabel("Username");
		lbuser_user.setBounds(69, 108, 96, 25);
		lbuser_user.setFont(new Font("Consolas", Font.BOLD, 15));
		controlPanel.add(lbuser_user);

		tfdangnhaptk = new JTextField();
		tfdangnhaptk.setBounds(175, 110, 170, 30);
		tfdangnhaptk.setFont(new Font("Consolas", Font.BOLD, 15));
		tfdangnhaptk.setColumns(10);
		controlPanel.add(tfdangnhaptk);

		JLabel lbuser_pass = new JLabel("Password");
		lbuser_pass.setBounds(69, 153, 80, 25);
		lbuser_pass.setFont(new Font("Consolas", Font.BOLD, 15));
		controlPanel.add(lbuser_pass);

		tfdangnhapmk = new JTextField();
		tfdangnhapmk.setBounds(175, 149, 170, 30);
		tfdangnhapmk.setFont(new Font("Consolas", Font.BOLD, 15));
		tfdangnhapmk.setColumns(10);
		controlPanel.add(tfdangnhapmk);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(65, 105, 225));
		btnNewButton_1.setBounds(128, 220, 150, 30);
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD, 15));
		controlPanel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						// Mã này sẽ được thực thi trong một luồng riêng

						hocsinh();
					}
				});

				// Khởi động luồng

				thread.start();
			}
		});

		JButton btnNewButton_2 = new JButton("GUESS");
		btnNewButton_2.setForeground(new Color(255, 20, 147));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(69, 30, 120, 21);
		btnNewButton_2.setFont(new Font("Consolas", Font.BOLD, 15));
		btnNewButton_2.setFocusable(false);
		controlPanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("MANAGER");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setForeground(new Color(0, 128, 0));
		btnNewButton_3.setBounds(220, 30, 120, 21);
		btnNewButton_3.setFont(new Font("Consolas", Font.BOLD, 15));
		btnNewButton_3.setFocusable(false);
		controlPanel.add(btnNewButton_3);
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255, 200));
		panel2.setLayout(null);

		JLabel lbmanager = new JLabel("STAFF LOGIN");
		lbmanager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbmanager.setBounds(135, 71, 140, 13);
		panel2.add(lbmanager);

		JLabel lbusername = new JLabel("Username");
		lbusername.setBounds(82, 98, 96, 46);
		panel2.add(lbusername);

		tfdangnhaptk1 = new JTextField();
		tfdangnhaptk1.setColumns(10);
		tfdangnhaptk1.setBounds(188, 112, 128, 19);
		panel2.add(tfdangnhaptk1);

		JLabel lbpassword = new JLabel("Password");
		lbpassword.setBounds(82, 154, 62, 13);
		panel2.add(lbpassword);

		tfdangnhapmk1 = new JTextField();
		tfdangnhapmk1.setColumns(10);
		tfdangnhapmk1.setBounds(188, 151, 128, 19);
		panel2.add(tfdangnhapmk1);

		JButton btlogin = new JButton("Login");
		btlogin.setForeground(new Color(255, 255, 255));
		btlogin.setBackground(new Color(65, 105, 225));
		btlogin.setBounds(128, 220, 150, 30);
		btlogin.setFont(new Font("Consolas", Font.BOLD, 15));
		panel2.add(btlogin);
		btlogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						// Mã này sẽ được thực thi trong một luồng riêng
						giaovien();
					}
				});
				// Khởi động luồng
				thread.start();
			}
		});

		JButton btguess = new JButton("GUESS");
		btguess.setForeground(new Color(255, 20, 147));
		btguess.setBackground(new Color(255, 255, 255));
		btguess.setBounds(69, 30, 120, 21);
		btguess.setFont(new Font("Consolas", Font.BOLD, 15));
		btguess.setFocusable(false);
		panel2.add(btguess);

		JButton btmanager = new JButton("MANAGER");
		btmanager.setBackground(new Color(255, 255, 255));
		btmanager.setForeground(new Color(0, 128, 0));
		btmanager.setBounds(220, 30, 120, 21);
		btmanager.setFont(new Font("Consolas", Font.BOLD, 15));
		btmanager.setFocusable(false);
		panel2.add(btmanager);
		pnmain.add(panel2, "3");

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setIcon(new ImageIcon(GiaoDienDki.class.getResource("/Design/login.jpg")));
		lblNewLabel_4.setBounds(0, 0, 1280, 750);
		contentPane.add(lblNewLabel_4);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c2 = (CardLayout) (pnmain.getLayout());
				c2.show(pnmain, "3");
			}
		});
		btguess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c3 = (CardLayout) (pnmain.getLayout());
				c3.show(pnmain, "1");
			}
		});

	}
}
