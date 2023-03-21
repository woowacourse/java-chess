package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;

import java.util.List;

public final class Knight extends Piece {

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

        if (!((fileDistance == 2 && rankDistance == 1) || (fileDistance == 1 && rankDistance == 2))) {
            throwCanNotMoveException();
        }
    }

    @Override
    public void validateRoute(final List<PieceState> routeSquares) {
        final int lastIndex = routeSquares.size() - 1;
        final PieceState lastSquare = routeSquares.get(lastIndex);

        if (lastSquare.isSameTeam(this)) {
            throwCanNotMoveException();
        }
    }
}
