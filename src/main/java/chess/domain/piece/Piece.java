package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;

public abstract class Piece {

    protected final Position position;
    protected final Color color;

    protected Piece(final File file, final Rank rank, final Color color) {
        this.color = color;
        this.position = new Position(file, rank);
    }

    public final boolean isSameColor(final Color otherColor) {
        return this.color == otherColor;
    }

    public final boolean isBlack() {
        return color.isBlack();
    }

    protected final void validateSamePosition(final Position targetPosition) {
        if (position.equals(targetPosition)) {
            throw new IllegalArgumentException("현재 위치로 이동할 수 없습니다.");
        }
    }

    protected abstract boolean canMove(final Position targetPosition);

    public abstract List<Position> getPassingPath(final Position targetPosition);

    public final Color getColor() {
        return color;
    }

    public final Position getPosition() {
        return position;
    }
}
