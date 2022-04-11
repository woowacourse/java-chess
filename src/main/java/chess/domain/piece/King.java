package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class King extends UnpromotablePiece {

    private static final String NAME = "King";
    private static final String SYMBOL = "k";
    private static final double SCORE = 0;

    private static final BiPredicate<Integer, Integer> MOVEMENT_STRATEGY =
            (rankMove, fileMove) -> Math.abs(rankMove) <= 1 && Math.abs(fileMove) <= 1;

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position sourcePosition,
                           final Position targetPosition,
                           final List<Position> otherPositions) {
        return sourcePosition.canMove(targetPosition, MOVEMENT_STRATEGY);
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
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
