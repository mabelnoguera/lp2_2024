package lp2.resources;

import lp2.services.SegundoParcialService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/segundo-parcial-constructor")
public class SegundoParcialConstructorResource {

  private final SegundoParcialService segundoParcialService;

  @Inject
  public SegundoParcialConstructorResource(SegundoParcialService segundoParcialService) {
    this.segundoParcialService = segundoParcialService;
  }

  @POST
  public Response procesarLista(List<Object> listadoPalabras) {
    if (listadoPalabras == null || listadoPalabras.isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", "El listado de palabras no puede estar vacía ni ser null.")).build();
    }

    if (!sonTodasPalabras(listadoPalabras)) {
      return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", "La lista debe contener solo palabras.")).build();
    }

    List<String> listadoPalabrasToString = listadoPalabras.stream().map(Object::toString).collect(Collectors.toList());

    try {
      Map<String, Object> resultado = segundoParcialService.procesarListadoPalabras(listadoPalabrasToString);
      return Response.ok(resultado).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ocurrió un error.").build();
    }
  }

  private boolean sonTodasPalabras(List<Object> listaPalabras) {
    return listaPalabras != null && listaPalabras.stream().allMatch(palabra -> palabra instanceof String);
  }
}
