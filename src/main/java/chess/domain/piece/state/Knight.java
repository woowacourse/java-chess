package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import java.util.List;

public final class Knight extends Piece {

    private static final int TWO_STEP = 2;
    private static final int ONE_STEP = 1;

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        validatePossibleDestination(from, to);
        return List.of(to);
    }

    private void validatePossibleDestination(final Coordinate from, final Coordinate to) {
        final int fileDistance = Math.abs(from.calculateFileDistance(to));
        final int rankDistance = Math.abs(from.calculateRankDistance(to));

        if (isCanNotMoveDirection(fileDistance, rankDistance)) {
            throwCanNotMoveException();
        }
    }

    private boolean isCanNotMoveDirection(final int fileDistance, final int rankDistance) {
        return (isTwoFileOneRank(fileDistance, rankDistance) && isOneFileTwoRank(fileDistance,
                rankDistance));
    }

    private boolean isTwoFileOneRank(final int fileDistance, final int rankDistance) {
        return !(fileDistance == TWO_STEP && rankDistance == ONE_STEP);
    }

    private static boolean isOneFileTwoRank(final int fileDistance, final int rankDistance) {
        return !(fileDistance == ONE_STEP && rankDistance == TWO_STEP);
    }

    @Override
    public void validateRoute(final List<PieceState> pieceRoute) {
        final int lastIndex = pieceRoute.size() - 1;
        final PieceState lastPieceState = pieceRoute.get(lastIndex);
        if (lastPieceState.isSameTeam(this)) {
            throwCanNotMoveException();
        }
    }
}
