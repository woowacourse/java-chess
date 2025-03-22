package chess.domain.piece;

import chess.domain.pieces.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (position.canMoveDiagonal(i, i)) {
                positions.add(position.moveDiagonal(i, i));
            }
            if (position.canMoveDiagonal(i, -i)) {
                positions.add(position.moveDiagonal(i, -i));
            }
            if (position.canMoveDiagonal(-i, i)) {
                positions.add(position.moveDiagonal(-i, i));
            }
            if (position.canMoveDiagonal(-i, -i)) {
                positions.add(position.moveDiagonal(-i, -i));
            }
        }

        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        return calculateCanMovePositions();
    }
}
