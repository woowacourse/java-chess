package chess;

public class PieceType {
    private final Name name;
//    private final Color color;

    public PieceType(final Name name) {
        this.name = name;
//        this.color = color;
    }

    public Name getName() {
        return name;
    }
}
