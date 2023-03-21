package chess.domain.piece;

import chess.domain.chessboard.Coordinate;

import java.util.List;

public interface PieceState {

    boolean isEmpty();

    boolean isSameTeam(final Piece piece);

    Team getTeam();

    List<Coordinate> findRoute(final Coordinate from, final Coordinate to);

    void validateRoute(final List<PieceState> routeSquares);
}
