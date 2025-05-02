import java.util.Scanner;

public class Bingo extends CasinospielBasis{
    private Scanner scanner = new Scanner(System.in);
    private Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;

    public Bingo(Spieler spieler) {
        super("Bingo", spieler);
    }

    @Override
    public String ersteNachricht() {
        return "Bingo";
    }

    @Override
    public String verarbeiteEingabe(String eingabe) {
        return "";
    }

    private void drawing(){}

    private void checkForWin(){}

    @Override
    public void neuesSpiel() {
    }
}
