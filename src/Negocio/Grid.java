package Negocio;

public class Grid 
{
	private int[][] _matrix;
	private static final int SIDE = 4;
	
	public Grid()
	{
		_matrix = new int[][] {
			{ 2, 1, 2, 1 },
			{ 0, 1, 3, 2 },
			{ 1, 3, 3, 2 },
			{ 1, 3, 3, 1 }
		};
	}
	
	public Grid(int[][] grid) {
		_matrix = grid;
	}

	public void print() {
		for (int row = 0; row < SIDE; row ++)
		{
			for (int col = 0; col < SIDE; col++)
			{
				System.out.print(String.valueOf(_matrix[row][col]) + " ");
			}
			System.out.println("");
		}

	}
	
	public enum MoveDirection {
		Right, Left, Down, Up
	}
	
	public boolean move(MoveDirection direction) 
	{
		boolean moved = false;
		for (int row = 0; row < SIDE; row++)
		{
			for (int cell = 0; cell < SIDE - 1; cell++)
			{
				
				int x = 0, y = 0, dx = 0, dy = 0;
				switch(direction)
				{
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
				if (n == 0) continue; //Es una celda vacia, nada que mover
				
				int infront = _matrix[y + dy][x + dx];
				
				if (infront == 0) // Mover pero no combinar
				{
					_matrix[y][x] = 0;
					_matrix[y + dy][x + dx] = n;
					moved = true;
				}
				else if ((n == 1 && infront == 2) ||
					     (n == 2 && infront == 1) ||
					     (n == infront && n > 2 && infront > 2)) //Mover y combinar
				{
					_matrix[y][x] = 0;
					_matrix[y + dy][x + dx] = n + infront;
					moved = true;
				}
			}
		}
		
		return moved;
	}
	
	public int[][] getMatrix() {
		return _matrix;
	}
}
