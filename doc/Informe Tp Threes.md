<div style="height: 90vh; display: flex; flex-direction: column; justify-content: space-between; text-align: center;"> 
	<div> 
		<h1>Universidad Nacional General Sarmiento</h1> 
		<h3>Licenciatura en sistemas</h3> 
	</div> 
	<div> 
		<h1 style="font-size: 2.2rem;">Threes</h1> 
		<p style="color: #666;">Trabajo Práctico 1</p> 
	</div>
	<div style="text-align: right;"> 
		<p> <strong>Integrantes:</strong> Bruno Lisanti, Mateo Lauzán, Martín Godoy, Santino Tettamanti </p>
		<p><strong>Materia:</strong> Programación III </p> 
		<p><strong>Docentes:</strong> Daniel Bertacini, Patricia Bagnes, Gonzalo Faccini  </p>
		<p><strong>Fecha:</strong> 13 de Septiembre de 2026</p> 
	</div> 
</div> <div style="page-break-after: always;"></div>
- Integrantes: Bruno David Lisanti, Mateo Lauzán, Martín Godoy, Santino Tettamanti .

### Arquitectura elegida
La arquitectura que elegimos para este proyecto fue la de **forms and controls**, ya que la interacción que se debe hacer con la parte de Negocio era lo suficientemente breve y sencilla como para incluirla directamente en los event handlers que trae swing por defecto, solo es necesario llamar al método ```Grid.play``` y la información que traían consigo los eventos solo consistían en el código de tecla presionado, por lo que tampoco había complejidad por esa parte.

### Clases utilizadas
- ```MainForm``` se encarga de dibujar por pantalla la ventana principal de juego donde se verá la grilla de números manipulable mediante las teclas direccionales (arriba, abajo, izquierda y derecha), dicha grilla se llenará y modificará llamando a métodos de una instancia de la clase ```Grid```.
 - ```Grid``` modela la grilla de números que se manipula mediante movimientos en el juego, encargándose esta de realizar las operaciones sobre la matriz interna, como mover hacia una dirección, hacer aparecer nuevas fichas, y verificar si todavía quedan movimientos posibles o se terminó el juego definitivamente.
 - ```Scoring``` modela un registro individual del ranking de puntajes: guarda el nombre del jugador y los puntos obtenidos en una partida. Implementa ```Comparable``` para poder ordenarse automáticamente de mayor a menor puntaje.
 - ```ScoreManager```es una clase de utilidad responsable de la persistencia del ranking de puntajes: se encarga de guardarlos, leerlos y borrarlos de un archivo local, y de determinar si un puntaje nuevo alcanza para entrar al top de mejores puntajes históricos.
 - ```ScoresForm```es la ventana que muestra el ranking de mejores puntajes y permite borrarlo, delegando toda la lógica de datos en ```ScoreManager``` y limitándose a la presentación.
### Detalles de implementación

- Al presionar la tecla para mover en una dirección, solo si se pudo hacer algún movimiento o combinación va a aparecer una nueva ficha de valor y posición aleatorias en el extremo opuesto a la dirección.
- Al principio del juego el estado de la grilla es totalmente aleatorio, lo único definido es la cantidad de casillas que tendrán un valor definido en la constante privada ```Grid.INITIAL_TILES```
- Al finalizar la partida (cuando no quedan movimientos posibles) se le solicita al jugador su nombre mediante un cuadro de diálogo y se registra su puntaje en el ranking histórico.
- El ranking conserva únicamente los mejores puntajes registrados; si se agrega un puntaje nuevo y el ranking ya está completo, se descarta el menor de los guardados.
- Los puntajes persisten entre ejecuciones del programa en un archivo local, por lo que el historial no se pierde al cerrar la aplicación, salvo que el usuario decida borrarlo explícitamente desde la ventana de ranking.

### Buenas prácticas

- Se encapsuló la matriz interna que conforma la grilla en la clase ```Grid``` y se puede acceder a sus valores mediante el método ```getValueByPosition```, evitando que pueda ser modificada directamente desde afuera.
- En el método ```Grid.play``` se recibe por parámetro un dato de tipo enumeración ```MoveDirection```, que hace explícito la dirección a la que se quiere mover la grilla sin necesidad de crear mas métodos para distintos movimientos.
- ```ScoreManager``` centraliza toda la lógica de persistencia del ranking en una única clase, evitando que la interfaz gráfica (```ScoresForm``` o ```MainForm```) acceda directamente al sistema de archivos. Esto respeta la separación entre la capa de Negocio y la de Interfaz que se sostiene en el resto del proyecto.
- ```Scoring``` implementa ```Comparable<Scoring>``` para definir su orden natural (de mayor a menor puntaje), evitando duplicar lógica de comparación en cada lugar donde se necesita ordenar el ranking.
