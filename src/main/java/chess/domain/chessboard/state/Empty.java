package chess.domain.chessboard.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.piece.Piece;
import java.util.List;

public final class Empty implements PieceState {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void canMove(final List<Square> routeSquares) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return false;
    }
}
