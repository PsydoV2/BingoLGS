import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CasinospielBasis bingo = new Bingo(new Spieler("Max Mustermann", 100));
        Scanner scanner = new Scanner(System.in);


        String jetonsEinsatz;
        String anzahlZiehungen;
        String reihenInput;
        String checkString;

        System.out.print(bingo.ersteNachricht());

        do {
            jetonsEinsatz = scanner.nextLine();
            checkString = bingo.verarbeiteEingabe(jetonsEinsatz);
            System.out.print(checkString);
        }while (!checkString.contains("akzeptiert"));

        do{
            anzahlZiehungen = scanner.nextLine();
            checkString = bingo.verarbeiteEingabe(anzahlZiehungen);
            System.out.print(checkString);
        }while (!checkString.contains("Ziehungen"));

        do{

            reihenInput = scanner.nextLine();
            checkString = bingo.verarbeiteEingabe(reihenInput);
            System.out.print(checkString);
        }while(!checkString.contains("gespeichert"));

        do{

            reihenInput = scanner.nextLine();
            checkString = bingo.verarbeiteEingabe(reihenInput);
            System.out.print(checkString);
        }while(!checkString.contains("gespeichert"));

        do{

            reihenInput = scanner.nextLine();
            checkString = bingo.verarbeiteEingabe(reihenInput);
            System.out.print(checkString);
        }while(!checkString.contains("gespeichert"));

    }
}