package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;
import chess.domain.square.Movement;

public class King extends Piece {

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return (movement.isCross() || movement.isDiagonal()) && movement.findDistance() == 1;
    }
}
