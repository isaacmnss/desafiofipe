package br.com.alura.desafiofipe.service;

import java.util.List;

public interface ConverteDadosInterface {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
