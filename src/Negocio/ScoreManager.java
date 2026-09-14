package Negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guarda y recupera el ranking de mejores puntajes. Se persisten en un archivo
 * binario junto al ejecutable para que se mantengan entre partidas.
 */
public class ScoreManager {

	private static final String SCORE_FILE = "puntajes.dat";
	private static final int MAX_SCORE = 10;

	private ScoreManager() {
		// Clase de utilidad, no instanciable.
	}

	// Devuelve el ranking actual, ordenado de mayor a menor.
	@SuppressWarnings("unchecked")
	public static List<Scoring> loadScores() {
		File archivo = new File(SCORE_FILE);

		if (!archivo.exists())
			return new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			return (List<Scoring>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	// Agrega un puntaje al ranking, lo ordena y conserva solo los mejores.
	public static void save(String player, int points) {
		List<Scoring> scores = loadScores();
		scores.add(new Scoring(player, points));
		Collections.sort(scores);

		if (scores.size() > MAX_SCORE)
			scores = new ArrayList<>(scores.subList(0, MAX_SCORE));

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
			out.writeObject(scores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
