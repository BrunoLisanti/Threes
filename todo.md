### Alta prioridad
[x] - Hacer que spawneen nuevos bloques en un lugar aleatorio en la fila opuesta al movimiento hecho cada vez que se combine algo

[x] - Hacer una manera de verificar que no se puedan hacer mas movimientos (Juego terminado)

[ ] - Contador de puntos

### Baja prioridad (objetivos opcionales de la consigna)
[ ] - Interfaz que muestre siguiente ficha a aparecer.
[ ] - Registro de puntajes + Botón que lleve a los mejores puntajes

### Requisitos de la consigna que no estaban en esta lista
[ ] - INFORME. Documento aparte donde se describa la implementación, se justifique
      la arquitectura elegida para separar en capas y se muestren ejemplos de las
      buenas prácticas aplicadas.
[ ] - Mostrar el puntaje al usuario cuando el juego termina.
[x] - Tablero inicial de verdad.

### Limpieza antes de entregar
[ ] - Encapsulamiento: Grid.getMatrix() devuelve la matriz interna, así que desde
      afuera se puede escribir el tablero. La consigna evalúa encapsulamiento
      explícitamente.
[ ] - Hacer MainForm.grid de instancia.
[ ] - generateBoxes() y updateBoxes() repiten la traducción matriz.
[ ] - MainForm.refreshScreen() no hace falta ya que el setText() ya repinta.
[ ] - Sacar los andamios de debug.
