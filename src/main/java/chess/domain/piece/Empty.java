package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

public class Empty extends Basis {
    public static final String EMPTY_MOVE_ERROR = "해당 위치에 움직일 말이 없습니다";
    private static final String EMPTY_DISPLAYNAME = ".";

    public Empty() {
        super(EMPTY_DISPLAYNAME);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        throw new UnsupportedOperationException(EMPTY_MOVE_ERROR);
    }

    @Override
    public void moveForKill(final Position to, final Pieces pieces) {
    }

    @Override
    public boolean hasPosition(final Position position) {
        throw new UnsupportedOperationException(EMPTY_MOVE_ERROR);
    }

    @Override
    public Position getPosition() {
        throw new UnsupportedOperationException(EMPTY_MOVE_ERROR);
    }

    @Override
    public boolean isSameColor(final Color color) {
        throw new UnsupportedOperationException(EMPTY_MOVE_ERROR);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public Column getColumn() {
        throw new UnsupportedOperationException(EMPTY_MOVE_ERROR);
    }
}
