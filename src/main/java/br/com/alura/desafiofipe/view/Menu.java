package br.com.alura.desafiofipe.view;

import br.com.alura.desafiofipe.model.Dados;
import br.com.alura.desafiofipe.model.Modelos;
import br.com.alura.desafiofipe.model.Veiculo;
import br.com.alura.desafiofipe.service.ConsumoApiService;
import br.com.alura.desafiofipe.service.ConverteDadosService;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApiService consumoApiService = new ConsumoApiService();
    private final ConverteDadosService conversor = new ConverteDadosService();

    public void exibeMenu(){


        String menu = """
                *** OPÇÕES ***
                
                Carro
                Moto
                Caminhão
                
                Digite uma das opções acima
                
                """;

        String endereco = null;

        boolean inputValido = false;

        while (!inputValido){
            System.out.println(menu);
            String inputUser = scanner.nextLine();

            if (inputUser.trim().toLowerCase().contains("carr")){
                endereco = URL_BASE + "carros/marcas";
                inputValido =true;
            } else if (inputUser.trim().toLowerCase().contains("mot")){
                endereco = URL_BASE + "motos/marcas";
                inputValido = true;
            } else if (inputUser.trim().toLowerCase().contains("camin")){
                endereco = URL_BASE + "caminhoes/marcas";
                inputValido = true;
            }else {
                System.out.println("Entrada inválida, tente novamente");
            }


            String json = consumoApiService.obterDados(endereco);
//            System.out.println(json);

            List <Dados> marcasList = conversor.obterLista(json, Dados.class);

            marcasList.stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

            System.out.println("Digite o código da marca para consulta");
            String inputCodigo = scanner.nextLine();

            endereco = endereco + "/"+inputCodigo+"/modelos";
            json = consumoApiService.obterDados(endereco);


            var modeloLista = conversor.obterDados(json, Modelos.class);

            System.out.println("\n modelos desta marca: ");
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

            System.out.println("\n Digite um trecho do nome do carro: ");
            String nomeCarro = scanner.nextLine();

            List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeCarro.toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println("\n Modelos filtrados: ");
            modelosFiltrados.forEach(System.out::println);

            System.out.println("\nDigite o código do modelo desejado");
            String codigoVeiculo = scanner.nextLine();

            endereco = endereco + "/" + codigoVeiculo + "/anos";
            json = consumoApiService.obterDados(endereco);
//            System.out.println(json);

            List<Dados> listaModeloDesejado = conversor.obterLista(json, Dados.class);
            List<Veiculo> veiculos = new ArrayList<>();

            for (int i = 0; i < listaModeloDesejado.size(); i++) {
                var enderecoAnos = endereco + "/" +listaModeloDesejado.get(i).codigo();
                json = consumoApiService.obterDados(endereco);
                Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
                veiculos.add(veiculo);
            }

            System.out.println("\nVeículos filtrados: ");
            veiculos.forEach(System.out::println);
        }
    }
}
