package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.function.BiPredicate;

public class Knight extends Piece {

    private static final double SCORE = 2.5;
    private static final BiPredicate<Integer, Integer> movingCondition =
            (rankDifference, fileDifference) ->
                    (fileDifference == 2 && rankDifference == 1) || (fileDifference == 1 && rankDifference == 2);

    public Knight(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition, true);
        return new Knight(teamColor, targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
