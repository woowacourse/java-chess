package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Bishop extends UnpromotablePiece {

    private static final String NAME = "Bishop";
    static final String SYMBOL = "b";
    private static final double SCORE = 3;

    private static final BiPredicate<Integer, Integer> MOVEMENT_STRATEGY =
            (rankMove, fileMove) -> Math.abs(rankMove) == Math.abs(fileMove);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position sourcePosition,
                           final Position targetPosition,
                           final List<Position> otherPositions) {
        return sourcePosition.canMove(targetPosition, MOVEMENT_STRATEGY) &&
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

    @Override
    public String getName() {
        return NAME;
    }
}
