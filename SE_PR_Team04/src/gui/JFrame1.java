package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.DropMode;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class JFrame1 extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField textPW;
	private JLabel lblPassword;
	private final JLabel lblNewLabel_1 = new JLabel("backGroundLabel");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame1 frame = new JFrame1();
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
	public JFrame1() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Muscletastic");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel.setFont(new Font("Eras Bold ITC", Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 175, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(25, 38, 63, 27);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBorder(UIManager.getBorder("TextField.border"));
		username.setToolTipText("");
		username.setBounds(25, 61, 130, 20);
		contentPane.add(username);
		username.setColumns(12);
		
		textPW = new JPasswordField();
		textPW.setToolTipText("");
		textPW.setBounds(25, 108, 130, 20);
		contentPane.add(textPW);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(25, 89, 80, 20);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = textPW.getText();
				String uname = username.getText();
				
				if(pw.equals("123") && uname.equals("muscle")) {
					textPW.setText(null);
					username.setText(null);
					JFrame2 jf2 = new JFrame2();
					jf2.main(null);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login failed", JOptionPane.ERROR_MESSAGE);
					textPW.setText(null);
					username.setText(null);
				}
			}
		});
		btnNewButton.setBounds(25, 159, 86, 20);
		contentPane.add(btnNewButton);
		ImageIcon img = new ImageIcon("D:\\JEE Eclipse\\SE_PR_Team04\\img\\workout_img.jpg");
		lblNewLabel_1.setIcon(img);
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);
		
	}
}
