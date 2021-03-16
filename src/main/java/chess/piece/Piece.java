package chess.piece;

public abstract class Piece {
    private final String name;
    private final double score;

    protected Piece(String name, double score) {
        this.name = name;
        this.score = score;
    }

}
