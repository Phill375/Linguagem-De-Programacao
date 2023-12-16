package br.org.fundatec.util;

import java.util.Scanner;

public class TecladoUtil {

    public static String lerString(String mensagem) {
        return  inicializaTeclado(mensagem).next();
    }

    public static Integer lerInteiro(String mensagem) {
        Scanner scanner = inicializaTeclado(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }


    private static Scanner inicializaTeclado(String mensagem) {
        System.out.println(mensagem);
        return new Scanner(System.in);
    }
}
