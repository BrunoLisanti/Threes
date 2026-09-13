package Negocio;

import java.io.Serializable;


// Scorings registrado por  player al finalizar una partida

public class Scoring implements Serializable, Comparable<Scoring> {

	private static final long serialVersionUID = 1L;

	private String player;
	private int points;

	public Scoring(String player, int points) {
		this.player = (player == null || player.isBlank()) ? "Anónimo" : player.trim();
		this.points = points;
	}

	public String getplayer() {
		return player;
	}

	public int getpoints() {
		return points;
	}

	// Ordenar Scorings, mayor a menor
	@Override
	public int compareTo(Scoring another) {
		return Integer.compare(another.points, this.points);
	}
}
