import java.util.*;

public class Bingo extends CasinospielBasis {

    private enum Zustand { EINSATZ, ANZ_ZIEHUNGEN, ERSTE_REIHE, ZWEITE_REIHE, DRITTE_REIHE }

    private Zustand zustand = Zustand.EINSATZ;
    private final Spielfeld spielfeld = new Spielfeld();
    private int einsatz;
    private int ziehungen = 5;
    private int[][] bingoFeld = new int[3][3];
    private int aktuelleReihe = 0;
    private final Set<Integer> gezogeneZahlen = new HashSet<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public Bingo(Spieler spieler) {
        super("Bingo", spieler);
    }

    @Override
    public String ersteNachricht() {
        return ANSI_YELLOW +"""
               ╔════════════════════════════════════════╗
               ║               🎉 BINGO! 🎉             ║
               ║────────────────────────────────────────║
               ║  Setze Jetons, wähle Zahlen und hoffe  ║
               ║  auf eine Reihe, Spalte oder Diagonale ║
               ╚════════════════════════════════════════╝
               """ + ANSI_YELLOW + "\nGib deinen Einsatz (Jetons) ein:" + ANSI_RESET;
    }

    @Override
    public String verarbeiteEingabe(String eingabe) {
        if (eingabe == null || eingabe.isBlank()) return ANSI_RED + "Eingabe darf nicht leer sein." + ANSI_RESET;
        return switch (zustand) {
            case EINSATZ        -> "\n" + verarbeiteEinsatz(eingabe);
            case ANZ_ZIEHUNGEN  -> "\n" + verarbeiteZiehungsEingabe(eingabe);
            case ERSTE_REIHE, ZWEITE_REIHE, DRITTE_REIHE -> "\n" + verarbeiteReihe(eingabe);
        };
    }

    private String verarbeiteEinsatz(String eingabe) {
        try {
            int tempEinsatz = Integer.parseInt(eingabe.trim());
            if (tempEinsatz <= 0) return ANSI_RED + "Der Einsatz muss positiv sein:" + ANSI_RESET;
            if (spieler.getJetons() < tempEinsatz) return ANSI_RED + "Nicht genügend Jetons." + ANSI_RESET;

            einsatz = tempEinsatz;
            spieler.removeJetons(einsatz);
            zustand = Zustand.ANZ_ZIEHUNGEN;
            return ANSI_GREEN + einsatz + " Jetons akzeptiert." + ANSI_YELLOW + "\n\nBitte gib die Anzahl der gewünschten Ziehungen ein (zwischen 3 und 9):" + ANSI_RESET;

        } catch (NumberFormatException e) {
            return ANSI_RED + "Ungültige Eingabe. Bitte gib eine Zahl ein:" + ANSI_RESET;
        }
    }

    private String verarbeiteZiehungsEingabe(String eingabe) {
        try {
            int anz = Integer.parseInt(eingabe.trim());
            if (anz < 3 || anz > 9) return ANSI_RED + "Bitte gib eine Zahl zwischen 3 und 9 ein:" + ANSI_RESET;

            ziehungen = anz;
            zustand = Zustand.ERSTE_REIHE;
            return ANSI_GREEN + ziehungen + " Ziehungen!" + ANSI_YELLOW + " \n\nBitte gib die erste Reihe (3 Zahlen 1-9, keine Duplikate, durch Komma getrennt) ein:" + ANSI_RESET;
        } catch (NumberFormatException e) {
            return ANSI_RED + "Ungültige Eingabe. Bitte gib eine Zahl ein:" + ANSI_RESET;
        }
    }

    private String verarbeiteReihe(String eingabe) {
        eingabe = eingabe.replace(" ", "");
        String[] teile = eingabe.split(",");
        if (teile.length != 3) return ANSI_RED + "Bitte genau 3 Zahlen eingeben:" + ANSI_RESET;

        Set<Integer> reihenZahlen = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            try {
                int zahl = Integer.parseInt(teile[i].trim());
                if (zahl < 1 || zahl > 9) return ANSI_RED + "Zahlen müssen zwischen 1 und 9 liegen." + ANSI_RESET;
                if (reihenZahlen.contains(zahl)) return ANSI_RED + "Zahlen dürfen sich nicht doppeln." + ANSI_RESET;

                for (int r = 0; r < aktuelleReihe; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (bingoFeld[r][c] == zahl) return ANSI_RED + "Zahl " + zahl + " wurde bereits verwendet." + ANSI_RESET;
                    }
                }

                reihenZahlen.add(zahl);
                bingoFeld[aktuelleReihe][i] = zahl;

            } catch (NumberFormatException e) {
                return ANSI_RED + "Alle Eingaben müssen Zahlen sein." + ANSI_RESET;
            }
        }

        aktuelleReihe++;
        if (aktuelleReihe == 1) {
            zustand = Zustand.ZWEITE_REIHE;
            return ANSI_GREEN + "Erste Reihe gespeichert." + ANSI_YELLOW + " \n\nBitte gib die zweite Reihe ein:";
        } else if (aktuelleReihe == 2) {
            zustand = Zustand.DRITTE_REIHE;
            return ANSI_GREEN + "Zweite Reihe gespeichert." + ANSI_YELLOW + " \n\nBitte gib die dritte Reihe ein:";
        } else {
            return ANSI_GREEN + "Dritte Reihe gespeichert. \n\nBingo-Feld vollständig!\n" + ANSI_RESET
                    + spielfeld.generateBoard(bingoFeld)
                    + auswerten();
        }
    }

    private String auswerten() {
        Random rand = new Random();
        while (gezogeneZahlen.size() < ziehungen) {
            gezogeneZahlen.add(rand.nextInt(9) + 1);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gezogeneZahlen.contains(bingoFeld[i][j])) {
                    spielfeld.spielFeld[i][j].setDisplayValue("X");
                } else {
                    spielfeld.spielFeld[i][j].setDisplayValue(String.valueOf(bingoFeld[i][j]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_RED + "\n" +
                """
                ╔═══════════════════════════╗
                    🎯 Gezogene Zahlen 🎯   \s
                ╚═══════════════════════════╝
               \s""" + ANSI_RESET);
        sb.append(gezogeneZahlen).append("\n\n");
        sb.append(spielfeld.renderDrawnBoard());

        boolean bingo = pruefeBingo();
        if (bingo) {
            int faktor = switch (ziehungen) {
                case 3 -> 10;
                case 4 -> 6;
                case 5 -> 3;
                case 6 -> 2;
                default -> 1;
            };
            int gewinn = einsatz * faktor;
            spieler.addJetons(gewinn);
            sb.append(ANSI_GREEN + "\n🟩🟩🟩  B I N G O !  🟩🟩🟩\n");
            sb.append("Glückwunsch! Du hast eine vollständige Reihe.\n");
            sb.append("💰 Gewinn: ").append(gewinn).append(" Jetons 💰\n" + ANSI_RESET);
        } else {
            sb.append(ANSI_RED + "⛔ Leider kein Bingo erzielt ⛔\n");
            sb.append("Dein Einsatz von ").append(einsatz).append(" Jetons ist verloren.\n" + ANSI_RESET);
        }

        neuesSpiel();
        return sb.toString();
    }

    private boolean pruefeBingo() {
        for (int i = 0; i < 3; i++) {
            if (gezogeneZahlen.contains(bingoFeld[i][0]) &&
                    gezogeneZahlen.contains(bingoFeld[i][1]) &&
                    gezogeneZahlen.contains(bingoFeld[i][2])) return true;
            if (gezogeneZahlen.contains(bingoFeld[0][i]) &&
                    gezogeneZahlen.contains(bingoFeld[1][i]) &&
                    gezogeneZahlen.contains(bingoFeld[2][i])) return true;
        }

        return (gezogeneZahlen.contains(bingoFeld[0][0]) &&
                gezogeneZahlen.contains(bingoFeld[1][1]) &&
                gezogeneZahlen.contains(bingoFeld[2][2]))
                || (gezogeneZahlen.contains(bingoFeld[0][2]) &&
                gezogeneZahlen.contains(bingoFeld[1][1]) &&
                gezogeneZahlen.contains(bingoFeld[2][0]));
    }

    @Override
    public void neuesSpiel() {
        zustand = Zustand.EINSATZ;
        aktuelleReihe = 0;
        einsatz = 0;
        ziehungen = 5;
        bingoFeld = new int[3][3];
        gezogeneZahlen.clear();
    }
}
