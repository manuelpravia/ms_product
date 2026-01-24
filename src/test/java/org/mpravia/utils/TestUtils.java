package org.mpravia.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUtils {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /**
     * Lee un archivo JSON desde resources y lo convierte al tipo de objeto especificado.
     * @param path Ruta del archivo en src/test/resources (ej: "json/product.json")
     * @param clazz Clase a la que se desea convertir
     */
    public static <T> T readJson(String path, Class<T> clazz) {
        try (InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo en la ruta: " + path);
            }
            return mapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Error al transformar el JSON " + path + " a la clase " + clazz.getSimpleName(), e);
        }
    }

    public static <T> List<T> readJsonList(String path, Class<T> clazz) {
        try (InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(path)) {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(is, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la lista de objetos desde " + path, e);
        }
    }

}
