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
    public Piece move(final List<Piece> pieces, final Position targetPosition) {
        position.validateTargetPosition(targetPosition, invalidTargetCondition, false);
        checkOtherPiecesInPathToTarget(position, targetPosition, pieces);

        return new Rook(teamColor, targetPosition);
    }

    private void checkOtherPiecesInPathToTarget(final Position currentPosition, final Position targetPosition,
                                                final List<Piece> pieces) {
        if (currentPosition == targetPosition) {
            return;
        }
        if (pieces.stream().anyMatch(piece -> piece.hasPosition(currentPosition))) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
        checkOtherPiecesInPathToTarget(currentPosition.nextPosition(targetPosition), targetPosition, pieces);
    }
}

