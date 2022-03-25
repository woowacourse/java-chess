package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.function.BiPredicate;

public class Rook extends Piece {

    static final BiPredicate<Integer, Integer> invalidTargetCondition =
            (rankDifference, fileDifference) -> fileDifference != 0 && rankDifference != 0;

    public Rook(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, invalidTargetCondition, false);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));

        return new Rook(teamColor, targetPosition);
    }
}

