package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;
import chess.domain.square.Movement;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece target) {
        return movement.isDiagonal();
    }
}
