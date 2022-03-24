package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;

public class King extends Piece {

    public King(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> pieces, final Position targetPosition) {

        final int rankDifference = position.calculateDifferenceOfRank(targetPosition);
        final int fileDifference = position.calculateDifferenceOfFile(targetPosition);

        validateTargetPositionToMove(fileDifference, rankDifference);

        return new King(teamColor, targetPosition);
    }

    private void validateTargetPositionToMove(final int fileDifference, final int rankDifference) {
        if (fileDifference > 1 || rankDifference > 1) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }
}
