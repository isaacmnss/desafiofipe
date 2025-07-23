package br.com.alura.desafiofipe.view;

import java.util.Scanner;

public class Menu {

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final Scanner scanner = new Scanner(System.in);

    public void exibeMenu(){

        String menu = """
                *** OPÇÕES ***
                
                Carro
                Moto
                Caminhão
                
                Digite uma das opções acima
                
                """;

        String endereco;

        boolean inputValido = false;

        while (!inputValido){
            System.out.println(menu);
            String inputUser = scanner.nextLine();

            if (inputUser.trim().toLowerCase().contains("carr")){
                endereco = URL_BASE + "/carros/marcas";
                inputValido =true;
            } else if (inputUser.trim().toLowerCase().contains("mot")){
                endereco = URL_BASE + "/motos/marcas";
                inputValido = true;
            } else if (inputUser.trim().toLowerCase().contains("camin")){
                endereco = URL_BASE + "caminhoes/marcas";
                inputValido = true;
            }else {
                System.out.println("Entrada inválida, tente novamente");
            }

        }
    }
}
