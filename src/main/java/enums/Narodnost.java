package enums;

public enum Narodnost {
    USA("USA"),
    CZ("CZ"),
    PL("PL"),
    DE("DE");

    private String reprezentacia;

    /**
     *
     * @param reprezentacia danej narodnosti klienta ako text
     */
    Narodnost(String reprezentacia) {
        this.reprezentacia = reprezentacia;
    }

    /**
     *
     * @return textovu reprezentaciu danej narodnsti
     */
    public String getReprezentacia() {
        return reprezentacia;
    }
}
