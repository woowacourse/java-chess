package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Movement movement) {
        return movement.isDiagonal()
                || movement.isVertical()
                || movement.isHorizontal();
    }
}
