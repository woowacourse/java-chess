package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.function.BiPredicate;

public class King extends Piece {

    private static final BiPredicate<Integer, Integer> invalidTargetCondition =
            (rankDifference, fileDifference) -> rankDifference > 1 || fileDifference > 1;

    public King(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, invalidTargetCondition, true);
        return new King(teamColor, targetPosition);
    }
}
