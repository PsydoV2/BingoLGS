public class Spielfeld {
    public Feld[][] spielFeld = new Feld[3][3];

    /**
     * Initialisiert das Spielbrett mit Zahlen.
     */
    public String generateBoard(int[][] numbers) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                spielFeld[i][j] = new Feld(numbers[i][j]);

        return renderDrawnBoard();
    }

    /**
     * Gibt das Spielbrett mit gezogenen Zahlen (X) als ASCII-Art zurück.
     */
    public String renderDrawnBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append("""
               ╔═══════════════════════════════╗
               ║           B I N G O           ║
               ╠═════════╦═════════╦═══════════╣
               """);

        for (int i = 0; i < 3; i++) {
            sb.append("║");
            for (int j = 0; j < 3; j++) {
                String val = spielFeld[i][j].getDisplayValue();
                val = formatCell(val);
                sb.append("  ").append(val).append("  ║");
            }
            if (i < 2) {
                sb.append("\n╠═════════╬═════════╬═══════════╣\n");
            } else {
                sb.append("\n╚═════════╩═════════╩═══════════╝\n");
            }
        }

        return sb.toString();
    }

    /**
     * Formatiert Zahlen bzw. X zentriert auf 5 Zeichen.
     */
    private String formatCell(String val) {
        if (val.equals("X")) return "  X  ";
        return switch (val.length()) {
            case 1 -> "  " + val + "  ";
            case 2 -> " " + val + "  ";
            default -> val;
        };
    }
}
