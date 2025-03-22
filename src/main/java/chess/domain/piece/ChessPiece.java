package chess.domain.piece;

import chess.domain.pieces.Color;
import chess.domain.position.Position;

import java.util.List;

public abstract class ChessPiece {

    protected final Color color;
    protected Position position;

    public ChessPiece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(final Position newPosition) {
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        this.position = newPosition;
    }

    public void take(final Position newPosition) {
        if (!calculateCanTakePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        this.position = newPosition;
    }

    public Position getPosition() {
        return this.position;
    }

    protected abstract List<Position> calculateCanMovePositions();
    protected abstract List<Position> calculateCanTakePositions();
}
