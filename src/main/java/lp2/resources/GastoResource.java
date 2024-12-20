package lp2.resources;

import java.time.LocalDate;
import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lp2.models.Gasto;
import lp2.services.GastoService;

@Path("/gastos")
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarGasto(Gasto param) {
        this.service.guardarGasto(param);
        return Response.ok().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarGasto(Gasto param) {
        this.service.editarGasto(param);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void eliminarGasto(@PathParam("id") Integer id) {
        this.service.eliminarGasto(id);
    }

    @GET
    @Path("promedio")
    @Produces(MediaType.APPLICATION_JSON)
    public double obtenerPromedioGastoPorDia() {
        return this.service.obtenerPromedioGastoPorDia();
    }

    @GET
    @Path("rango")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gasto> obtenerGastosPorRango(
            @QueryParam("fechaInicio") String fechaInicioParam,
            @QueryParam("fechaFin") String fechaFinParam
    ) {
        LocalDate fechaInicio = LocalDate.parse(fechaInicioParam);
        LocalDate fechaFin = LocalDate.parse(fechaFinParam);

        return this.service.obtenerGastosPorRango(fechaInicio, fechaFin);
    }
}
