import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CasinospielBasis bingo = new Bingo(new Spieler("", 100));
        Scanner scanner = new Scanner(System.in);

        boolean gameRunning = true;
        String jetonsEinsatz = "";
        String anzahlZiehungen = "";
        String reihenInput = "";
        String checkString = "";

        while(gameRunning){

            do {
                System.out.print(bingo.ersteNachricht());
                jetonsEinsatz = scanner.nextLine();
                checkString = bingo.verarbeiteEingabe(jetonsEinsatz);
                System.out.println(checkString);
            }while (!checkString.contains("akzeptiert"));

            do{
                anzahlZiehungen = scanner.nextLine();
                checkString = bingo.verarbeiteEingabe(anzahlZiehungen);
                System.out.print(checkString);
            }while (!checkString.contains("Ziehungen"));

            do{

                reihenInput = scanner.nextLine();
                checkString = bingo.verarbeiteEingabe(reihenInput);
                System.out.println(checkString);
            }while(!checkString.contains("gespeichert"));

            do{

                reihenInput = scanner.nextLine();
                checkString = bingo.verarbeiteEingabe(reihenInput);
                System.out.println(checkString);
            }while(!checkString.contains("gespeichert"));

            do{

                reihenInput = scanner.nextLine();
                checkString = bingo.verarbeiteEingabe(reihenInput);
                System.out.println(checkString);
            }while(!checkString.contains("gespeichert"));

            System.out.println("Möchtest du eine weitere Runde spielen? (yes/no)");
            gameRunning = scanner.next().equals("yes");
        }

    }
}