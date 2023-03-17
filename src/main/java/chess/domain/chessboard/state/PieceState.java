package chess.domain.chessboard.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.piece.Piece;
import java.util.List;

public interface PieceState {

    boolean isEmpty();

    boolean isSameTeam(final Piece piece);

    Team getTeam();

    List<Coordinate> findRoute(final Coordinate from, final Coordinate to);

    void validateRoute(final List<Square> routeSquares);
}
