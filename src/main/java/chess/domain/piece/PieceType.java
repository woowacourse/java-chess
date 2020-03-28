package chess.domain.piece;

public enum PieceType {
    PAWN(1.0, "p"),
    KNIGHT(2.5, "n"),
    BISHOP(3.0, "b"),
    ROOK(5.0, "r"),
    QUEEN(9.0, "q"),
    KING(0.0, "k");

    private double score;
    private String expression;

    PieceType(double score, String expresion) {
        this.score = score;
        this.expression = expresion;
    }

    public String getAcronymToLowerCase() {
        return this.expression.toLowerCase();
    }

    public String getAcronymToUpperCase() {
        return this.expression.toUpperCase();
    }

    public boolean canMove() {
        return true;
    }
}
