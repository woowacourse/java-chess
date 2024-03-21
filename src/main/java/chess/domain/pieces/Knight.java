package chess.domain.pieces;

import chess.domain.Movement;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        int fileDiff = Math.abs(movement.getFileDifference());
        int rankDiff = Math.abs(movement.getRankDifference());

        return (fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2);
    }
}
