Conceptos Cliente-Servidor (Parte 2)
Autenticación y autorización en un sistema cliente-servidor basado en REST.
La autenticación y autorización son procesos esenciales para asegurar que solo los usuarios válidos puedan acceder a los recursos del servidor.

Autenticación: Verifica la identidad del usuario. Comúnmente se utilizan métodos como:
Basic Auth: El cliente envía un nombre de usuario y contraseña en la cabecera de la solicitud HTTP.
Token-based Auth: El usuario inicia sesión y el servidor devuelve un token (por ejemplo, JWT - JSON Web Token) que se debe incluir en las cabeceras de las solicitudes posteriores.
Autorización: Determina si el usuario autenticado tiene permiso para realizar una acción específica. 
Esto puede implementarse mediante:
Roles y permisos: El servidor valida si el usuario tiene el rol adecuado para acceder al recurso.
Scopes: En el caso de OAuth, los scopes definen el nivel de acceso que el usuario tiene a los recursos.
Papel de los códigos de estado HTTP en la comunicación entre cliente y servidor
Los códigos de estado HTTP son números que indican el resultado de una solicitud. Son fundamentales porque:

Comunican el resultado: Indican si la solicitud fue exitosa, si hubo un error del cliente (4xx) o un error del servidor (5xx).
Proporcionan información contextual: Ofrecen detalles adicionales sobre lo que ocurrió, permitiendo al cliente manejar la respuesta adecuadamente.
Por ejemplo, un código 200 OK indica éxito, mientras que un 404 Not Found informa al cliente que el recurso solicitado no existe.

Importancia de devolver el código adecuado y su impacto en la experiencia del cliente
Devolver el código adecuado es fundamental porque:

Guía el flujo de la aplicación: Ayuda a los clientes a saber cómo proceder después de una solicitud. Por ejemplo, ante un 401 Unauthorized, el cliente puede solicitar credenciales.
Mejora la experiencia del usuario: Un cliente bien informado puede ofrecer mensajes más claros y decisiones más rápidas, como redirigir a una página de inicio de sesión en caso de error de autenticación.
Si se devuelven códigos incorrectos, los clientes pueden experimentar confusión, errores inesperados y un flujo de trabajo interrumpido.

Definición de un recurso y su importancia
Un recurso es una entidad que puede ser identificada y manipulada a través de la API. En REST, los recursos se representan comúnmente como objetos y se accede a ellos a través de URLs.

Importancia:

Claridad: Definir recursos de manera clara facilita a los desarrolladores entender la estructura de la API y cómo interactuar con ella.
Modularidad: Mantener los recursos como entidades distintas permite una mejor gestión de los datos y una separación de responsabilidades, facilitando cambios y mejoras en la lógica del servidor.
Consistencia: Ayuda a mantener un enfoque coherente en cómo se manejan los datos, lo que es vital para el desarrollo y la integración.
Ventajas de la comunicación sin estado ("stateless") en un servicio REST desde la perspectiva del cliente
Simplicidad: Cada solicitud es independiente, lo que simplifica la lógica del cliente. No necesita preocuparse por el estado del servidor.

Escalabilidad: Los clientes pueden realizar múltiples solicitudes sin preocuparse por el mantenimiento del estado. Esto facilita la implementación de sistemas escalables, donde diferentes servidores pueden manejar diferentes solicitudes sin conflictos.

Mejor rendimiento: Al no requerir mantener estado entre solicitudes, se reduce la sobrecarga del servidor, lo que puede llevar a tiempos de respuesta más rápidos.

Facilidad de caché: Las respuestas pueden ser cacheadas de manera más eficiente, mejorando el rendimiento general del sistema.

Importancia de la idempotencia en las operaciones de un servicio REST
La idempotencia es la propiedad que asegura que realizar la misma operación múltiples veces tendrá el mismo efecto que hacerlo una sola vez. En el contexto de un servicio REST:

Métodos idempotentes: Los métodos como GET, PUT y DELETE son idempotentes. Esto significa que si un cliente envía la misma solicitud varias veces, el resultado será el mismo, sin efectos secundarios adicionales.

Seguridad y confiabilidad: La idempotencia permite que los clientes repitan solicitudes de manera segura sin temor a alterar el estado del servidor de forma no deseada, lo que es especialmente útil en redes inestables.

Facilidad en la recuperación de errores: Si una solicitud falla y se repite, el cliente puede hacerlo sin preocuparse de que causará cambios inesperados en el estado del servidor.