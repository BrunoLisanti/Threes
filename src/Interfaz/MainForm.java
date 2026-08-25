package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MainForm {

	private static final int SIZE = 4;

	private JFrame frame;
	private JPanel board;

	private JLabel[][] cells = new JLabel[SIZE][SIZE];

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

		board = new JPanel(new GridLayout(SIZE, SIZE));
		frame.add(board);

		generateBoxes();
		setupKeyBindings();
	}

	private void generateBoxes() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				JLabel cell = new JLabel();
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
				cell.setFont(new Font("SansSerif", Font.BOLD, 28));

				cells[row][col] = cell;
				board.add(cell);
			}
		}
	}

	private void setupKeyBindings() {
		bindArrow("UP", "ARRIBA");
		bindArrow("DOWN", "ABAJO");
		bindArrow("LEFT", "IZQUIERDA");
		bindArrow("RIGHT", "DERECHA");
	}

	private void bindArrow(String key, String actionName) {
		JComponent content = (JComponent) frame.getContentPane();

		content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(key), actionName);

		content.getActionMap().put(actionName, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("presionó " + actionName);
			}
		});
	}

}
