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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import Negocio.Grid;

public class MainForm {

	private static final int SIZE = 4;

	static Grid grid;

	private JFrame frame;
	private JPanel board;

	private JLabel[][] cells = new JLabel[SIZE][SIZE];

	private boolean gameOver;

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
		grid = new Grid();
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

	private void refreshScreen() {
		frame.getContentPane().repaint();
	}

	private void generateBoxes() {
		int[][] matrix = grid.getMatrix();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				JLabel cell = new JLabel();
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
				cell.setFont(new Font("SansSerif", Font.BOLD, 28));

				int number = matrix[row][col];
				if (number != 0)
					cell.setText(String.valueOf(number));

				cells[row][col] = cell;
				board.add(cell);
			}
		}
	}

	private void updateBoxes() {
		int[][] matrix = grid.getMatrix();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				int number = matrix[row][col];
				cells[row][col].setText(number == 0 ? "" : String.valueOf(number));
			}
		}
		grid.print();
		refreshScreen();
	}

	private void setupKeyBindings() {
		bindArrow("UP", "ARRIBA", () -> playTurn(Grid.MoveDirection.Up));
		bindArrow("DOWN", "ABAJO", () -> playTurn(Grid.MoveDirection.Down));
		bindArrow("LEFT", "IZQUIERDA", () -> playTurn(Grid.MoveDirection.Left));
		bindArrow("RIGHT", "DERECHA", () -> playTurn(Grid.MoveDirection.Right));
	}

	private void playTurn(Grid.MoveDirection direction) {
		if (gameOver || !grid.play(direction))
			return;

		updateBoxes();

		if (grid.isGameOver()) {
			gameOver = true;
			showGameOver();
		}
	}

	private void showGameOver() {
		// TODO: falta el puntaje
		JOptionPane.showMessageDialog(frame,
				"No quedan movimientos. Juego terminado.",
				"Threes!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void bindArrow(String key, String actionName, Runnable onAction) {
		JComponent content = (JComponent) frame.getContentPane();

		content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(key), actionName);

		content.getActionMap().put(actionName, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				onAction.run();
				System.out.println("presionó " + actionName);
			}
		});
	}

}
