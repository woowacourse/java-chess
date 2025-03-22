package chess.piece;

import chess.position.Position;

import java.util.List;

public abstract class ChessPiece {

    protected Position position;

    protected ChessPiece(final Position position) {
        this.position = position;
    }

    public void move(final Position newPosition) {
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        this.position = newPosition;
    }

    protected abstract List<Position> calculateCanMovePositions();
}
