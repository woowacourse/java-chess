package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.function.BiPredicate;

public class Queen extends Piece {

    private static final BiPredicate<Integer, Integer> invalidTargetCondition =
            Bishop.invalidTargetCondition.and(Rook.invalidTargetCondition);

    public Queen(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, invalidTargetCondition, true);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));

        return new Queen(teamColor, targetPosition);
    }
}
