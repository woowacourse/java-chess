package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Bishop extends UnpromotablePiece {

    static final String SYMBOL = "b";
    private static final double SCORE = 3;

    static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) -> Math.abs(rankMove) == Math.abs(fileMove);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position sourcePosition,
                           final Position targetPosition,
                           final List<Position> otherPositions) {
        return sourcePosition.canMove(targetPosition, movingCondition) &&
                !sourcePosition.isOtherPieceInPathToTarget(targetPosition, otherPositions);
    }

    @Override
    public String getSymbol() {
        if (team.isBlack()) {
            return SYMBOL.toUpperCase();
        }
        return SYMBOL;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
