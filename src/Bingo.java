import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bingo extends CasinospielBasis {
    private final Spielfeld spielfeld = new Spielfeld();
    private boolean gameEnd = false;
    private boolean gameWon = false;
    private int einsatz = 0;
    private int anzDraw = 5; // Anzahl der Ziehungen

    public Bingo(Spieler spieler) {
        super("Bingo", spieler);
    }

    @Override
    public String ersteNachricht() {
        // Erste Anweisung an den Spieler
        return """
                ----- Bingo -----\s
                 \
                Gebe ein wie viele Jetons du setzen möchtest und darauf per Komma getrennt die 9 Zahlen der Bingokarte.
                \n Die Zahlen müssen zwischen 1 und 9 sein und dürfen sich nicht doppeln!
                \n Bsp.:
                \n 500, 1, 2, 3, 4, 5, 6, 7, 8, 9
                \n 500 sind die gsetzten Jetons
                ---------------------------------------------
                """;
    }

    @Override
    public String verarbeiteEingabe(String eingabe) {
        String GLOBAL_STRING = "";
        int[][] eingegebeneBingoZahlen = new int[3][3];

        // Eingabe aufteilen
        String[] teile = eingabe.split(",");
        if (teile.length != 10) {
            return "Fehler: Bitte genau 1 Einsatzwert und 9 Bingozahlen angeben.";
        }

        try {
            einsatz = Integer.parseInt(teile[0].trim());
            spieler.removeJetons(einsatz);
        } catch (NumberFormatException e) {
            return "Fehler: Einsatz ist keine gültige Zahl.";
        }

        // Bingo-Zahlen verarbeiten
        Set<Integer> verwendeteZahlen = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            int zahl;
            try {
                zahl = Integer.parseInt(teile[i].trim());
            } catch (NumberFormatException e) {
                return "Fehler: '" + teile[i] + "' ist keine gültige Zahl.";
            }

            if (zahl < 1 || zahl > 9) {
                return "Fehler: Alle Zahlen müssen zwischen 1 und 9 liegen.";
            }
            if (!verwendeteZahlen.add(zahl)) {
                return "Fehler: Zahl " + zahl + " wurde mehrfach angegeben.";
            }

            // Position berechnen und eintragen
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            eingegebeneBingoZahlen[row][col] = zahl;
        }

        GLOBAL_STRING += "Du hast " + einsatz + " Jetons gesetzt. \n";
        GLOBAL_STRING += "Dein neues Guthaben: " + spieler.getJetons() + "\n";
        GLOBAL_STRING += "Dein Bingofeld wurde erstellt:\n";

        spielfeld.fillList(); // Zahlen vorbereiten
        GLOBAL_STRING += spielfeld.generateBoard(eingegebeneBingoZahlen); // Nutzerfeld anzeigen

        GLOBAL_STRING += "\nDie Zahlen werden nun gezogen...\n\n";

        spielfeld.fillList(); // Ziehung vorbereiten
        GLOBAL_STRING += drawing(); // Zahlen ziehen

        GLOBAL_STRING += spielfeld.renderDrawnBoard(); // Finale Anzeige

        if (gameWon && gameEnd) {
            spieler.addJetons(einsatz * 3);
        }

        return GLOBAL_STRING;
    }


    private String drawing() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < anzDraw; i++) {
            int randomNumber = spielfeld.getRandomNumber(); // Zahl ziehen

            // Alle Felder auf die gezogene Zahl prüfen
            for (int k = 0; k < spielfeld.getSpielFeld().length; k++) {
                for (int j = 0; j < spielfeld.getSpielFeld()[0].length; j++) {
                    if (spielfeld.getSpielFeld()[k][j].getValue() == randomNumber) {
                        spielfeld.getSpielFeld()[k][j].setDisplayValue("X");
                        spielfeld.getSpielFeld()[k][j].setGezogen(true);
                        break; // keine Duplikate in einem Feld
                    }
                }
            }

            // Prüfen ob durch diese Ziehung ein Gewinn entstanden ist
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

        // Zeilen prüfen
        for (int row = 0; row < rows; row++) {
            boolean lineCorrect = true;
            for (int col = 0; col < cols; col++) {
                if (feld[row][col].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) return true;
        }

        // Spalten prüfen
        for (int col = 0; col < cols; col++) {
            boolean lineCorrect = true;
            for (int row = 0; row < rows; row++) {
                if (feld[row][col].isGezogen()) {
                    lineCorrect = false;
                    break;
                }
            }
            if (lineCorrect) return true;
        }

        // Diagonale: links oben nach rechts unten
        boolean leftToRight = true;
        for (int i = 0; i < rows; i++) {
            if (feld[i][i].isGezogen()) {
                leftToRight = false;
                break;
            }
        }
        if (leftToRight) return true;

        // Diagonale: rechts oben nach links unten
        boolean rightToLeft = true;
        for (int i = 0; i < rows; i++) {
            if (feld[i][cols - 1 - i].isGezogen()) {
                rightToLeft = false;
                break;
            }
        }
        return rightToLeft;
    }

    @Override
    public void neuesSpiel() {
        einsatz = 0; // Einsatz zurücksetzen
    }
}
