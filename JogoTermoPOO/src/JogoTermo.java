import java.io.*;
import java.util.*;

public class JogoTermo extends Jogo {
    private boolean venceu = false;

    public JogoTermo() {
        carregarPalavras();
        palavraSecreta = escolherPalavraSecreta();
    }

    @Override
    protected void carregarPalavras() {
        palavras = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/palavras.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.length() == 5) palavras.add(linha.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar palavras: " + e.getMessage());
        }
    }

    @Override
    protected String escolherPalavraSecreta() {
        Random rand = new Random();
        return palavras.get(rand.nextInt(palavras.size()));
    }

    @Override
    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        while (tentativasRestantes > 0) {
            System.out.println("\nTentativas restantes: " + tentativasRestantes);
            System.out.print("Digite uma palavra de 5 letras: ");
            String tentativa = scanner.nextLine().toUpperCase();

            if (tentativa.length() != 5) {
                System.out.println("A palavra deve ter 5 letras.");
                continue;
            }

            if (tentativa.equals(palavraSecreta)) {
                venceu = true;
                break;
            } else {
                mostrarDicas(tentativa);
                tentativasRestantes--;
            }
        }
    }

    @Override
    public void mostrarResultado() {
        if (venceu) {
            System.out.println("Parabéns! Você acertou a palavra: " + palavraSecreta);
        } else {
            System.out.println("Fim de jogo! A palavra correta era: " + palavraSecreta);
        }
    }

    private void mostrarDicas(String tentativa) {
        for (int i = 0; i < tentativa.length(); i++) {
            char letra = tentativa.charAt(i);
            if (letra == palavraSecreta.charAt(i)) {
                System.out.print("[" + letra + "] ");
            } else if (palavraSecreta.contains(String.valueOf(letra))) {
                System.out.print("(" + letra + ") ");
            } else {
                System.out.print(letra + " ");
            }
        }
        System.out.println();
    }

    public boolean jogadorVenceu() {
        return venceu;
    }
}