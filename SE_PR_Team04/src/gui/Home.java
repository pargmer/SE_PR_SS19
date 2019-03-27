package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.ComponentOrientation;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton btnCreateWorkout = new JButton("Create Workout");
		btnCreateWorkout.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCreateWorkout.setBounds(265, 227, 109, 23);
		btnCreateWorkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frame.dispose();
				CreateWorkout createWorkout = new CreateWorkout();
				createWorkout.newScreen();
				
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnCreateWorkout);
		
		JLabel lblFitnessmanager = new JLabel("Fitnessmanager");
		lblFitnessmanager.setBounds(117, 0, 202, 36);
		lblFitnessmanager.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 27));
		lblFitnessmanager.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblFitnessmanager);
	}
}
