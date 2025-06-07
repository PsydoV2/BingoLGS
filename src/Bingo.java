import java.util.*;

/**
 * Implementation eines textbasierten Bingo-Spiels.
 * Der Spieler wählt Einsatz, Anzahl Ziehungen und füllt ein 3x3-Feld.
 * Ziel ist es, bei der Ziehung eine vollständige Reihe, Spalte oder Diagonale zu treffen.
 */
public class Bingo extends CasinospielBasis {

    // Spielphasen als Zustandsmodell
    private enum Zustand { EINSATZ, ANZ_ZIEHUNGEN, ERSTE_REIHE, ZWEITE_REIHE, DRITTE_REIHE }

    private Zustand zustand = Zustand.EINSATZ;               // Aktueller Spielzustand
    private final Spielfeld spielfeld = new Spielfeld();     // Spielfeld (visuelle Darstellung)
    private int einsatz;                                     // Einsatz in Jetons
    private int ziehungen = 5;                               // Anzahl der gewünschten Ziehungen (3–9)
    private int[][] bingoFeld = new int[3][3];               // Vom Spieler gesetzte Bingo-Zahlen
    private int aktuelleReihe = 0;                           // Fortschritt beim Befüllen der Reihen
    private final Set<Integer> gezogeneZahlen = new HashSet<>(); // Zufällig gezogene Zahlen

    public Bingo(Spieler spieler) {
        super("Bingo", spieler);
    }

    @Override
    public String ersteNachricht() {
        return "Willkommen zu Bingo!\nBitte gib deinen Einsatz (Jetons) ein:";
    }

    /**
     * Verarbeitet die Nutzereingabe abhängig vom aktuellen Zustand.
     */
    @Override
    public String verarbeiteEingabe(String eingabe) {
        if (eingabe == null || eingabe.isBlank()) return "Eingabe darf nicht leer sein.";

        return switch (zustand) {
            case EINSATZ        -> "\n" + verarbeiteEinsatz(eingabe);
            case ANZ_ZIEHUNGEN  -> "\n" + verarbeiteZiehungsEingabe(eingabe);
            case ERSTE_REIHE,
                 ZWEITE_REIHE,
                 DRITTE_REIHE   -> "\n" + verarbeiteReihe(eingabe);
        };
    }

    /**
     * Liest und prüft den Einsatz. Wechsel in nächste Phase nur bei gültigem Wert.
     */
    private String verarbeiteEinsatz(String eingabe) {
        try {
            int tempEinsatz = Integer.parseInt(eingabe.trim());
            if (tempEinsatz <= 0) return "Der Einsatz muss positiv sein.";
            if (spieler.getJetons() < tempEinsatz) return "Nicht genügend Jetons.";

            einsatz = tempEinsatz;
            spieler.removeJetons(einsatz);
            zustand = Zustand.ANZ_ZIEHUNGEN;
            return "Einsatz: " + einsatz + " Jetons akzeptiert.\nBitte gib die Anzahl der gewünschten Ziehungen ein (zwischen 3 und 9):";

        } catch (NumberFormatException e) {
            return "Ungültige Eingabe. Bitte gib eine Zahl ein.";
        }
    }

    /**
     * Liest die Anzahl der gewünschten Ziehungen und validiert den Bereich (3–9).
     */
    private String verarbeiteZiehungsEingabe(String eingabe) {
        try {
            int anz = Integer.parseInt(eingabe.trim());
            if (anz < 3 || anz > 9) return "Bitte gib eine Zahl zwischen 3 und 9 ein.";

            ziehungen = anz;
            zustand = Zustand.ERSTE_REIHE;
            return "\nZiehungsanzahl: " + ziehungen + ". Bitte gib die erste Reihe (3 Zahlen 1-9, keine Duplikate, durch Komma getrennt) ein:";
        } catch (NumberFormatException e) {
            return "Ungültige Eingabe. Bitte gib eine Zahl ein.";
        }
    }

    /**
     * Verarbeitet die Eingabe einer der drei Reihen. Prüft auf Wertebereich und Duplikate.
     */
    private String verarbeiteReihe(String eingabe) {
        String[] teile = eingabe.split(",");
        if (teile.length != 3) return "Bitte genau 3 Zahlen eingeben.";

        Set<Integer> reihenZahlen = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            try {
                int zahl = Integer.parseInt(teile[i].trim());
                if (zahl < 1 || zahl > 9) return "Zahlen müssen zwischen 1 und 9 liegen.";
                if (reihenZahlen.contains(zahl)) return "Zahlen dürfen sich nicht doppeln.";

                // Überprüfung auf globale Duplikate
                for (int r = 0; r < aktuelleReihe; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (bingoFeld[r][c] == zahl) return "Zahl " + zahl + " wurde bereits verwendet.";
                    }
                }

                reihenZahlen.add(zahl);
                bingoFeld[aktuelleReihe][i] = zahl;

            } catch (NumberFormatException e) {
                return "Alle Eingaben müssen Zahlen sein.";
            }
        }

        // Zustandsübergang je nach Fortschritt
        aktuelleReihe++;
        if (aktuelleReihe == 1) {
            zustand = Zustand.ZWEITE_REIHE;
            return "Erste Reihe gespeichert. Bitte gib die zweite Reihe ein:";
        } else if (aktuelleReihe == 2) {
            zustand = Zustand.DRITTE_REIHE;
            return "Zweite Reihe gespeichert. Bitte gib die dritte Reihe ein:";
        } else {
            return "Dritte Reihe gespeichert. Bingo-Feld vollständig!\n"
                    + spielfeld.generateBoard(bingoFeld)
                    + auswerten();
        }
    }

    /**
     * Führt die Ziehungen durch, markiert Treffer, prüft auf Bingo und berechnet ggf. den Gewinn.
     */
    private String auswerten() {
        Random rand = new Random();
        while (gezogeneZahlen.size() < ziehungen) {
            gezogeneZahlen.add(rand.nextInt(9) + 1);
        }

        // Markiere getroffene Zahlen im Spielfeld
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
        sb.append("Gezogene Zahlen: ").append(gezogeneZahlen).append("\n");
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
            sb.append("BINGO! Du hast gewonnen.\nGewinn: ").append(gewinn).append(" Jetons\n");
        } else {
            sb.append("Leider kein Bingo. Einsatz verloren.\n");
        }

        neuesSpiel();
        return sb.toString();
    }

    /**
     * Prüft das Spielfeld auf vollständige Treffer in Reihe, Spalte oder Diagonale.
     */
    private boolean pruefeBingo() {
        for (int i = 0; i < 3; i++) {
            // Reihen
            if (gezogeneZahlen.contains(bingoFeld[i][0]) &&
                    gezogeneZahlen.contains(bingoFeld[i][1]) &&
                    gezogeneZahlen.contains(bingoFeld[i][2]))
                return true;

            // Spalten
            if (gezogeneZahlen.contains(bingoFeld[0][i]) &&
                    gezogeneZahlen.contains(bingoFeld[1][i]) &&
                    gezogeneZahlen.contains(bingoFeld[2][i]))
                return true;
        }

        // Diagonalen
        if (gezogeneZahlen.contains(bingoFeld[0][0]) &&
                gezogeneZahlen.contains(bingoFeld[1][1]) &&
                gezogeneZahlen.contains(bingoFeld[2][2]))
            return true;

        return gezogeneZahlen.contains(bingoFeld[0][2]) &&
                gezogeneZahlen.contains(bingoFeld[1][1]) &&
                gezogeneZahlen.contains(bingoFeld[2][0]);
    }

    /**
     * Setzt das Spiel vollständig zurück.
     */
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
