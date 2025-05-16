import java.util.Random;

public class Bingo extends CasinospielBasis{
    private final Random random = new Random();
    private Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;
    private boolean gameWon = false;
    private int einsatz = 0;
    private int anzDraw = 10;

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
            spieler.addJetons(einsatz * 1);
        }

        return GLOBAL_STRING;
    }

    private void drawing(int randomNumber) {
        for (int i = 0; i < anzDraw; i++) {
            for (int k = 0; k < spielfeld.getSpielFeld().length; k++) {
                for (int j = 0; j < spielfeld.getSpielFeld()[j].length; j++) {
                    if (spielfeld.getSpielFeld()[k][j].getValue() == randomNumber) {
                        spielfeld.getSpielFeld()[k][j].setDisplayValue("X");
                        spielfeld.getSpielFeld()[k][j].setGezogen(true);
                    }
                }
            }
            if (checkForWin()) {
                gameEnd = true;
            }
        }
    }

    private boolean checkForWin() {

        //horizontal

        for (int k = 0; k < spielfeld.getSpielFeld()[0].length; k++) {
            boolean lineCorrect = true;
            for (int j = 0; j < spielfeld.getSpielFeld()[j].length; j++) {
                if (!spielfeld.getSpielFeld()[k][j].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) {
                return true;
            }
        }

        //vertikal

        for (int k = 0; k < spielfeld.getSpielFeld()[0].length; k++) {
            boolean lineCorrect = true;
            for (int j = 0; j < spielfeld.getSpielFeld().length; j++) {
                if (!spielfeld.getSpielFeld()[k][j].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) {
                return true;
            }
        }

        //links oben nach rechts unten

        boolean lineCorrectLeftToptoRightBottom = true;
        for (int j = 0; j < spielfeld.getSpielFeld()[0].length; j++) {
            if (!spielfeld.getSpielFeld()[j][j].isGezogen()) {
                lineCorrectLeftToptoRightBottom = false;
                break;
            }
        }
        if (lineCorrectLeftToptoRightBottom) {
            return true;
        }

        //rechts oben nach links unten

        boolean lineCorrectRightToptoLeftBottom = true;
        for (int j = 0; j < spielfeld.getSpielFeld().length; j++) {
            if (!spielfeld.getSpielFeld()[j][spielfeld.getSpielFeld().length - 1 - j].isGezogen()) {
                lineCorrectRightToptoLeftBottom = false;
                break;
            }
        }
        if (lineCorrectRightToptoLeftBottom) {
            return true;
        }

        return false;
    }

    @Override
    public void neuesSpiel() {
        einsatz = 0;
    }
}
