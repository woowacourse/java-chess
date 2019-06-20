package chess.domain;

public class Count {

    private int count;

    public void add() {
        count += 1;
    }

    public double score(final Piece piece) {
        return count * piece.getScore();
    }
}
