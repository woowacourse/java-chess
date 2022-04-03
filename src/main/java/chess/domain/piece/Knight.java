package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Knight extends UnpromotablePiece {

    static final String SYMBOL = "n";
    private static final double SCORE = 2.5;

    private static final BiPredicate<Integer, Integer> movingCondition = (rankMove, fileMove) ->
            (Math.abs(fileMove) == 2 && Math.abs(rankMove) == 1) ||
                    (Math.abs(fileMove) == 1 && Math.abs(rankMove) == 2);

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position sourcePosition,
                           final Position targetPosition,
                           final List<Position> otherPositions) {
        return sourcePosition.canMove(targetPosition, movingCondition);
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
