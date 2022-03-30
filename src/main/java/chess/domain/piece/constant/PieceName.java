package chess.domain.piece.constant;

public enum PieceName {

    KING("King"),
    QUEEN("Queen"),
    BISHOP("Bishop"),
    ROOK("Rook"),
    KNIGHT("Knight"),
    PAWN("Pawn"),
    ;

    private final String name;

    PieceName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
