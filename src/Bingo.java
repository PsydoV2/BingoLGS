import java.util.Random;

public class Bingo extends CasinospielBasis{
    private final Random random = new Random();
    private Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;
    private boolean gameWon = false;
    private int einsatz = 0;

    public Bingo(Spieler spieler) {
        super("Bingo", spieler);
    }

    @Override
    public String ersteNachricht() {
        return """
                ----- Bingo -----\s
                 \
                Gebe ein wie viele Jetons du setzen möchtest als 'BETRAG' ein.
                """;
    }

    @Override
    public String verarbeiteEingabe(String eingabe) {
        String GLOBAL_STRING = "";
        einsatz = Integer.parseInt(eingabe);
        spieler.removeJetons(einsatz);

        GLOBAL_STRING += spielfeld.generateBoard();

        if (gameWon && gameEnd){
            spieler.addJetons(einsatz * 2);
        }

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
        einsatz = 0;
    }
}
