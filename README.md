Instrucciones

1) Se deberá descargar el ZIP del repositorio.
2) Descomprimir el ZIP.
3) Abrir en un IDE por separado la carpeta de Servidor y la de Cliente.
4) Ejecutar primero el main del Servidor y luego el del Cliente.
5) Seguir las instrucciones indicadas en la consola (se pedirán datos al cliente para establecer conexión con el servidor).
6) Para finalizar la conexión ingresar X como mensaje en cualquiera de las partes involucradas en la conexión.

----------------------------------------------------------------------------------------------------------------------------------------

Trabajo Final Redes I - Preguntas

1) ¿Qué es un puerto?

- Es una interfaz que usan las computadoras para entenderse y comunicarse entre sí en internet u otra cualquier red, éste es una numeración lógica que se asigna a las conexiones, tanto en el origen como en el destino. Éste proporciona un espacio de almacenamiento momentáneo para la información que se puede transferir.

2) ¿Cómo están formado los endpoints?

- Los endpoints proporcionan la información necesaria para hacer frente a un servicio web. Proporciona una referencia o especificación que se utiliza para definir un grupo de propiedades de direccionamiento de mensajes y da características de extremo a extremo de mensajes, como las referencias de la fuente y el destino de los criterios de valoración, así como la identidad de los mensajes para permitir el uniforme direccionamiento de mensajes independientes. Éste está conformado por el número IP y el número de puerto.

3) ¿Qué es un socket?

- Un socket es un tipo especial de manejador de fichero que utiliza un proceso para pedir servicios de red al sistema operativo, actúa como un punto final en un canal de comunicación bidireccional. Los sockets trabajan junto con un conjunto de peticiones de programación (API). Una dirección de socket contiene protocolo, dirección local y proceso local.

4) ¿A que capa del modelo TCP/IP pertenecen los sockets? ¿Por qué?

- Un socket funciona como interfaz para la comunicación entre un proceso de aplicación y la capa de transporte donde se permite intercambiar datos de la red a los procesos. Por lo que, podemos decir que los sockets se encuentran entre medio de las capas de Transporte y Aplicación, ya que son el punto de comunicación entre ambas.

5) ¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets?

- En primer lugar el servidor crea un socket al cual se le debe asignar una dirección IP y un número de puerto, éstas deben ser conocidas por los clientes ya que lo utilizarán para establecer conexión con el servidor. Una vez que el servidor se encuentra iniciado, se va a quedar a la espera de que un cliente establezca conexión y envíe un mensaje. Un cliente crea un socket asignándole la dirección IP y el número de puerto que tiene el socket que el servidor creó inicialmente para establecer conexión con el mismo y poder enviar un mensaje. Al recibir el mensaje el servidor recibirá los datos del cliente que lo envió y los usará para enviar un mensaja de respuesta, y así sucesivamente hasta que por algún motivo se corte o finalice la conexión.

6) ¿Cuales son las causas comunes por la que la conexión entre cliente/servidor falle?

- Las causas más comunes por las que la conexión entre cliente/servidor puede fallar son:
* El servidor no se ha iniciado.
* El cliente no conoce la dirección de IP y/o el número de puerto.
* El cliente se ha desconectado.
* El servidor está mal configurado.
* El firewall está bloqueando la conexión de alguna de las partes.
* El puerto que se quiere utilizar está siendo ocupado por otro proceso.

7) Diferencias entre sockets UDP y TCP

- La principal diferencia entre ambos es que el UDP necesita que le entregemos paquetes de datos que el usuario debe construir, mientras el TCP admite bloques de datos que serán empaquetados de forma transparente antes de ser transmitidos. Además tanto los paquetes de datos UDP como los segmentos TCP pueden perderse. Si un paquete se pierde el UDP no hace nada. Por el contrario, si un segmento se pierde el TCP lo retransmitirá, y este proceso durará hasta que el segmento ha sido correctamente entregado al host receptor, o se produzca un número máximo de retransmisiones. Por último, podemos decir que en el UDP controlamos qué datos viajan en cada paquete. En el TCP esto no es posible porque el empaquetamiento es automático. De hecho, el TCP espera un tiempo prudencial a tener bastantes datos que transmitir antes de enviar un segmento ya que esto ahorra ancho de banda. Si es importante que los datos tarden el mínimo tiempo posible en llegar al receptor el UDP es la mejor opción. En este sentido se dice que el UDP tiene una menor latencia que el TCP.

8) Diferencia entre sync & async sockets

- Con los sync, la invocación espera el resultado, es decir, se invoca a un método y se queda en ese punto esperando hasta tanto se devuelva un resultado o un error. Por otra parte, con los async, se realiza la invocación, pero se continúa con la ejecución, y por lo general, se define alguna función o método que recibirá la respuesta o el error para tomar alguna acción; además la ejecución no se queda esperando la respuesta, continúa sin bloquear la ejecución actual.
