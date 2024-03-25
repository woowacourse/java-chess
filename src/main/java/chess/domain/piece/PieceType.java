package chess.domain.piece;

import chess.domain.square.Square;

import java.util.function.BiPredicate;

public enum PieceType {

    KING((source, target) -> (source.isStraight(target) || source.isDiagonal(target))
            && source.isWithinOneStep(target)),
    QUEEN((source, target) -> source.isStraight(target) || source.isDiagonal(target)),
    ROOK((source, target) -> source.isStraight(target)),
    BISHOP((source, target) -> source.isDiagonal(target)),
    KNIGHT((source, target) -> source.isStraightAndDiagonal(target)),
    PAWN((source, target) -> source.isOnlyForward(target) || source.isAttack(target)),
    ;

    private final BiPredicate<Square, Square> moveStrategy;

    PieceType(final BiPredicate<Square, Square> moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public boolean findMoveStrategy(final Square source, final Square target) {
        return moveStrategy.test(source, target);
    }
}
