package chess.domain.piece;

public enum Type {
    ROOK("R","Rook", 5),
    KNIGHT("N", "Knight",2.5),
    BISHOP("B", "Bishop", 3),
    QUEEN("Q","Queen", 9),
    KING("K","King", 0),
    PAWN("P", "Pawn",1);

    private final String name;
    private final String fullName;
    private final double score;

    Type(String name,String fullName, double score) {
        this.name = name;
        this.fullName = fullName;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
}
