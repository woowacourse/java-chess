package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public Path searchPathTo(final Position from, final Position to, final Piece locatedPiece) {
        validateSameColor(locatedPiece);
        final Movement movement = calculateUnitMovement(from, to);

        if (canNotMoveToLocatedPiece(movement)) {
            throw new IllegalStateException(getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
        }

        return moveToLocatedPiece(from, to, movement, locatedPiece);
    }

    protected abstract Path moveToLocatedPiece(final Position from, final Position to,
                                               final Movement movement, final Piece locatedPiece);

    protected abstract boolean canNotMoveToLocatedPiece(final Movement movement);

    private Movement calculateUnitMovement(final Position from, final Position to) {
        return to.convertMovement(from);
    }

    public void validateSameColor(Piece other) {
        if (other != null && color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isSameColor(Color color) {
        return this.color.isSameColor(color);
    }

    public boolean isDifferentColor(Color color) {
        return this.color.isDifferentColor(color);
    }
}
