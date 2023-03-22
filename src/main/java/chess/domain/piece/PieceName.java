package chess.domain.piece;

public enum PieceName {

    BISHOP_NAME("b"),
    KING_NAME("k"),
    KNIGHT_NAME("n"),
    PAWN_NAME("p"),
    QUEEN_NAME("q"),
    ROOK_NAME("r");

    private final String name;

    PieceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
