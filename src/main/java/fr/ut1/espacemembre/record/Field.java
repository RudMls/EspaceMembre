package fr.ut1.espacemembre.record;

public record Field(String value, String error) {

    public Field() {
        this("", "");
    }

}
