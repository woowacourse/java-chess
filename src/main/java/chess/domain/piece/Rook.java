package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class Rook extends UnpromotablePiece {

    static final String SYMBOL = "r";
    private static final double SCORE = 5;

    static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) -> fileMove == 0 || rankMove == 0;

    public Rook(final Team team) {
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

