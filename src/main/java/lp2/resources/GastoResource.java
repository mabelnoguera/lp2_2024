package lp2.resources;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lp2.models.Gasto;
import lp2.services.GastoService;

@Path("/gastos-archivo")
public class GastoResource {
    private final GastoService service;

    public GastoResource(GastoService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gasto> obtenerTodos() {
        return this.service.obtenerTodos();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Gasto ejemploDeRepository(@PathParam("id") Integer id) {
        return this.service.obtenerGastoPorId(id);
    }
}
