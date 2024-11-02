package lp2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SegundoParcialService {

  public Map<String, Object> procesarListadoPalabras(List<String> listadoPalabras) {
    int palindromosEnTotal = contarPalindromos(listadoPalabras);
    List<String> listaPalindromos = obtenerPalindromos(listadoPalabras);

    Map<String, Object> resultado = new HashMap<>();
    resultado.put("Total Palindromos", palindromosEnTotal);
    resultado.put("Listado Palindromos", listaPalindromos);

    return resultado;
  }

  private int contarPalindromos(List<String> palabras) {
    return (int) palabras.stream().filter(this::esPalindromo).count();
  }

  private List<String> obtenerPalindromos(List<String> palabras) {
    return palabras.stream().filter(this::esPalindromo).collect(Collectors.toList());
  }

  private boolean esPalindromo(String str) {
    int left = 0;
    int right = str.length() - 1;

    while (left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }
}
