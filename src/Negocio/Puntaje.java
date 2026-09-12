package Negocio;

import java.io.Serializable;


// puntaje registrado por  jugador al finalizar una partida

public class Puntaje implements Serializable, Comparable<Puntaje> {

	private static final long serialVersionUID = 1L;

	private String jugador;
	private int puntos;

	public Puntaje(String jugador, int puntos) {
		this.jugador = (jugador == null || jugador.isBlank()) ? "Anónimo" : jugador.trim();
		this.puntos = puntos;
	}

	public String getJugador() {
		return jugador;
	}

	public int getPuntos() {
		return puntos;
	}

	// Ordenar puntajes, mayor a menos
	@Override
	public int compareTo(Puntaje otro) {
		return Integer.compare(otro.puntos, this.puntos);
	}
}
