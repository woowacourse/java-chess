package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Rook extends UnpromotablePiece {

    private static final String NAME = "Rook";
    static final String SYMBOL = "r";
    private static final double SCORE = 5;

    private static final BiPredicate<Integer, Integer> MOVEMENT_STRATEGY =
            (rankMove, fileMove) -> rankMove == 0 || fileMove == 0;

    public Rook(final Team team) {
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

