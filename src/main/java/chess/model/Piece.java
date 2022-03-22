package chess.model;

public class Piece {

    private final Name name;
    private final Color color;

    public Piece(Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String value() {
        return name.getValue();
    }

    public boolean isBlack() {
        return color.isBlack();
    }
}
