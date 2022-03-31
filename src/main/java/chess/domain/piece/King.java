package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;
import java.util.function.BiPredicate;

public class King extends Piece {

    private static final double SCORE = 0;
    private static final BiPredicate<Integer, Integer> movingCondition =
            (rankMove, fileMove) -> Math.abs(rankMove) <= 1 && Math.abs(fileMove) <= 1;

    public King(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition);
        return new King(teamColor, targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
