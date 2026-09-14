### Alta prioridad
[x] - Hacer que spawneen nuevos bloques en un lugar aleatorio en la fila opuesta al movimiento hecho cada vez que se combine algo

[x] - Hacer una manera de verificar que no se puedan hacer mas movimientos (Juego terminado)

[x] - Contador de puntos
      -> Grid.calculateAndGetPoints(). Cada ficha 3*2^n vale 3^(n+1); el 1 y el 2
         no puntúan. Se muestra al terminar la partida.

### Baja prioridad (objetivos opcionales de la consigna)
[x] - Interfaz que muestre siguiente ficha a aparecer.
[x] - Registro de puntajes + Botón que lleve a los mejores puntajes

### Requisitos de la consigna que no estaban en esta lista
[~] - INFORME. Documento aparte donde se describa la implementación, se justifique
      la arquitectura elegida para separar en capas y se muestren ejemplos de las
      buenas prácticas aplicadas.
      -> Escrito en doc/. Faltan: ejemplos de código (la consigna los pide con esas
         palabras), la sección de decisiones tomadas durante el desarrollo, y
         mencionar los dos objetivos opcionales que sí se hicieron.
[x] - Mostrar el puntaje al usuario cuando el juego termina.
[x] - Tablero inicial de verdad.

### Limpieza antes de entregar
[x] - Encapsulamiento: Grid.getMatrix() devuelve la matriz interna, así que desde
      afuera se puede escribir el tablero. La consigna evalúa encapsulamiento
      explícitamente.
[x] - Hacer MainForm.grid de instancia.
[x] - generateBoxes() y updateBoxes() repiten la traducción matriz.
      -> Las dos delegan ahora en updateCellAppearance().
[ ] - MainForm.refreshScreen() no hace falta ya que el setText() ya repinta.
[x] - Sacar los andamios de debug.
      -> Se fueron los println de las teclas y el Grid.print().
