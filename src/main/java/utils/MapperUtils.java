package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {

    public static <T>T map(String text, Class<T> aClass) throws JsonProcessingException {

       return new ObjectMapper()
               .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
               .readValue(text,aClass);
    }
}
