package Interfaz;

import Negocio.GestorPuntajes;
import Negocio.Puntaje;
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
 * Diálogo que muestra el ranking de mejores puntajes registrados.
 */
public class ScoresForm extends JDialog {

	private static final long serialVersionUID = 1L;

	public ScoresForm(JFrame parent) {
		super(parent, "Mejores Puntajes", true);
		setSize(360, 420);
		setLocationRelativeTo(parent);
		setResizable(false);
		setLayout(new BorderLayout());

		JLabel titleLbl = new JLabel("Mejores Puntajes", SwingConstants.CENTER);
		titleLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		titleLbl.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		add(titleLbl, BorderLayout.NORTH);

		List<Puntaje> puntajes = GestorPuntajes.cargarPuntajes();

		if (puntajes.isEmpty()) {
			JLabel emptyLbl = new JLabel("Todavía no hay puntajes registrados.", SwingConstants.CENTER);
			emptyLbl.setFont(new Font("SansSerif", Font.ITALIC, 14));
			add(emptyLbl, BorderLayout.CENTER);
		} else {
			add(new JScrollPane(crearTabla(puntajes)), BorderLayout.CENTER);
		}

		JButton cerrarBtn = new JButton("Cerrar");
		cerrarBtn.setFocusable(false);
		cerrarBtn.addActionListener(e -> dispose());

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
		bottomPanel.add(cerrarBtn);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	private JTable crearTabla(List<Puntaje> puntajes) {
		String[] columnas = { "#", "Jugador", "Puntos" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		int posicion = 1;
		for (Puntaje p : puntajes) {
			modelo.addRow(new Object[] { posicion++, p.getJugador(), p.getPuntos() });
		}

		JTable tabla = new JTable(modelo);
		tabla.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tabla.setRowHeight(28);
		tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setMaxWidth(40);

		return tabla;
	}
}
