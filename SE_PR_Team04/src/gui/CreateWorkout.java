package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class CreateWorkout extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateWorkout dialog = new CreateWorkout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateWorkout() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnHome = new JButton("Home");
			btnHome.setFont(new Font("Tw Cen MT", Font.ITALIC, 16));
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					new Home().setVisible(true);;
					dispose();
				}
			});
			btnHome.setBounds(134, 162, 164, 47);
			getContentPane().add(btnHome);
		}
		
		JLabel lblWorkoutErstellen = new JLabel("Workout erstellen");
		lblWorkoutErstellen.setBounds(0, 0, 125, 19);
		getContentPane().add(lblWorkoutErstellen);
		
		textField = new JTextField();
		textField.setBounds(0, 30, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
}
