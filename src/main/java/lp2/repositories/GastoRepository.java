package lp2.repositories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.enterprise.context.ApplicationScoped;
import lp2.models.Gasto;

@ApplicationScoped
public class GastoRepository {
    private static final String FILE_PATH = "src/main/resources/data.json";
    private ObjectMapper mapper;
    private List<Gasto> lista;

    public GastoRepository() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        lista = cargarDatos();
    }

    public List<Gasto> cargarDatos() {
        try {
            File data = new File(FILE_PATH);
            if (data.exists()) {
                return mapper.readValue(data, new TypeReference<List<Gasto>>() {
            });
        } else {
            return new ArrayList<>();
        }

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<Gasto> obtenerTodos() {
        return new ArrayList<>(lista);
    }

    public Gasto obtenerGastoPorId(Integer id) {
        return lista.stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
