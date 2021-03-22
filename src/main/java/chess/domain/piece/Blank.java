package chess.domain.piece;

import chess.domain.position.Position;

final public class Blank extends Piece {
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
}
