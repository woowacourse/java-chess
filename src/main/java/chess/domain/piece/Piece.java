package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;

public abstract class Piece {
    protected static final String INVALID_DESTINATION_MESSAGE = "해당 위치로 이동할 수 없습니다.";
    protected static final String INVALID_MOVING_CAUSE_OF_CATCHING = "같은 색 말은 잡을 수 없습니다.";

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
        return color == Color.BLACK;
    }

    protected final void validateSamePosition(final Position targetPosition) {
        if (position.equals(targetPosition)) {
            throw new IllegalArgumentException(INVALID_DESTINATION_MESSAGE);
        }
    }

    protected final void validateDestination(final Position targetPosition) {
        if (!canMove(targetPosition)) {
            throw new IllegalArgumentException(INVALID_DESTINATION_MESSAGE);
        }
    }

    protected final void validateCatchingSameColor(final Piece pieceInTargetPosition) {
        if (pieceInTargetPosition.isSameColor(color)) {
            throw new IllegalArgumentException(INVALID_MOVING_CAUSE_OF_CATCHING);
        }
    }

    protected abstract boolean canMove(final Position targetPosition);

    public abstract Piece move(final Piece pieceInTargetPosition);

    public abstract List<Position> getPassingPositions(final Position targetPosition);

    public abstract boolean isKing();

    public final Color getColor() {
        return color;
    }

    public final Position getPosition() {
        return position;
    }
}
