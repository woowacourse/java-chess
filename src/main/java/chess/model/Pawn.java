package chess.model;

import java.util.List;

public class Pawn extends Piece {
    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    public Pawn(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        if (targetPiece != null && isSameSide(targetPiece)) {
            throw new IllegalArgumentException("아군X");
        }
        Distance distance = target.calculateDistance(source);
        if (targetPiece != null && !isPossibleDiagonal(distance)) {
            throw new IllegalArgumentException("앞에 어떤 기물이든 안됨");
        }
        if (canCrossMove(source, distance) || canDiagonalMove(targetPiece, distance)) {
            return distance.findPath(source);
        }
        return List.of();
    }

    private boolean canDiagonalMove(Piece targetPiece, Distance distance) {
        return isPossibleDiagonal(distance) && targetPiece != null && !isSameSide(targetPiece);
    }

    private boolean isPossibleDiagonal(Distance distance) {
        return distance.isDiagonalMovement() && distance.hasSame(DISPLACEMENT);
    }

    private boolean canCrossMove(ChessPosition source, Distance distance) {
        if (source.isPawnInitialPosition(side)) {
            return canMoveForwardWith(distance, DISPLACEMENT) ||
                    canMoveForwardWith(distance, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(distance, DISPLACEMENT);
    }

    private boolean canMoveForwardWith(Distance distance, int displacement) {
        return distance.isForward(side) && distance.hasSame(displacement);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }
}
