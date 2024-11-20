package Service;

import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

    <T> T obterDados(String json, TypeReference<T> tipoReferencia);  // Adicionando o método com TypeReference
}
