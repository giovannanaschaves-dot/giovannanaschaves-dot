package financeiro.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void salvar(String caminhoArquivo, List<T> lista) {
        try {
            File arquivo = new File(caminhoArquivo);
            arquivo.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(arquivo);
            gson.toJson(lista, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static <T> List<T> carregar(String caminhoArquivo, Type tipo) {
        try {
            File arquivo = new File(caminhoArquivo);
            if (!arquivo.exists()) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(arquivo);
            List<T> lista = gson.fromJson(reader, tipo);
            reader.close();
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}