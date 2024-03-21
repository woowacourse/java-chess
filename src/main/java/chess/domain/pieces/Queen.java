package chess.domain.pieces;

import chess.domain.Movement;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return movement.isCross() || movement.isDiagonal();
    }
}
