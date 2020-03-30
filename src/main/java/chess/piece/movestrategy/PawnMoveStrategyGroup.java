package chess.piece.movestrategy;

import chess.coordinate.Vector;
import chess.piece.Piece;

import java.util.Arrays;

public enum PawnMoveStrategyGroup {
    STRAIGHT(new StraightPawnMoveStrategy()),
    DIAGONAL(new DiagonalPawnMoveStrategy()),
    NOTHING(new CanNotMoveStrategy());

    private final PawnMoveStrategy pawnMoveStrategy;

    PawnMoveStrategyGroup(PawnMoveStrategy pawnMoveStrategy) {
        this.pawnMoveStrategy = pawnMoveStrategy;
    }

    public static boolean movable(Vector vector, Piece targetPiece) {
        return Arrays.stream(values())
                .filter(aStrategy -> aStrategy.support(vector))
                .findFirst()
                .orElse(NOTHING)
                .pawnMoveStrategy.movable(vector, targetPiece);
    }

    private boolean support(Vector vector) {
        return this.pawnMoveStrategy.support(vector);
    }

    private static class CanNotMoveStrategy implements PawnMoveStrategy {
        @Override
        public boolean support(Vector vector) {
            return false;
        }

        @Override
        public boolean movable(Vector vector, Piece targetPiece) {
            return false;
        }
    }
}
