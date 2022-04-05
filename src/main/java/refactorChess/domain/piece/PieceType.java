package refactorChess.domain.piece;


public enum PieceType {

    PAWN("p", 1.0),
    ROOK("r", 5.0),
    KNIGHT("n", 2.5),
    BISHOP("b", 3.0),
    QUEEN("q", 9.0),
    KING("k", 0.0),
    NO_PIECE(".", 0.0)
    ;

    private final String name;
    private final double score;

    PieceType(String name, double score) {
        this.name = name;
        this.score = score;
    }
}
