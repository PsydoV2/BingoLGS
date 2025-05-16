import java.util.Random;
import java.util.Scanner;

public class Bingo extends CasinospielBasis {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private Spielfeld spielfeld = new Spielfeld();
    private int anzDraw = 10;
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
    }
}
