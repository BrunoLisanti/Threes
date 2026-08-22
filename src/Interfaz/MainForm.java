package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MainForm {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Component glue = Box.createGlue();
		glue.setBounds(49, 109, 1, 1);
		frame.getContentPane().add(glue);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(33, 40, 70, 70);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("1");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(115, 40, 70, 70);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("1");
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(197, 40, 70, 70);
		frame.getContentPane().add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("2");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(279, 40, 70, 70);
		frame.getContentPane().add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("1");
		panel_3.add(lblNewLabel_3);

		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_4.setBounds(33, 122, 70, 70);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("1");
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_5.setBounds(115, 122, 70, 70);
		frame.getContentPane().add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("1");
		panel_5.add(lblNewLabel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.setBounds(197, 122, 70, 70);
		frame.getContentPane().add(panel_6);
		
		JLabel lblNewLabel_6 = new JLabel("2");
		panel_6.add(lblNewLabel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_7.setBounds(279, 122, 70, 70);
		frame.getContentPane().add(panel_7);
		
		JLabel lblNewLabel_7 = new JLabel("1");
		panel_7.add(lblNewLabel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_8.setBounds(33, 204, 70, 70);
		frame.getContentPane().add(panel_8);
		
		JLabel lblNewLabel_8 = new JLabel("2");
		panel_8.add(lblNewLabel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_9.setBounds(115, 204, 70, 70);
		frame.getContentPane().add(panel_9);
		
		JLabel lblNewLabel_9 = new JLabel("2");
		panel_9.add(lblNewLabel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_10.setBounds(197, 204, 70, 70);
		frame.getContentPane().add(panel_10);
		
		JLabel lblNewLabel_10 = new JLabel("3");
		panel_10.add(lblNewLabel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_11.setBounds(279, 204, 70, 70);
		frame.getContentPane().add(panel_11);
		
		JLabel lblNewLabel_11 = new JLabel("");
		panel_11.add(lblNewLabel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_12.setBounds(33, 286, 70, 70);
		frame.getContentPane().add(panel_12);
		
		JLabel lblNewLabel_12 = new JLabel("");
		panel_12.add(lblNewLabel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_13.setBounds(115, 286, 70, 70);
		frame.getContentPane().add(panel_13);
		
		JLabel lblNewLabel_13 = new JLabel("1");
		panel_13.add(lblNewLabel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_14.setBounds(197, 286, 70, 70);
		frame.getContentPane().add(panel_14);
		
		JLabel lblNewLabel_14 = new JLabel("1");
		panel_14.add(lblNewLabel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_15.setBounds(279, 286, 70, 70);
		frame.getContentPane().add(panel_15);
		
		JLabel lblNewLabel_15 = new JLabel("1");
		panel_15.add(lblNewLabel_15);
	}
}
