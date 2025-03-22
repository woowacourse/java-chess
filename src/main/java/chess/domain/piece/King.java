package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
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
