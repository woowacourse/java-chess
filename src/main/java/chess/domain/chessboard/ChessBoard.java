package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<Coordinate, PieceState> squares;

    public ChessBoard() {
        this.squares = ChessFactory.getInstance().create();
    }

    public void move(final Coordinate from, final Coordinate to) {
        final PieceState departure = squares.get(from);
//        final PieceState arrival = squares.get(to);
        validateCanMove(from, to, departure);

        squares.replace(to, departure);
        squares.replace(from, new Empty());
//        arrival.switchPieceState(departure);
//        departure.switchPieceState(new Square());
    }

    private void validateCanMove(final Coordinate from, final Coordinate to, final PieceState departure) {
        final List<Coordinate> route = departure.findRoute(from, to);

        final List<PieceState> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public List<PieceState> getSquares() {
        return this.squares.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
