package br.com.alura.desafiofipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDadosService implements ConverteDadosInterface{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
