/**
 * Repräsentiert ein einzelnes Feld im Bingo-Spielfeld.
 * Jedes Feld enthält eine Zahl, eine Markierung (gezogen oder nicht)
 * und einen Anzeige-String (z. B. "X" statt Zahl, wenn gezogen).
 */
public class Feld {

    private int value;               // Ursprünglicher Zahlenwert des Feldes
    private String displayValue;     // String zur Anzeige (z. B. "X" statt Zahl)

    /**
     * Konstruktor: Initialisiert das Feld mit einem Zahlenwert.
     * @param value Der angezeigte Zahlenwert (zwischen 1 und 9)
     */
    Feld(int value) {
        this.value = value;
        this.displayValue = String.valueOf(value); // Anfangsanzeige: Zahl selbst
    }

    /** Setzt den internen Zahlenwert des Feldes */
    public void setValue(int value) {
        this.value = value;
    }

    /** Gibt den ursprünglichen Zahlenwert zurück */
    public int getValue() {
        return value;
    }

    /**
     * Setzt den Anzeigetext des Feldes (z.B. "X" oder Zahl).
     * Wird genutzt, um Treffer visuell zu markieren.
     */
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /** Gibt den aktuell anzuzeigenden Text zurück */
    public String getDisplayValue() {
        return displayValue;
    }
}
