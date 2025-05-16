import java.util.Random;

public class Bingo extends CasinospielBasis{
    private final Random random = new Random();
    private Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;
    private boolean gameWon = false;
    private int einsatz = 0;
    private int anzDraw = 50;

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

        GLOBAL_STRING += "Du hast " + einsatz + " Jetons gesetzt. \n";
        GLOBAL_STRING += "Dein neues Guthaben: " + spieler.getJetons() + "\n";
        GLOBAL_STRING += "Dein Bingofeld wurde erstellt:\n";

        spielfeld.fillList();
        GLOBAL_STRING += spielfeld.generateBoard();

        GLOBAL_STRING += "\nDie Zahlen werden nun gezogen...\n\n";

        spielfeld.fillList();
        GLOBAL_STRING += drawing();

        GLOBAL_STRING += spielfeld.renderDrawnBoard();

        if (gameWon && gameEnd){
            spieler.addJetons(einsatz * 2);
        }

        return GLOBAL_STRING;
    }

    private String drawing() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < anzDraw; i++) {
            int drawnNumber = spielfeld.getRandomNumber();
            sb.append("Gezogene Zahl: ").append(drawnNumber).append("\n");

            Feld[][] feld = spielfeld.getSpielFeld();
            int cols = feld[0].length;

            for (Feld[] felds : feld) {
                for (int c = 0; c < cols; c++) {
                    if (felds[c].getValue() == drawnNumber) {
                        felds[c].setDisplayValue("X");
                        felds[c].setGezogen(true);
                    }
                }
            }

            if (checkForWin()) {
                gameWon = true;
                gameEnd = true;
                sb.append("BINGO! Du hast gewonnen!\n");
                break;
            }
        }

        if (!gameWon){
            sb.append("\nDu hast leider verloren!\n");
        }

        gameEnd = true;
        return sb.toString();
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
