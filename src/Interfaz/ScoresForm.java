package Interfaz;

import Negocio.ScoreManager;
import Negocio.Scoring;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Diálogo que muestra el ranking de mejores Scorings registrados.
 */
public class ScoresForm extends JDialog {

	private static final long serialVersionUID = 1L;

	public ScoresForm(JFrame parent) {
		super(parent, "Mejores Scorings", true);
		setSize(360, 420);
		setLocationRelativeTo(parent);
		setResizable(false);
		setLayout(new BorderLayout());

		JLabel titleLbl = new JLabel("Mejores Scorings", SwingConstants.CENTER);
		titleLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLbl.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		add(titleLbl, BorderLayout.NORTH);

		List<Scoring> Scorings = ScoreManager.loadScores();

		if (Scorings.isEmpty()) {
			JLabel emptyLbl = new JLabel("Todavía no hay Scorings registrados.", SwingConstants.CENTER);
			emptyLbl.setFont(new Font("SansSerif", Font.ITALIC, 14));
			add(emptyLbl, BorderLayout.CENTER);
		} else {
			add(new JScrollPane(createTable(Scorings)), BorderLayout.CENTER);
		}

		JButton closeBtn = new JButton("Cerrar");
		closeBtn.setFocusable(false);
		closeBtn.addActionListener(e -> dispose());

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
		bottomPanel.add(closeBtn);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	private JTable createTable(List<Scoring> Scorings) {
		String[] columns = { "#", "Jugador", "Puntos" };
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		int pos = 1;
		for (Scoring s : Scorings) {
			model.addRow(new Object[] { pos++, s.getplayer(), s.getpoints() });
		}

		JTable tabla = new JTable(model);
		tabla.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tabla.setRowHeight(28);
		tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setMaxWidth(40);

		return tabla;
	}
}
