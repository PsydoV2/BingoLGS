public class Feld {
    private int value;
    private boolean gezogen = false;
    private String displayValue;

    Feld(int value) {
        this.value = value;
        this.displayValue = value + "";
    }

    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public void setGezogen(boolean gezogen) {
        this.gezogen = gezogen;
    }
    public boolean isGezogen() {
        return gezogen;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}
