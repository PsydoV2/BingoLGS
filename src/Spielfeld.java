import java.util.*;

public class Spielfeld {
    public Feld[][] spielFeld = new Feld[3][3];
    private final String TRENNERVERT = "|";
    private final String TRENNERHORZ = "—";
    private final Random random = new Random();
    private final List<Integer> numbers = new ArrayList<>();

    public void fillList(){
        numbers.clear();

        for(int i = 1; i <= 99; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);
    }

    public int getRandomNumber(){
        int drawn = numbers.getFirst();
        numbers.removeFirst();
        return drawn;
    }

    public String generateBoard() {
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        // Spielfeld initialisieren
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spielFeld[i][j] = new Feld(getRandomNumber());
            }
        }

        StringBuilder sb = new StringBuilder();
        String horizontalLine = TRENNERHORZ.repeat(25);

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

    public Feld[][] getSpielFeld() {
        return spielFeld;
    }

    public String renderDrawnBoard(){
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        StringBuilder sb = new StringBuilder();
        String horizontalLine = TRENNERHORZ.repeat(25);

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
