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
		<p> <strong>Integrantes:</strong> Bruno Lisanti, Mateo Lauzán </p>
		<p><strong>Materia:</strong> Programación III </p> 
		<p><strong>Docentes:</strong> Daniel Bertacini, Patricia Bagnes, Gonzalo Faccini  </p>
		<p><strong>Fecha:</strong> 13 de Septiembre de 2026</p> 
	</div> 
</div> <div style="page-break-after: always;"></div>
- Integrantes: Bruno David Lisanti, Mateo Lauzán.

### Arquitectura elegida
La arquitectura que elegimos para este proyecto fue la de **forms and controls**, ya que la interacción que se debe hacer con la parte de Negocio era lo suficientemente breve y sencilla como para incluirla directamente en los event handlers que trae swing por defecto, solo es necesario llamar al método ```Grid.play``` y la información que traían consigo los eventos solo consistían en el código de tecla presionado, por lo que tampoco había complejidad por esa parte.

### Clases utilizadas
- ```MainForm``` se encarga de dibujar por pantalla la ventana principal de juego donde se verá la grilla de números manipulable mediante las teclas direccionales (arriba, abajo, izquierda y derecha), dicha grilla se llenará y modificará llamando a métodos de una instancia de la clase ```Grid```.
 - ```Grid``` modela la grilla de números que se manipula mediante movimientos en el juego, encargándose esta de realizar las operaciones sobre la matriz interna, como mover hacia una dirección, hacer aparecer nuevas fichas, y verificar si todavía quedan movimientos posibles o se terminó el juego definitivamente.
### Detalles de implementación

- Al presionar la tecla para mover en una dirección, solo si se pudo hacer algún movimiento o combinación va a aparecer una nueva ficha de valor y posición aleatorias en el extremo opuesto a la dirección.
- Al principio del juego el estado de la grilla es totalmente aleatorio, lo único definido es la cantidad de casillas que tendrán un valor definido en la constante privada ```Grid.INITIAL_TILES```

### Buenas prácticas

- Se encapsuló la matriz interna que conforma la grilla en la clase ```Grid``` y se puede acceder a sus valores mediante el método ```getValueByPosition```, evitando que pueda ser modificada directamente desde afuera.
- En el método ```Grid.play``` se recibe por parámetro un dato de tipo enumeración ```MoveDirection```, que hace explícito la dirección a la que se quiere mover la grilla sin necesidad de crear mas métodos para distintos movimientos. 