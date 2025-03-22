package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMoveUp()) {
            positions.add(position.moveUp());
        }
        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        final List<Position> positions = new ArrayList<>();
        if (position.canMoveRightUp()) {
            positions.add(position.moveRightUp());
        }
        if (position.canMoveLeftUp()) {
            positions.add(position.moveLeftUp());
        }
        return positions;
    }
}
