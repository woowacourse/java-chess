package chess.domain.piece;

import chess.domain.pieces.Color;
import chess.domain.position.Movement;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMove(Movement.DOWN_DOWN_RIGHT)) {
            positions.add(position.move(Movement.DOWN_DOWN_RIGHT));
        }
        if (position.canMove(Movement.DOWN_DOWN_LEFT)) {
            positions.add(position.move(Movement.DOWN_DOWN_LEFT));
        }
        if (position.canMove(Movement.UP_UP_LEFT)) {
            positions.add(position.move(Movement.UP_UP_LEFT));
        }
        if (position.canMove(Movement.UP_UP_RIGHT)) {
            positions.add(position.move(Movement.UP_UP_RIGHT));
        }
        if (position.canMove(Movement.LEFT_LEFT_DOWN)) {
            positions.add(position.move(Movement.LEFT_LEFT_DOWN));
        }
        if (position.canMove(Movement.LEFT_LEFT_UP)) {
            positions.add(position.move(Movement.LEFT_LEFT_UP));
        }
        if (position.canMove(Movement.RIGHT_RIGHT_DOWN)) {
            positions.add(position.move(Movement.RIGHT_RIGHT_DOWN));
        }
        if (position.canMove(Movement.RIGHT_RIGHT_UP)) {
            positions.add(position.move(Movement.RIGHT_RIGHT_UP));
        }
        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        return calculateCanMovePositions();
    }
}
