package chess.piece;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class King {

    private final Position position;

    public King(final Position position) {
        this.position = position;
    }

    public King move(final Position newPosition) {
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        return new King(newPosition);
    }

    private List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMoveLeft()) {
            positions.add(position.moveLeft());
        }
        if (position.canMoveRight()) {
            positions.add(position.moveRight());
        }
        if (position.canMoveUp()) {
            positions.add(position.moveUp());
        }
        if (position.canMoveDown()) {
            positions.add(position.moveDown());
        }
        if (position.canMoveLeftUp()) {
            positions.add(position.moveLeftUp());
        }
        if (position.canMoveLeftDown()) {
            positions.add(position.moveLeftDown());
        }
        if (position.canMoveRightUp()) {
            positions.add(position.moveRightUp());
        }
        if (position.canMoveRightDown()) {
            positions.add(position.moveRightDown());
        }
        if (position.canMoveRightUp()) {
            positions.add(position.moveRightUp());
        }
        return positions;
    }
}
