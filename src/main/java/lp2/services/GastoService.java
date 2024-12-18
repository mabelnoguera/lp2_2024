package lp2.services;

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

    public List<Gasto> obtenerTodos(){
        return repository.obtenerTodos();
    }

    public Gasto obtenerGastoPorId(Integer id){
        Gasto data = repository.obtenerGastoPorId(id);
        return data;
    }
}
