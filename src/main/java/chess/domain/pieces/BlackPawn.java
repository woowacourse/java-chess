package chess.domain.pieces;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;
import chess.domain.square.Movement;

public class BlackPawn extends Piece {

    private static final int INITIAL_BLACK_PAWN_RANK = 6;
    private static final int DEFAULT_BLACK_PAWN_MOVE = -1;
    private static final int INITIAL_BLACK_PAWN_MOVE = -2;

    public BlackPawn() {
        super(Color.BLACK, Type.PAWN);
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
        return movement.isDiagonal() && movement.getRankDifference() == DEFAULT_BLACK_PAWN_MOVE;
    }

    private boolean isMove(final Movement movement) {
        if (isInitialSquare(movement)) {
            return isFirstMove(movement) || isDefaultMove(movement);
        }
        return isDefaultMove(movement);
    }

    private boolean isInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == INITIAL_BLACK_PAWN_RANK;
    }

    private boolean isFirstMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == INITIAL_BLACK_PAWN_MOVE;
    }

    private boolean isDefaultMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == DEFAULT_BLACK_PAWN_MOVE;
    }
}
