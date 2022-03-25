package chess.domain.piece;

public enum State {

    PAWN("P", 1),
    ROOK("R", 5),
    KNIGHT("N", 2.5),
    BISHOP("B", 3),
    QUEEN("Q", 9),
    KING("K", 0);

    private final String name;
    private final double score;

    State(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getNameOfWhitePiece() {
        return name.toLowerCase();
    }

    public String getNameOfBlackPiece() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
