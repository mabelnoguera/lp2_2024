package lp2.services;

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import lp2.models.Gasto;
import lp2.repositories.GastoRepository;

@ApplicationScoped
public class GastoService {

    private final GastoRepository repository;

    public GastoService(GastoRepository repository) {
        this.repository = repository;
    }

    public List<Gasto> obtenerTodos() {
        return repository.obtenerTodos();
    }

    public Gasto obtenerGastoPorId(Integer id) {
        Gasto data = repository.obtenerGastoPorId(id);
        return data;
    }

    public void guardarGasto(Gasto param) {
        repository.guardarGasto(param);
    }

    public void editarGasto(Gasto param) {
        repository.editarGasto(param);
    }

    public void eliminarGasto(Integer id) {
        repository.eliminarGasto(id);
    }

    public double obtenerPromedioGastoPorDia() {
        return repository.obtenerPromedioGastoPorDia();
    }

    public List<Gasto> obtenerGastosPorRango(LocalDate fechaInicio, LocalDate fechaFin) {
        return repository.obtenerGastosPorRango(fechaInicio, fechaFin);
    }
}
