package chess.domain.piece;

import chess.domain.position.Position;

public class Blank extends Piece {
    private static final Piece blank = new Blank();
    private static final String INITIAL_NAME = ".";

    private Blank() {
        super(Team.NOTHING, INITIAL_NAME);
    }

    public static Piece getInstance() {
        return blank;
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece piece) {
        throw new IllegalStateException("비어 있는 칸입니다.");
    }

    @Override
    public double getScore() {
        return 0;
    }
}
