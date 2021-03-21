package chess.domain.piece;

import chess.domain.position.Position;

public final class Blank extends Piece {

    private static final Piece BLANK = new Blank();
    private static final String INITIAL_NAME = ".";

    private Blank() {
        super(Team.NOTHING, INITIAL_NAME);
    }

    public static Piece getInstance() {
        return BLANK;
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece piece) {
        throw new UnsupportedOperationException("비어 있는 칸입니다.");
    }

    @Override
    public boolean hasMiddlePath() {
        return false;
    }

    @Override
    public double getScore() {
        return 0;
    }
}
