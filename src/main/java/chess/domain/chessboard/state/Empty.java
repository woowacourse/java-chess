package chess.domain.chessboard.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.piece.Piece;
import java.util.List;

public final class Empty implements PieceState {

    private static final String EMPTY_EXCEPTION = "빈칸은 해당 동작을 수행할 수 없습니다.";

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }

    @Override
    public void canMove(final List<Square> routeSquares) {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return false;
    }
}
