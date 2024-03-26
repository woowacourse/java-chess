package chess.domain.piece.type;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Movement movement) {
        return movement.isVertical() || movement.isHorizontal();
    }
}
