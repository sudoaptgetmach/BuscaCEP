package org.mach.json;

import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mach.templates.CEP;

public class JsonCreator {

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    private static final List<CEP> lista = new ArrayList<>();

    public void createJson(CEP cep) {

        lista.add(cep);

        try (FileWriter writer = new FileWriter("listacep.json")) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.out.println("Um erro aconteceu: ");
            System.out.println(e.getMessage());
        }
    }
}