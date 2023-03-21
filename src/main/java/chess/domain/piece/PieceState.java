package chess.domain.piece;

import chess.domain.chessboard.SquareCoordinate;

import java.util.List;

public interface PieceState {

    boolean isEmpty();

    boolean isSameTeam(final Piece piece);
    boolean isSameTeam(final Team team);

    Team getTeam();

    List<SquareCoordinate> findRoute(final SquareCoordinate from, final SquareCoordinate to);

    void validateRoute(final List<PieceState> routeSquares);
}
