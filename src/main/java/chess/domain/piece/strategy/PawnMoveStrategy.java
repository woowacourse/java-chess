package chess.domain.piece.strategy;

import chess.domain.piece.info.*;

public class PawnMoveStrategy implements MoveStrategy {
    private static final String PAWN_ERROR = "[ERROR] 폰 이동 규칙에 어긋납니다.";
    private final Color color;

    public PawnMoveStrategy(Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isAttackAble(source, target)) {
            return true;
        }
        Direction pawnDirection = Cross.findCrossByTwoPosition(source, target);
        if (isMoveAble(source, target, pawnDirection)) {
            validateFirstTurnIfTwoDistance(source, target);
            return true;
        }
        throw new IllegalArgumentException(PAWN_ERROR);
    }

    public boolean isAttackAble(Position source, Position target) {
        if (this.color == Color.BLACK) {
            return Math.abs(source.subtractX(target)) == 1 && source.subtractY(target) == 1;
        }
        if (this.color == Color.WHITE) {
            return Math.abs(source.subtractX(target)) == 1 && source.subtractY(target) == -1;
        }
        return false;
    }

    private boolean isMoveAble(Position source, Position target, Direction cross) {
        if (this.color == Color.BLACK) {
            return (cross == Cross.DOWN) && (source.subtractY(target) <= 2);
        }
        if (this.color == Color.WHITE) {
            return (cross == Cross.UP) && (target.subtractY(source) <= 2);
        }
        return false;
    }

    private void validateFirstTurnIfTwoDistance(Position source, Position target) {
        if (this.color == Color.BLACK && (!(source.isFirstTurnIfPawn(color)) && source.subtractY(target) == 2)) {
            throw new IllegalArgumentException(PAWN_ERROR);
        }
        if (this.color == Color.WHITE && (!(source.isFirstTurnIfPawn(color)) && target.subtractY(source) == 2)) {
            throw new IllegalArgumentException(PAWN_ERROR);
        }
    }
}
