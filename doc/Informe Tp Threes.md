<div style="height: 90vh; display: flex; flex-direction: column; justify-content: space-between; text-align: center;"> 
	<div> 
		<h1>Universidad Nacional General Sarmiento</h1> 
		<h3>Licenciatura en Sistemas</h3> 
	</div> 
	<div> 
		<h1 style="font-size: 2.2rem;">Threes</h1> 
		<p style="color: #666;">Trabajo Práctico 1</p> 
	</div>
	<div style="text-align: right;"> 
		<p> <strong>Integrantes:</strong> Bruno Lisanti, Mateo Lauzán, Martín Godoy, Santino Tettamanti </p>
		<p><strong>Materia:</strong> Programación III </p> 
		<p><strong>Docentes:</strong> Daniel Bertaccini, Patricia Bagnes, Gonzalo Faccini</p>
		<p><strong>Fecha:</strong> 13 de Septiembre de 2026</p> 
	</div> 
</div> <div style="page-break-after: always;"></div>

### Arquitectura elegida
La arquitectura que elegimos para este proyecto fue la de **forms and controls**, ya que la interacción que se debe hacer con la parte de Negocio era lo suficientemente breve y sencilla como para incluirla directamente en los handlers de eventos que trae Swing por defecto. Para resolver la jugada solo es necesario llamar al método ```Grid.play``` y después consultarle a ```Grid``` el estado para actualizar la pantalla. La información que traían consigo los eventos solo consistía en el código de tecla presionado, por lo que tampoco había complejidad por esa parte.

Este es el método que se ejecuta al presionar una flecha:

```java
private void playTurn(Grid.MoveDirection direction) {
    if (gameOver || !grid.play(direction))
        return;

    updateBoxes();
    nextNumberLbl.setText(String.valueOf(grid.getIncomingNextRandomTile()));

    if (grid.isGameOver()) {
        gameOver = true;
        showGameOver();
    }
}
```

Podemos ver que no hace ninguna cuenta del juego, le pide a ```Grid``` que resuelva la jugada y después le pregunta cómo quedó todo, para actualizar la pantalla con esa información.

Las dos capas están separadas también físicamente, en dos paquetes: ```Negocio```, con las clases que resuelven el juego, e ```Interfaz``` con las ventanas. La separación se puede comprobar mirando los imports. Ninguna clase de ```Negocio``` importa ```javax.swing```, ```java.awt``` ni nada del paquete ```Interfaz```. Es decir que el negocio no sabe que existe Swing, y si mañana hubiera que reemplazar la ventana por otra cosa, ```Grid``` no se enteraría.

Eso también nos ordenó la cohesión y el acoplamiento. Cada clase terminó con una sola responsabilidad: ```Grid``` las reglas del juego, ```Scoring``` un puntaje individual, ```ScoreManager``` guardar y leer el ranking, ```MainForm``` la ventana de juego y ```ScoresForm``` la del ranking. Y las dependencias entre clases van siempre en la misma dirección, de la interfaz hacia el negocio, nunca al revés.

### Clases utilizadas
- ```MainForm``` se encarga de dibujar por pantalla la ventana principal de juego donde se verá la grilla de números manipulable mediante las teclas direccionales (arriba, abajo, izquierda y derecha), dicha grilla se llenará y modificará llamando a métodos de una instancia de la clase ```Grid```.
- ```Grid``` modela la grilla de números que se manipula mediante movimientos en el juego, encargándose esta de realizar las operaciones sobre la matriz interna, como mover hacia una dirección, hacer aparecer nuevas fichas, verificar si todavía quedan movimientos posibles o se terminó el juego definitivamente, y calcular el puntaje final de la partida.
- ```Scoring``` modela un registro individual del ranking de puntajes, guarda el nombre del jugador y los puntos obtenidos en una partida. Implementa ```Comparable``` para poder ordenarse automáticamente de mayor a menor puntaje.
- ```ScoreManager``` es una clase de utilidad responsable de la persistencia del ranking de puntajes, se encarga de guardarlos y leerlos de un archivo local, y de conservar únicamente los mejores puntajes históricos.
- ```ScoresForm``` es la ventana que muestra el ranking de mejores puntajes, delegando toda la lógica de datos en ```ScoreManager``` y limitándose a la presentación.

### Detalles de implementación

- Al empezar la partida la grilla se arma al azar. Tanto las casillas ocupadas como sus valores salen de forma aleatoria. Lo único fijo es la cantidad de casillas ocupadas, definida en la constante privada ```Grid.INITIAL_TILES```, y que esas fichas iniciales valen 1, 2 o 3.
- Al presionar una de las teclas de dirección, la ficha nueva aparece solo si hubo algún movimiento o combinación. Su valor es aleatorio y se ubica en el extremo opuesto a la dirección, sobre alguna de las filas o columnas que efectivamente se movieron.
- Al finalizar la partida, cuando ya no quedan movimientos posibles, se le muestra al jugador el puntaje obtenido, se le pide su nombre mediante un cuadro de diálogo y se registra el resultado en el ranking histórico.
- El ranking conserva únicamente los mejores puntajes registrados, si se agrega uno nuevo y el ranking ya está completo, se descarta el menor de los guardados.
- Los puntajes persisten entre ejecuciones del programa en un archivo local, por lo que el historial no se pierde al cerrar la aplicación.

### Buenas prácticas

- Se encapsuló la matriz interna que conforma la grilla en la clase ```Grid``` y se puede acceder a sus valores mediante el método ```getValueByPosition```, evitando que pueda ser modificada directamente desde afuera. Antes ```Grid``` devolvía la matriz entera, y cualquiera podía escribirle desde afuera sin pasar por las reglas del juego.

```java
public int getValueByPosition(int x, int y) throws IndexOutOfBoundsException {
    if (x < 0 || y < 0 || x >= SIDE || y >= SIDE) {
        throw new IndexOutOfBoundsException("Invalid indexes, X: " + x + " Y: " + y);
    }

    return _matrix[x][y];
}
```
- En el método ```Grid.play``` se recibe por parámetro un dato de tipo enumeración ```MoveDirection```, que hace explícita la dirección a la que se quiere mover la grilla sin necesidad de crear más métodos para distintos movimientos.
- ```ScoreManager``` centraliza toda la lógica de persistencia del ranking en una única clase, evitando que la interfaz gráfica (```ScoresForm``` o ```MainForm```) acceda directamente al sistema de archivos. Esto respeta la separación entre la capa de Negocio y la de Interfaz que se sostiene en el resto del proyecto.
- ```Scoring``` implementa ```Comparable<Scoring>``` para definir su orden natural (de mayor a menor puntaje), evitando duplicar lógica de comparación en cada lugar donde se necesita ordenar el ranking.

### Interfaz

Algunas decisiones que tomamos pensando en el que juega:

- **Las fichas se colorean según su valor.** El 1 y el 2 tienen cada uno su color, y los múltiplos de tres van de rosa a bordó a medida que crecen, para poder distinguirlas de un vistazo sin tener que leer el número.
- **Al abrir el juego se muestra un cartel con los controles**, porque no es evidente que se juega con las flechas del teclado.
- **Hay un botón para reiniciar la partida**, así no hace falta cerrar y volver a abrir el programa para jugar de nuevo.
- **La ventana es de tamaño fijo.** Si se pudiera agrandar, las casillas dejarían de ser cuadradas y la grilla se vería deformada.

### Decisiones tomadas durante el desarrollo

- **Cómo detectamos que el juego terminó.** En vez de escribir de nuevo las reglas para ver si quedaba alguna combinación posible, probamos los cuatro movimientos sobre una copia del tablero. Si ninguno cambia nada, se terminó. De esta forma las reglas del juego quedan escritas en un solo lugar y no hay riesgo de que una copia quede desactualizada respecto de la otra.
- **Cómo calculamos el puntaje.** La consigna dice que el valor de los múltiplos de tres "crece exponencialmente", pero no da la fórmula. En la Wikipedia del juego, dice que el puntaje se calcula por lo rara que es la ficha y no por el número que tiene. Siguiendo eso, cada ficha vale el triple que la anterior, un 3 vale 3 puntos, un 6 vale 9, un 12 vale 27, y así. El 1 y el 2 no suman nada, como aclara la consigna.
- **Con cuántas fichas empieza el tablero.** La consigna no lo define, así que lo elegimos nosotros, 9 de las 16 casillas. Nos pareció un punto medio razonable, porque con muy pocas la partida se hace larga y con muchas arranca casi trabada.
- **Dónde guardamos los puntajes.** Podríamos haberlos dejado en memoria, pero se perdían al cerrar el programa. Los guardamos en un archivo local para que el ranking siga estando la próxima vez que se abra el juego.
- **Cómo capturamos las flechas.** Usamos key bindings en lugar de un ```KeyListener```. Con un ```KeyListener``` las teclas dejan de llegar cuando algún botón toma el foco, y nuestra ventana tiene dos botones. Los key bindings escuchan la tecla en toda la ventana sin importar qué componente esté seleccionado.

### Objetivos opcionales

De los tres objetivos opcionales que propone la consigna, implementamos dos:

- **Mostrar la ficha que aparecerá a continuación.** Arriba del tablero se muestra el valor de la próxima ficha, que se calcula de antemano y se guarda hasta que se usa.
- **Registrar los mejores puntajes y consultar la tabla histórica.** Al terminar la partida se le pide el nombre al jugador y su puntaje entra al ranking. El botón "Mejores Puntajes" abre una ventana con la tabla ordenada de mayor a menor.
