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
 * Se encarga de guardar y recuperar el registro de mejores puntajes del
 * juego. Los puntajes se persisten en un archivo binario junto al ejecutable
 * para que se mantengan entre partidas.
 */
public class GestorPuntajes {

	private static final String ARCHIVO_PUNTAJES = "puntajes.dat";
	private static final int MAX_PUNTAJES = 10;

	private GestorPuntajes() {
		// Clase de utilidad, no instanciable.
	}

	// Devuelve el ranking actual, ordenado de mayor a menor puntaje.
	@SuppressWarnings("unchecked")
	public static List<Puntaje> cargarPuntajes() {
		File archivo = new File(ARCHIVO_PUNTAJES);

		if (!archivo.exists())
			return new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			return (List<Puntaje>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	// Agrega un nuevo puntaje al ranking, lo ordena y conserva solo los mejores.
	public static void guardarPuntaje(String jugador, int puntos) {
		List<Puntaje> puntajes = cargarPuntajes();
		puntajes.add(new Puntaje(jugador, puntos));
		Collections.sort(puntajes);

		if (puntajes.size() > MAX_PUNTAJES)
			puntajes = new ArrayList<>(puntajes.subList(0, MAX_PUNTAJES));

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PUNTAJES))) {
			out.writeObject(puntajes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Indica si un puntaje entraría al ranking de mejores puntajes.
	public static boolean esPuntajeDestacado(int puntos) {
		List<Puntaje> puntajes = cargarPuntajes();

		if (puntajes.size() < MAX_PUNTAJES)
			return true;

		return puntos > puntajes.get(puntajes.size() - 1).getPuntos();
	}
}
