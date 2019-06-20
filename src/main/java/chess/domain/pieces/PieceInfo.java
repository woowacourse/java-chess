package chess.domain.pieces;

public enum PieceInfo {
    Queen("Queen", 9.0),
    Rook("Rook", 5.0),
    Bishop("Bishop", 3.0),
    Knight("Knight", 2.5),
    Pawn("Pawn", 1.0),
    King("King", 0.0),
    Empty("Empty", 0.0);

    private String name;
    private double score;

    PieceInfo(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public double score() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }
}
