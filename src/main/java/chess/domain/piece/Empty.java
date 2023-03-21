package chess.domain.piece;

import chess.domain.chessboard.Coordinate;

import java.util.List;

public final class Empty implements PieceState {

    private static final String EMPTY_EXCEPTION = "빈칸은 해당 동작을 수행할 수 없습니다.";

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return false;
    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }

    @Override
    public void validateRoute(final List<PieceState> routeSquares) {
        throw new UnsupportedOperationException(EMPTY_EXCEPTION);
    }
}
