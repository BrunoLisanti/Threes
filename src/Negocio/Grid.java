package Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	private int[][] _matrix;
	private static final int SIDE = 4;
	private static final int INITIAL_TILES = 9;

	private final Random random;

	public Grid() {
		this(new int[SIDE][SIDE]);
		placeInitialTiles();
	}

	public Grid(int[][] grid) {
		_matrix = grid;
		random = new Random();
	}

	private void placeInitialTiles() {
		int placed = 0;
		while (placed < INITIAL_TILES) {
			int row = random.nextInt(SIDE);
			int col = random.nextInt(SIDE);

			if (_matrix[row][col] == 0) {
				_matrix[row][col] = randomTile();
				placed++;
			}
		}
	}

	private int randomTile() {
		return random.nextInt(3) + 1;
	}

	public void print() {
		for (int row = 0; row < SIDE; row++) {
			for (int col = 0; col < SIDE; col++) {
				System.out.print(String.valueOf(_matrix[row][col]) + " ");
			}
			System.out.println("");
		}

	}

	public enum MoveDirection {
		Right, Left, Down, Up
	}

	// Resuelve el turno completo. Corre las fichas, fusiona lo que se pueda y
	// agrega la ficha nueva.
	public boolean play(MoveDirection direction) {
		List<Integer> movedLines = move(direction);

		if (movedLines.isEmpty())
			return false;

		spawn(movedLines, direction);
		return true;
	}

	// Corre y fusiona las fichas una casilla en la dirección dada.
	private List<Integer> move(MoveDirection direction) {
		List<Integer> movedLines = new ArrayList<>();

		for (int row = 0; row < SIDE; row++) {
			for (int cell = 0; cell < SIDE - 1; cell++) {

				int x = 0, y = 0, dx = 0, dy = 0;
				switch (direction) {
					case Right -> {
						x = 2 - cell; // x va de 3 a 0, derecha a izquierda
						y = row; // y es constante
						dx = 1;
						dy = 0;

					}
					case Left -> {
						x = 1 + cell; // x va de 0 a 3, izquierda a derecha
						y = row; // y es constante
						dx = -1;
						dy = 0;

					}
					case Down -> {
						x = row; // x constante
						y = 2 - cell; // y va de 2 a 0
						dx = 0;
						dy = 1;

					}
					case Up -> {
						x = row; // x constante
						y = 1 + cell; // y va de 0 a 0
						dx = 0;
						dy = -1;
					}
				}

				int n = _matrix[y][x];
				if (n == 0)
					continue; // Es una celda vacia, nada que mover

				int infront = _matrix[y + dy][x + dx];

				if (infront == 0) // Mover pero no combinar
				{
					_matrix[y][x] = 0;
					_matrix[y + dy][x + dx] = n;
					markLine(movedLines, row);
				} else if ((n == 1 && infront == 2) ||
						(n == 2 && infront == 1) ||
						(n == infront && n > 2 && infront > 2)) // Mover y combinar
				{
					_matrix[y][x] = 0;
					_matrix[y + dy][x + dx] = n + infront;
					markLine(movedLines, row);
				}
			}
		}

		return movedLines;
	}

	private void markLine(List<Integer> movedLines, int line) {
		if (!movedLines.contains(line))
			movedLines.add(line);
	}

	// Agrega una ficha nueva de valor 1, 2 o 3 en el borde opuesto al movimiento.
	private void spawn(List<Integer> movedLines, MoveDirection direction) {
		int randomLine = movedLines.get(random.nextInt(movedLines.size()));
		int randomValue = randomTile();

		switch (direction) {
			case Right -> _matrix[randomLine][0] = randomValue;
			case Left -> _matrix[randomLine][SIDE - 1] = randomValue;
			case Down -> _matrix[0][randomLine] = randomValue;
			case Up -> _matrix[SIDE - 1][randomLine] = randomValue;
		}

	}

	// Copia independiente de la matriz.
	private int[][] copyMatrix() {
		int[][] copy = new int[SIDE][];
		for (int row = 0; row < SIDE; row++)
			copy[row] = _matrix[row].clone();
		return copy;
	}

	// Se revisa si quedan jugadas disponibles copiando el tablero y probando los 4
	// movimientos posibles. Si alguno de ellos genera un movimiento, el juego no
	// finalizó
	public boolean isGameOver() {
		for (MoveDirection direction : MoveDirection.values()) {
			Grid copy = new Grid(copyMatrix());
			if (!copy.move(direction).isEmpty())
				return false;
		}
		return true;
	}
	
	public int getValueByPosition(int x, int y) {
		return _matrix[x][y];
	}
}
