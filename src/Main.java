public class Main {
    public static void main(String[] args) {
        CasinospielBasis bingo = new Bingo(new Spieler("", 100));
        System.out.print(bingo.ersteNachricht());
        System.out.print(bingo.verarbeiteEingabe("100, 1, 2, 3, 4, 5, 6, 7, 8, 9"));
    }
}