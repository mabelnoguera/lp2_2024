  Implementación del Patrón Repository en Quarkus
¿Qué es el patrón de diseño Repository y cuál es su principal objetivo en aplicaciones REST?
El patrón de diseño Repository es un patrón que proporciona una abstracción para el acceso a datos, ocultando la lógica de acceso a la base de datos detrás de una interfaz. Su principal objetivo es separar la lógica de negocio de la lógica de acceso a datos, permitiendo que la aplicación REST interactúe con los datos de manera más estructurada y mantenible.

Objetivos principales:

Abstracción: Proveer una interfaz que abstrae las operaciones de acceso a datos, permitiendo que el código de la aplicación no dependa de la implementación específica del acceso a datos.
Desacoplamiento: Separar las preocupaciones de acceso a datos de la lógica de negocio, facilitando pruebas y mantenimiento.
Reutilización: Promover la reutilización de la lógica de acceso a datos en diferentes partes de la aplicación.
Cómo separar la lógica de negocio del acceso a datos al implementar el patrón Repository
Para implementar el patrón Repository en Quarkus y separar la lógica de negocio del acceso a datos, se pueden seguir estos pasos:

Definir una interfaz Repository: Crea una interfaz que declare métodos para las operaciones de acceso a datos, como findAll, findById, save, delete, etc.

java
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);
}
Implementar la interfaz: Crea una clase que implemente esta interfaz y maneje la lógica de acceso a datos, utilizando un ORM como Hibernate o JPA.

java
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = findById(id).orElseThrow();
        entityManager.remove(user);
    }
}
Usar el repositorio en los controladores: Inyecta el repositorio en los controladores REST para manejar las operaciones de negocio.

java
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/users")
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @POST
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        userRepository.delete(id);
    }
}
Técnicas para realizar consultas básicas en una clase Repository
findAll: Puedes utilizar JPQL o el API Criteria para obtener todos los registros.

java
return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
findById: Utiliza entityManager.find() para buscar un registro específico por su ID.

java
return Optional.ofNullable(entityManager.find(User.class, id));
Consultas personalizadas: Puedes definir métodos adicionales en el repositorio para realizar consultas más complejas, utilizando @Query si estás utilizando Spring Data JPA.

java
@Query("SELECT u FROM User u WHERE u.email = :email")
Optional<User> findByEmail(@Param("email") String email);
Ventajas de implementar el patrón Repository en aplicaciones REST
Mantenibilidad: La separación entre lógica de negocio y acceso a datos mejora la organización del código y facilita la comprensión y mantenimiento.

Testabilidad: Al tener una interfaz que abstrae el acceso a datos, es más fácil crear pruebas unitarias utilizando mocks o stubs.

Flexibilidad: Permite cambiar la implementación del acceso a datos (por ejemplo, cambiar de JPA a otra tecnología) sin afectar a la lógica de negocio.

Reutilización: La lógica de acceso a datos se puede reutilizar en diferentes partes de la aplicación, evitando duplicación de código.

Organización: Facilita la implementación de patrones de diseño adicionales, como el patrón de servicios, que permite gestionar la lógica de negocio de manera aún más efectiva.