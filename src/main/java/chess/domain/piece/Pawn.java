package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends Piece {

    private static final double PAWN_SCORE = 1;

    private final MoveStrategy moveStrategy;

    public Pawn(Color color) {
        super(color, PAWN_SCORE);
        this.moveStrategy = new PawnMoveStrategy();
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        if (color == Color.WHITE) {
            return moveStrategy.isMovable(fromPosition, toPosition)
                && Direction.judge(fromPosition, toPosition) == Direction.UP;
        }
        return moveStrategy.isMovable(fromPosition, toPosition)
            && Direction.judge(fromPosition, toPosition) == Direction.DOWN;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        if (color == Color.WHITE) {
            return moveStrategy.isCatchable(fromPosition, toPosition)
                && isWhiteCatchableDirection(Direction.judge(fromPosition, toPosition));
        }
        return moveStrategy.isCatchable(fromPosition, toPosition)
            && isBlackCatchableDirection(Direction.judge(fromPosition, toPosition));
    }

    private boolean isWhiteCatchableDirection(Direction direction) {
        return direction == Direction.LEFTUP || direction == Direction.RIGHTUP;
    }

    private boolean isBlackCatchableDirection(Direction direction) {
        return direction == Direction.LEFTDOWN || direction == Direction.RIGHTDOWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
