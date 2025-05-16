public class Feld {
    private int value;               // Zahl auf dem Feld
    private boolean gezogen = false; // Ob diese Zahl bereits gezogen wurde
    private String displayValue;     // Was auf dem Feld angezeigt wird (z. B. "X" statt Zahl)

    // Konstruktor: Initialisiert Feld mit einer Zahl
    Feld(int value) {
        this.value = value;
        this.displayValue = value + ""; // Standardanzeige ist die Zahl selbst
    }

    // Setzt den Wert des Feldes
    public void setValue(int value) {
        this.value = value;
    }

    // Gibt den Wert des Feldes zurück
    public int getValue() {
        return value;
    }

    // Setzt, ob das Feld als "gezogen" markiert ist
    public void setGezogen(boolean gezogen) {
        this.gezogen = gezogen;
    }

    // Gibt zurück, ob das Feld bereits gezogen wurde
    public boolean isGezogen() {
        return !gezogen;
    }

    // Setzt den anzuzeigenden Wert (z. B. "X")
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    // Gibt den aktuellen Anzeigewert zurück
    public String getDisplayValue() {
        return displayValue;
    }
}
