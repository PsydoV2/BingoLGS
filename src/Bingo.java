import java.util.Random;
import java.util.Scanner;

public class Bingo extends CasinospielBasis{
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
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
        String GLOBAL_STRING = "";

        GLOBAL_STRING += spielfeld.generateBoard();

        return GLOBAL_STRING;
    }

    private void drawing(){
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt();
            for (int j = 0; j < spielfeld.getSpielFeld().length; i++) {

            }
        }
    }

    private void checkForWin(){}

    @Override
    public void neuesSpiel() {
    }
}
