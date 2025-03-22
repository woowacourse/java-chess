package chess.piece;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(final Position position) {
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
        }

        return positions;
    }
}
