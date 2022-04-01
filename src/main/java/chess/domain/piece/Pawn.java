package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Rank;
import java.util.List;

public class Pawn extends Piece {

    private static final int RANK_DIFFERENCE = 1;
    private static final double PAWN_SCORE = 1;
    public static final int INITIAL_MOVABLE_SCOPE = 2;

    public Pawn(Color color) {
        super(color, PAWN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        if (color == Color.WHITE && fromPosition.isSameRank(Rank.TWO)) {
            return direction == Direction.UP && toPosition.getRankDifference(fromPosition) <= INITIAL_MOVABLE_SCOPE;
        }
        if (color == Color.WHITE) {
            return direction == Direction.UP && toPosition.getRankDifference(fromPosition) == RANK_DIFFERENCE;
        }
        if (color == Color.BLACK && fromPosition.isSameRank(Rank.SEVEN)) {
            return direction == Direction.DOWN && toPosition.getRankDifference(fromPosition) >= -INITIAL_MOVABLE_SCOPE;
        }
        if (color == Color.BLACK) {
            return direction == Direction.DOWN && toPosition.getRankDifference(fromPosition) == -RANK_DIFFERENCE;
        }
        return false;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        if (color == Color.WHITE) {
            return List.of(Direction.RIGHTUP, Direction.LEFTUP).contains(direction)
                    && toPosition.getRankDifference(fromPosition) == RANK_DIFFERENCE;
        }
        return List.of(Direction.RIGHTDOWN, Direction.LEFTDOWN).contains(direction)
                && toPosition.getRankDifference(fromPosition) == -RANK_DIFFERENCE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
