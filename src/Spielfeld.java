import java.util.*;

public class Spielfeld {
    public Feld[][] spielFeld = new Feld[3][3]; // 3x3 Bingo-Feld
    private final String TRENNERVERT = "|";      // Trenner für Spalten
    private final List<Integer> numbers = new ArrayList<>(); // Liste aller möglichen Zahlen

    // Füllt die Liste mit Zahlen von 1 bis 99 und mischt sie
    public void fillList(){
        numbers.clear();
        for(int i = 1; i <= 9; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    // Gibt die oberste (zufällige) Zahl zurück und entfernt sie aus der Liste
    public int getRandomNumber(){
        int drawn = numbers.getFirst();
        numbers.removeFirst();
        return drawn;
    }

    // Erstellt ein neues Bingo-Feld mit zufälligen Zahlen
    public String generateBoard(int[][] numbers) {
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        // Feldzellen mit Zahlen füllen
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spielFeld[i][j] = new Feld(numbers[i][j]);
            }
        }

        // Feld als String aufbauen
        StringBuilder sb = new StringBuilder();
        String horizontalLine = "—".repeat(cols * 8); // horizontale Linie

        sb.append(horizontalLine).append("\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(TRENNERVERT).append("\t")
                        .append(spielFeld[i][j].getDisplayValue()).append("\t");
            }
            sb.append(TRENNERVERT).append("\n")
                    .append(horizontalLine).append("\n");
        }

        return sb.toString();
    }

    // Gibt das Spielfeld-Array zurück
    public Feld[][] getSpielFeld() {
        return spielFeld;
    }

    // Gibt das aktuelle Spielfeld zurück (nach gezogenen Zahlen)
    public String renderDrawnBoard(){
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        StringBuilder sb = new StringBuilder(); // horizontale Linie
        String horizontalLine = "—".repeat(cols * 8);


        sb.append(horizontalLine).append("\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(TRENNERVERT).append("\t")
                        .append(spielFeld[i][j].getDisplayValue()).append("\t");
            }
            sb.append(TRENNERVERT).append("\n")
                    .append(horizontalLine).append("\n");
        }

        return sb.toString();
    }
}
