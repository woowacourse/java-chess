package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(final Position position) {
        super(position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (position.canMoveVertical(i)) {
                positions.add(position.moveVertical(i));
            }
            if (position.canMoveHorizontal(i)) {
                positions.add(position.moveHorizontal(i));
            }

            if (position.canMoveVertical(-i)) {
                positions.add(position.moveVertical(-i));
            }
            if (position.canMoveHorizontal(-i)) {
                positions.add(position.moveHorizontal(-i));
            }
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
}
