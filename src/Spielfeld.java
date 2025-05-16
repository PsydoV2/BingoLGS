import java.util.Scanner;

public class Spielfeld {
    private Scanner scanner = new Scanner(System.in);
    public Feld[][] spielFeld = new Feld[3][3];
    private String TRENNERVERT = "|";
    private String TRENNERHORZ = "";


    public String generateBoard(){
        return "";
    }

    public Feld[][] getSpielFeld() {
        return spielFeld;
    }
}
