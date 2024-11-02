 Creación de un Servicio REST Básico en Quarkus
Pasos para crear un recurso REST en Quarkus y estructura básica de un endpoint
Configurar el proyecto.

Utiliza el Quarkus CLI o el sitio web de Quarkus para generar un nuevo proyecto. Puedes usar el comando:
bash
mvn io.quarkus:quarkus-maven-plugin:2.10.2:create \
  -DprojectGroupId=com.example \
  -DprojectArtifactId=mi-proyecto-rest \
  -DclassName="com.example.rest.HelloResource" \
  -Dpath="/hello"
Definir el recurso REST:

Abre la clase generada (HelloResource.java) y define los endpoints. La estructura básica de un endpoint incluye el uso de las anotaciones @Path, @GET, @POST, etc.
java
Copiar código
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, REST!";
    }
}
Ejecutar la aplicación:

Utiliza el comando ./mvnw compile quarkus:dev para iniciar la aplicación en modo de desarrollo.
Probar el endpoint:

Abre un navegador o usa herramientas como Postman o curl para acceder a http://localhost:8080/hello.
Configuración de una ruta de endpoint utilizando la anotación @Path
La anotación @Path se utiliza para definir la ruta base del recurso. Por ejemplo, en la clase HelloResource, @Path("/hello") significa que cualquier solicitud que coincida con /hello será manejada por este recurso.

Puedes definir rutas más complejas combinando @Path en diferentes niveles, por ejemplo:

java
Copiar código
@Path("/users")
public class UserResource {

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id) {
        // Lógica para obtener un usuario por ID
    }
}
Inyección de dependencias (CDI) en Quarkus
La Inyección de Dependencias (CDI - Contexts and Dependency Injection) permite que los objetos sean gestionados por el contenedor de Quarkus. Esto implica que puedes definir servicios, repositorios, etc., y permitir que Quarkus maneje la creación y la inyección de estos objetos en tus recursos REST.

Importancia:

Desacoplamiento: Facilita la separación de preocupaciones y mejora la mantenibilidad del código.
Gestión de ciclos de vida: El contenedor gestiona el ciclo de vida de los objetos, permitiendo que sean creados, destruidos y reciclados de forma eficiente.
Facilidad de prueba: Facilita la creación de pruebas unitarias e integración al permitir el uso de mocks.
Uso de @Produces y @Consumes
Las anotaciones @Produces y @Consumes se utilizan para definir el tipo de datos que tu API REST puede enviar y recibir.

@Produces: Indica el tipo de respuesta que el endpoint puede devolver. Por ejemplo, @Produces(MediaType.APPLICATION_JSON) indica que el recurso devuelve JSON.

@Consumes: Indica el tipo de contenido que el endpoint acepta en las solicitudes. Por ejemplo, @Consumes(MediaType.APPLICATION_JSON) significa que el recurso espera recibir datos en formato JSON.

java
Copiar código
@POST
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public User createUser(User user) {
    // Lógica para crear un nuevo usuario
}
¿Qué es un controlador en un servicio REST y cuál es su función?
Un controlador en un servicio REST es una clase que maneja las solicitudes HTTP y proporciona respuestas adecuadas. Su función incluye:

Definir Endpoints: Los controladores contienen métodos que están anotados con @GET, @POST, @PUT, @DELETE, etc., para manejar las diferentes operaciones sobre los recursos.

Gestionar la Lógica de Negocio: Pueden incluir lógica para interactuar con servicios, repositorios o bases de datos.

Validar y Procesar Datos: Pueden validar los datos de entrada y procesar las respuestas antes de enviarlas al cliente.

Responder a Solicitudes: Los controladores son responsables de construir y enviar respuestas HTTP adecuadas al cliente.

