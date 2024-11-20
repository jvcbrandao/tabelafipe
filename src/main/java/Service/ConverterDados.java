package Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverterDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    // Método para obter um único dado (não lista)
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar o JSON", e);
        }
    }

    // Método para obter uma lista de dados
    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar a lista de dados", e);
        }
    }

    // Método para obter dados de uma lista ou objeto quando usamos TypeReference
    @Override
    public <T> T obterDados(String json, TypeReference<T> tipoReferencia) {
        try {
            return mapper.readValue(json, tipoReferencia);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar o JSON com TypeReference", e);
        }
    }

    // Método adicional para verificar o tipo de resposta da API (objeto ou lista)
    public <T> List<T> obterListaSeForObjetoOuLista(String json, TypeReference<List<T>> tipoReferencia) {
        try {
            // Se o JSON for um objeto único (não uma lista), vamos tentar desserializar como um único item primeiro
            if (json.trim().startsWith("{")) {  // Verifica se começa com um objeto
                // Mudança sugerida para usar JavaType
                JavaType javaType = mapper.getTypeFactory().constructType(tipoReferencia.getType());
                T objeto = mapper.readValue(json, javaType);  // Lê como tipo da referência diretamente
                return List.of(objeto);  // Retorna uma lista contendo o objeto único
            }
            return mapper.readValue(json, tipoReferencia); // Caso seja uma lista, desserializa como tal
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao tentar desserializar o JSON em lista ou objeto", e);
        }
    }
}
