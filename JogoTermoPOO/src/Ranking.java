import java.io.*;
import java.util.*;

public class Ranking {
    private final String arquivoRanking = "resources/ranking.txt";
    private final Map<String, Integer> pontuacoes = new HashMap<>();

    public Ranking() {
        carregarRanking();
    }

    public void carregarRanking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoRanking))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    pontuacoes.put(partes[0], Integer.parseInt(partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o ranking.");
        }
    }

    public void adicionarPontuacao(String nome, int pontos) {
        pontuacoes.put(nome, pontuacoes.getOrDefault(nome, 0) + pontos);
    }

    public void salvarRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoRanking))) {
            for (Map.Entry<String, Integer> entry : pontuacoes.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking.");
        }
    }

    public void mostrarRanking() {
        System.out.println("===== Ranking =====");
        pontuacoes.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}