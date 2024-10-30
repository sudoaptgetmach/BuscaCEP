package org.mach;

import org.mach.http.ViaCEPClient;
import org.mach.json.JsonCreator;
import org.mach.templates.CEP;

import java.io.IOException;
import java.util.Scanner;

public class AppManager {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        JsonCreator jsonCreator = new JsonCreator();

        while (!input.equalsIgnoreCase("sair")) {

            System.out.println("Insira um CEP ou digite sair para sair.");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")){ break; }

            if (input.length() != 8) {
                System.out.println("O input precisa conter exatamente 8 digitos.");
                continue;
            }

            try {
                ViaCEPClient client = new ViaCEPClient();
                CEP cep = client.requisicao(input);
                jsonCreator.createJson(cep);
            } catch (IllegalArgumentException | IOException | InterruptedException e) {
                System.out.println("Ocorreu um erro: ");
                System.out.println(e.getMessage());
            }
        }
    }
}