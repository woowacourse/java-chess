package chess.domain;

public enum PieceValue {
    KING("K", 0, 1),
    QUEEN("Q", 9,2),
    KNIGHT("N", 2.5,4),
    BISHOP("B", 3,5),
    ROOK("R", 5,3),
    PAWN("P", 1,6);

    private String name;
    private double score;
    private final int kindId;

    PieceValue(String name, double score, final int kindId) {
        this.name = name;
        this.score = score;
        this.kindId = kindId;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public int getKindId() {
        return kindId;
    }
}
