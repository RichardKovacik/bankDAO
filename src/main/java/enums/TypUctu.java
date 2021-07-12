package enums;

public enum TypUctu {
    PODNIKATELSKY("podnikatelsky"),
    STUDENTSKY("studentsky"),
    OBYCAJNY("obycajny");

    private String reprezentacia;

    /**
     *
     * @param reprezentacia daneho typu uctu ako text
     */
    TypUctu(String reprezentacia) {
        this.reprezentacia = reprezentacia;
    }

    /**
     *
     * @return reprezentaciu enumu ako string
     */
    public String getReprezentacia() {
        return reprezentacia;
    }
}
