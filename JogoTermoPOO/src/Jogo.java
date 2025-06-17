import java.util.List;

public abstract class Jogo implements Jogavel {
    protected String palavraSecreta;
    protected int tentativasRestantes;
    protected List<String> palavras;

    public Jogo() {
        tentativasRestantes = 6;
    }

    protected abstract void carregarPalavras();
    protected abstract String escolherPalavraSecreta();
}