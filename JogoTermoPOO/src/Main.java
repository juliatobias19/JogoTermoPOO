import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ranking ranking = new Ranking();

        System.out.println("Bem-vindo ao Jogo do Termo!");
        System.out.println("Regras do jogo:");
        System.out.println("- Você deve descobrir uma palavra secreta de 5 letras.");
        System.out.println("- Você tem 6 tentativas para acertar.");
        System.out.println("- Dicas:");
        System.out.println("   [L] → letra correta e na posição correta");
        System.out.println("   (L) → letra correta, mas na posição errada");
        System.out.println("    L  → letra não existe na palavra secreta\n");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        JogoTermo jogo = new JogoTermo();
        jogo.iniciarJogo();
        jogo.mostrarResultado();

        if (jogo.jogadorVenceu()) {
            ranking.adicionarPontuacao(nome, 10);
        } else {
            ranking.adicionarPontuacao(nome, 2);
        }

        ranking.salvarRanking();
        ranking.mostrarRanking();
    }
}