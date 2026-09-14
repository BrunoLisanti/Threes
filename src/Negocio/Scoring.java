package Negocio;

import java.io.Serializable;


// Puntaje registrado por un jugador al finalizar una partida

public class Scoring implements Serializable, Comparable<Scoring> {

	private static final long serialVersionUID = 1L;

	private String player;
	private int points;

	public Scoring(String player, int points) {
		this.player = (player == null || player.isBlank()) ? "Anónimo" : player.trim();
		this.points = points;
	}

	public String getPlayer() {
		return player;
	}

	public int getPoints() {
		return points;
	}

	// Ordena de mayor a menor puntaje
	@Override
	public int compareTo(Scoring another) {
		return Integer.compare(another.points, this.points);
	}
}
