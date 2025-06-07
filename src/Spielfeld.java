/**
 * Repräsentiert ein 3x3-Spielfeld für das Bingo-Spiel.
 * Enthält Methoden zur Initialisierung, Darstellung und Verwaltung gezogener Zahlen.
 */
public class Spielfeld {

    public Feld[][] spielFeld = new Feld[3][3];         // 3x3-Feld bestehend aus Feld-Objekten
    private final String TRENNERVERT = "|";             // Zeichen für vertikale Trennung der Spalten



    /**
     * Erstellt das Spielbrett visuell aus einem 3x3-Zahlenarray.
     * Wandelt die Zahlen in Feld-Objekte um.
     *
     * @param numbers Das vom Spieler gesetzte Bingo-Feld
     * @return Das formattierte Spielfeld als String
     */
    public String generateBoard(int[][] numbers) {
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        // Feld initialisieren mit neuen Zahlen
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spielFeld[i][j] = new Feld(numbers[i][j]);
            }
        }

        // Feld visuell darstellen
        StringBuilder sb = new StringBuilder();
        String horizontalLine = "—".repeat(cols * 8); // horizontale Linie zur Trennung

        sb.append(horizontalLine).append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(TRENNERVERT).append("\t")
                        .append(spielFeld[i][j].getDisplayValue()).append("\t");
            }
            sb.append(TRENNERVERT).append("\n").append(horizontalLine).append("\n");
        }

        return sb.toString();
    }

    /**
     * Gibt das Spielfeld nach der Ziehung zurück,
     * wobei gezogene Zahlen mit "X" markiert wurden.
     *
     * @return Das aktuelle Spielfeld mit Treffern
     */
    public String renderDrawnBoard() {
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        StringBuilder sb = new StringBuilder();
        String horizontalLine = "—".repeat(cols * 8);

        sb.append(horizontalLine).append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(TRENNERVERT).append("\t")
                        .append(spielFeld[i][j].getDisplayValue()).append("\t");
            }
            sb.append(TRENNERVERT).append("\n").append(horizontalLine).append("\n");
        }

        return sb.toString();
    }
}
