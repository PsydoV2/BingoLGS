import java.util.Random;

public class Bingo extends CasinospielBasis{
    private final Random random = new Random();
    private Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;
    private boolean gameWon = false;
    private int einsatz = 0;
    private int anzDraw = 95;

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

            int randomNumber = spielfeld.getRandomNumber();

            for (int k = 0; k < spielfeld.getSpielFeld().length; k++) {
                for (int j = 0; j < spielfeld.getSpielFeld()[0].length; j++) {
                    if (spielfeld.getSpielFeld()[k][j].getValue() == randomNumber) {
                        spielfeld.getSpielFeld()[k][j].setDisplayValue("X");
                        spielfeld.getSpielFeld()[k][j].setGezogen(true);
                        break;
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
        Feld[][] feld = spielfeld.getSpielFeld();
        int rows = feld.length;
        int cols = feld[0].length;

        // horizontal prüfen
        for (int row = 0; row < rows; row++) {
            boolean lineCorrect = true;
            for (int col = 0; col < cols; col++) {
                if (!feld[row][col].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) {
                return true;
            };
        }

        // vertikal prüfen
        for (int col = 0; col < cols; col++) {
            boolean lineCorrect = true;
            for (int row = 0; row < rows; row++) {
                if (!feld[row][col].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) {
                return true;
            };
        }

        // Diagonale links oben nach rechts unten
        boolean leftToRight = true;
        for (int i = 0; i < rows; i++) {
            if (!feld[i][i].isGezogen()) {
                leftToRight = false;
                break;
            }
        }
        if (leftToRight) {
            return true;
        }

        // Diagonale rechts oben nach links unten
        boolean rightToLeft = true;
        for (int i = 0; i < rows; i++) {
            if (!feld[i][cols - 1 - i].isGezogen()) {
                rightToLeft = false;
                break;
            }
        }
        return rightToLeft;
    }


    @Override
    public void neuesSpiel() {
        einsatz = 0;
    }
}
