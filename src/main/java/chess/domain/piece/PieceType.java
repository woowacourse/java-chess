package chess.domain.piece;

public enum PieceType {
    BISHOP("B", 3),
    ROOK("R", 5),
    QUEEN("Q", 9),
    KNIGHT("N", 2.5),
    KING("K", 0),
    PAWN("P", 1);

    private final String name;
    private final double score;

    PieceType(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String formatName(Color color) {
        return color.formatName(name);
    }

    public double getScore(){
        return score;
    }

    public double getDuplicatePawnScore(){
        return 0.5;
    }
}
