import java.util.Random;
import java.util.Scanner;

public class Spielfeld {
    public Feld[][] spielFeld = new Feld[3][3];
    private final String TRENNERVERT = "|";
    private final String TRENNERHORZ = "—";
    private final Random random = new Random();


    public String generateBoard() {
        int rows = spielFeld.length;
        int cols = spielFeld[0].length;

        // Spielfeld initialisieren
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spielFeld[i][j] = new Feld(getFieldNumber());
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

    private int getFieldNumber(){
        return random.nextInt(98) + 1;
    }
}
