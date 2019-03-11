package gui;

import app.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JFrame2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame2 frame = new JFrame2();
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
	public JFrame2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 11, 61, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLogout.setContentAreaFilled(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogout.setBounds(335, 227, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnCreateNewWorkout = new JButton("Create new Workout");
		btnCreateNewWorkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Scanner keyboard = new Scanner(System.in);
				
				System.out.println("Name eingeben:");
				String name = keyboard.nextLine();
				System.out.print("Dauer:");
				String duration = keyboard.nextLine();
				System.out.print("min:");
				int min = keyboard.nextInt();
			}
		});
		btnCreateNewWorkout.setBounds(142, 94, 133, 33);
		contentPane.add(btnCreateNewWorkout);
	}
}
