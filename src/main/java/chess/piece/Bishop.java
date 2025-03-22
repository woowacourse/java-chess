package chess.piece;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = -7; i <= 7; i++) {
            if (position.canMoveDiagonal(i, i)) {
                positions.add(position.moveDiagonal(i, i));
            }
            if (position.canMoveDiagonal(i, -i)) {
                positions.add(position.moveDiagonal(i, -i));
            }
        }

        return positions;
    }
}
