package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;
import chess.domain.square.Movement;

public class WhitePawn extends Piece {

    public WhitePawn() {
        super(Color.WHITE, Type.PAWN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        if (hasDestinationPiece(destinationPiece)) {
            return isAttack(movement);
        }
        return isMove(movement);
    }

    private boolean hasDestinationPiece(final Piece destinationPiece) {
        return destinationPiece != null;
    }

    private boolean isAttack(final Movement movement) {
        return movement.isDiagonal() && movement.getRankDifference() == 1;
    }

    private boolean isMove(final Movement movement) {
        if (isInitialSquare(movement)) {
            return isFirstMove(movement) || isDefaultMove(movement);
        }
        return isDefaultMove(movement);
    }

    private boolean isInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == 1;
    }

    private boolean isFirstMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == 2;
    }

    private boolean isDefaultMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == 1;
    }
}
