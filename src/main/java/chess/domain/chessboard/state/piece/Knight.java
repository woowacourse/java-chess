package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.Team;
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
    public void canMove(final List<Square> routeSquares) {
        final int lastIndex = routeSquares.size() - 1;
        final Square lastSquare = routeSquares.get(lastIndex);
        if (lastSquare.isSameTeam(this)) {
            throwCanNotMoveException();
        }
    }
}
