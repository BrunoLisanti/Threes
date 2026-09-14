package Interfaz;

import Negocio.Grid;
import Negocio.ScoreManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MainForm {
	private static final int SIZE = 4;

	private Grid grid;
	private JFrame frame;
	private JPanel board;
	private JLabel[][] cells = new JLabel[SIZE][SIZE];
	private JLabel nextNumberLbl;
	private JButton restartBtn;
	private JButton scoresBtn;
	private boolean gameOver;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
					window.showExplanationDialog();
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
		frame.setBounds(100, 100, 600, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		JPanel infoRow = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		JPanel buttonsRow = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		JLabel titleLbl = new JLabel("Siguiente número: ");
		titleLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		nextNumberLbl = new JLabel("?");
		nextNumberLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
		nextNumberLbl.setForeground(new Color(255, 0, 0));
		
		restartBtn = new JButton("Reiniciar Partida");
		restartBtn.setFont(new Font("SansSerif", Font.ITALIC, 16));
		restartBtn.setFocusable(false);
		restartBtn.addActionListener(e -> restartGame());

		scoresBtn = new JButton("Mejores Puntajes");
		scoresBtn.setFont(new Font("SansSerif", Font.ITALIC, 16));
		scoresBtn.setFocusable(false);
		scoresBtn.addActionListener(e -> showScores());
		
		infoRow.add(titleLbl);
		infoRow.add(nextNumberLbl);

		buttonsRow.add(restartBtn);
		buttonsRow.add(scoresBtn);

		topPanel.add(infoRow);
		topPanel.add(buttonsRow);
		
		frame.add(topPanel, BorderLayout.NORTH);

		board = new JPanel(new GridLayout(SIZE, SIZE));
		board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		frame.add(board);

		generateBoxes();
		nextNumberLbl.setText(String.valueOf(grid.getIncomingNextRandomTile()));
		
		setupKeyBindings();
	}
	
	private void restartGame() {
		grid = new Grid();
		gameOver = false;
		updateBoxes();
		nextNumberLbl.setText(String.valueOf(grid.getIncomingNextRandomTile()));
	}

	private void refreshScreen() {
		frame.getContentPane().repaint();
	}

	private void generateBoxes() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				JLabel cell = new JLabel();
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
				cell.setFont(new Font("SansSerif", Font.BOLD, 28));
				cell.setOpaque(true);

				int number = grid.getValueByPosition(row, col);
				updateCellAppearance(cell, number);

				cells[row][col] = cell;
				board.add(cell);
			}
		}
	}
	
	private void updateCellAppearance(JLabel cell, int value) {
		if (value == 0) {
			cell.setText("");
			cell.setBackground(Color.WHITE);
			return;
		}
		
		cell.setText(String.valueOf(value));
		cell.setForeground(Color.WHITE);
		if (value == 1) {
			cell.setBackground(new Color(94, 140, 240));
		} else if (value == 2) {
			cell.setBackground(new Color(184, 150, 255));
		}
		else {
			// Calculamos el valor de rosa a bordó
			int n = (int) Math.round(Math.log(value / 3.0) / Math.log(2));
			cell.setBackground(new Color(255, 200 - n * 20, 200 - n * 20));
		}

		
	}

	private void updateBoxes() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				int number = grid.getValueByPosition(row, col);
				updateCellAppearance(cells[row][col], number);
			}
		}
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
		nextNumberLbl.setText(String.valueOf(grid.getIncomingNextRandomTile()));

		if (grid.isGameOver()) {
			gameOver = true;
			showGameOver();
		}
	}

	private void showExplanationDialog() {
		JOptionPane.showMessageDialog(frame,
				"Controles: ↑ → ↓ ←",
				"Threes!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void showGameOver() {
		int points = grid.calculateAndGetPoints();

		String mensaje = "No quedan movimientos. Juego terminado.\nPuntaje: " + String.valueOf(points);
		

		JOptionPane.showMessageDialog(frame, mensaje, "Threes!", JOptionPane.INFORMATION_MESSAGE);

		String jugador = JOptionPane.showInputDialog(frame,
				"Ingresá tu nombre:",
				"Registrar Puntaje",
				JOptionPane.PLAIN_MESSAGE);

		ScoreManager.save(jugador, points);
	}

	private void showScores() {
		ScoresForm scoresForm = new ScoresForm(frame);
		scoresForm.setVisible(true);
	}

	private void bindArrow(String key, String actionName, Runnable onAction) {
		JComponent content = (JComponent) frame.getContentPane();

		content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(key), actionName);

		content.getActionMap().put(actionName, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				onAction.run();
			}
		});
	}

}