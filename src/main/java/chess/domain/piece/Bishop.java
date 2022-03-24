package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> pieces, final Position targetPosition) {
        final int rankDifference = position.calculateDifferenceOfRank(targetPosition);
        final int fileDifference = position.calculateDifferenceOfFile(targetPosition);

        validateTargetPositionToMove(fileDifference, rankDifference);
        checkOtherPiecesInPathToTarget(position, targetPosition, pieces);

        return new Bishop(teamColor, targetPosition);
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

    private void validateTargetPositionToMove(final int fileDifference, final int rankDifference) {
        if (fileDifference != rankDifference) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }
}
