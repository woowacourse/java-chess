package chess.domain.piece;

import chess.domain.Movement;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        if (hasDestinationPiece(destinationPiece)) {
            return isAttack(movement);
        }
        if (isSameColor(Color.WHITE)) {
            return isWhiteMove(movement);
        }
        return isBlackMove(movement);
    }

    private boolean hasDestinationPiece(final Piece destinationPiece) {
        if (destinationPiece != null) {
            return true;
        }
        return false;
    }

    private boolean isAttack(final Movement movement) {
        if (isSameColor(Color.WHITE)) {
            return isWhiteAttack(movement);
        }
        return isBlackAttack(movement);
    }

    private boolean isWhiteAttack(final Movement movement) {
        return movement.isDiagonal() && movement.getRankDifference() == 1;
    }

    private boolean isBlackAttack(final Movement movement) {
        return movement.isDiagonal() && movement.getRankDifference() == -1;
    }

    private boolean isWhiteMove(final Movement movement) {
        if (isWhiteInitialSquare(movement)) {
            return isWhiteFirstMove(movement) || isWhiteDefaultMove(movement);
        }
        return isWhiteDefaultMove(movement);
    }

    private boolean isWhiteInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == 1;
    }

    private boolean isWhiteFirstMove(final Movement movement) {
        return movement.isStraight() && movement.getRankDifference() == 2;
    }

    private boolean isWhiteDefaultMove(final Movement movement) {
        return movement.isStraight() && movement.getRankDifference() == 1;
    }

    private boolean isBlackMove(final Movement movement) {
        if (isBlackInitialSquare(movement)) {
            return isBlackFirstMove(movement) || isBlackDefaultMove(movement);
        }
        return isBlackDefaultMove(movement);
    }

    private boolean isBlackInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == 6;
    }

    private boolean isBlackFirstMove(final Movement movement) {
        System.out.println(movement.isStraight());
        System.out.println(movement.getRankDifference() == -2);
        return movement.isStraight() && movement.getRankDifference() == -2;
    }

    private boolean isBlackDefaultMove(final Movement movement) {
        return movement.isStraight() && movement.getRankDifference() == -1;
    }
}
