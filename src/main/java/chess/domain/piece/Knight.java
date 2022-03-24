package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.List;

public class Knight extends Piece {

    public Knight(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public Piece move(final List<Piece> pieces, final Position targetPosition) {
        final int rankDifference = position.calculateDifferenceOfRank(targetPosition);
        final int fileDifference = position.calculateDifferenceOfFile(targetPosition);

        validateTargetPositionToMove(fileDifference, rankDifference);

        return new Knight(teamColor, targetPosition);
    }

    private void validateTargetPositionToMove(final int fileDifference, final int rankDifference) {
        if ((fileDifference == 2 && rankDifference != 1) || (fileDifference == 1 && rankDifference != 2)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }
}
