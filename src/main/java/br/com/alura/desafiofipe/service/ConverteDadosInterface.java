package br.com.alura.desafiofipe.service;

public interface ConverteDadosInterface {
    <T> T obterDados(String json, Class<T> classe);
}
