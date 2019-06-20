package chess.model.unit;

public enum UnitClass {
    KING("King"),
    QUEEN("Queen"),
    ROOK("Rook"),
    BISHOP("Bishop"),
    KNIGHT("Knight"),
    PAWN("PawnMove");

    private final String name;

    UnitClass(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
