package chess.domain.piece;

import chess.domain.position.Position;

public final class Blank extends Piece {
    private static final Piece blank = new Blank();
    private static final String INITIAL_NAME = ".";

    private Blank() {
        super(Team.NOTHING, INITIAL_NAME);
    }

    public static Piece getInstance() {
        return blank;
    }

    @Override
    public boolean movable(final Position source, final Position target, final Piece piece) {
        throw new IllegalStateException("비어 있는 칸입니다.");
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public boolean multipleMovable() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
