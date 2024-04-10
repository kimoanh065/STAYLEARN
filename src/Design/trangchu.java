package Design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class trangchu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trangchu frame = new trangchu();
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
	public trangchu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBounds(0, 0, 1266, 713);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 240, 706);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 255, 255));
		panel_2.setBounds(238, 0, 1018, 49);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Quản lý trung tâm");
		mntmNewMenuItem.setBackground(new Color(0, 255, 255));
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 18));
		mntmNewMenuItem.setBounds(63, 11, 225, 27);
		panel_2.add(mntmNewMenuItem);
		
		JMenuItem mntmElearning = new JMenuItem("elearning");
		mntmElearning.setBackground(new Color(0, 255, 255));
		mntmElearning.setFont(new Font("Tahoma", Font.BOLD, 18));
		mntmElearning.setBounds(309, 11, 225, 27);
		panel_2.add(mntmElearning);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(857, 11, 40, 30);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(917, 11, 40, 30);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(975, 11, 40, 30);
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(238, 52, 1018, 55);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lý trung tâm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(10, 0, 301, 57);
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(238, 110, 1018, 151);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 255, 255));
		panel_5.setBounds(10, 11, 200, 115);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cơ sở");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 65, 123, 39);
		panel_5.add(lblNewLabel);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(new Color(0, 255, 64));
		panel_5_1.setBounds(269, 11, 200, 115);
		panel_4.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		JLabel lblHcVi = new JLabel("Học viên");
		lblHcVi.setForeground(new Color(255, 255, 255));
		lblHcVi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHcVi.setBounds(10, 65, 123, 39);
		panel_5_1.add(lblHcVi);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBackground(new Color(255, 128, 0));
		panel_5_2.setBounds(526, 11, 200, 115);
		panel_4.add(panel_5_2);
		panel_5_2.setLayout(null);
		
		JLabel lblGioVin = new JLabel("Giáo viên");
		lblGioVin.setForeground(new Color(255, 255, 255));
		lblGioVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGioVin.setBounds(10, 65, 123, 39);
		panel_5_2.add(lblGioVin);
		
		JPanel panel_5_3 = new JPanel();
		panel_5_3.setBackground(new Color(255, 0, 0));
		panel_5_3.setBounds(784, 11, 200, 115);
		panel_4.add(panel_5_3);
		panel_5_3.setLayout(null);
		
		JLabel lblLpangHc = new JLabel("Lớp đang học");
		lblLpangHc.setForeground(new Color(255, 255, 255));
		lblLpangHc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLpangHc.setBounds(10, 65, 146, 39);
		panel_5_3.add(lblLpangHc);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(238, 261, 1018, 445);
		panel.add(panel_6);
		
		URL iconURL_email = thongtincanhan.class.getResource("email.png");
	    ImageIcon icon0 = new ImageIcon(iconURL_email);
	    btnNewButton.setIcon(icon0);
	    
	    URL iconURL_thongbao = thongtincanhan.class.getResource("thongbao.icon.png");
	    ImageIcon icon1 = new ImageIcon(iconURL_thongbao);
	    btnNewButton_1.setIcon(icon1);
	    
	    URL iconURL_sinhnhat = thongtincanhan.class.getResource("cake.png");
	    ImageIcon icon2 = new ImageIcon(iconURL_sinhnhat);
	    btnNewButton_2.setIcon(icon2);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
