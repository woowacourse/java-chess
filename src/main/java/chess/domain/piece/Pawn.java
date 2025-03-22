package chess.domain.piece;

import chess.domain.pieces.Color;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected List<Position> calculateCanMovePositions() {
        final List<Position> positions = new ArrayList<>();
        if (color.isWhite()) {
            if (position.canMoveUp()) {
                positions.add(position.moveUp());
            }
            if (position.row() == Row.TWO && position.canMoveUp(2)) {
                positions.add(position.moveUp(2));
            }
        }
        if (color.isBlack()) {
            if (position.canMoveDown()) {
                positions.add(position.moveDown());
            }

            if (position.row() == Row.SEVEN && position.canMoveDown(2)) {
                positions.add(position.moveDown(2));
            }
        }
        return positions;
    }

    @Override
    protected List<Position> calculateCanTakePositions() {
        final List<Position> positions = new ArrayList<>();
        if (color.isWhite()) {
            if (position.canMoveRightUp()) {
                positions.add(position.moveRightUp());
            }
            if (position.canMoveLeftUp()) {
                positions.add(position.moveLeftUp());
            }
        }
        if (color.isBlack()) {
            if (position.canMoveRightDown()) {
                positions.add(position.moveRightDown());
            }
            if (position.canMoveLeftDown()) {
                positions.add(position.moveLeftDown());
            }
        }
        return positions;
    }
}
