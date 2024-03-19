package chess.model.piece;

public class Signature {
    private final String value;

    public Signature(String value) {
        this.value = value;
    }

    public String getWhite() {
        return value;
    }

    public String getBlack() {
        return value.toUpperCase();
    }
}
