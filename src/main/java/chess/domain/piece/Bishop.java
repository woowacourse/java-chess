package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.function.BiPredicate;

public class Bishop extends Piece {

    private static final double SCORE = 3;
    static final BiPredicate<Integer, Integer> movingCondition =
            (rankDifference, fileDifference) -> rankDifference == fileDifference;

    public Bishop(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, movingCondition, true);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));

        return new Bishop(teamColor, targetPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
