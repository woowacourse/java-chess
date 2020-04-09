package chess.domains.piece;

public enum PieceType {
    BISHOP("b", 3, "♗", "♝"),
    BLANK(".", 0, "", ""),
    KING("k", 0, "♔", "♚"),
    KNIGHT("n", 2.5, "♘", "♞"),
    PAWN("p", 1, "♙", "♟"),
    QUEEN("q", 9, "♕", "♛"),
    ROOK("r", 5, "♖", "♜");

    private final String name;
    private final double score;
    private final String whitePiece;
    private final String blackPiece;

    PieceType(String name, double score, String whitePiece, String blackPiece) {
        this.name = name;
        this.score = score;
        this.whitePiece = whitePiece;
        this.blackPiece = blackPiece;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getWhitePiece() {
        return whitePiece;
    }

    public String getBlackPiece() {
        return blackPiece;
    }
}
