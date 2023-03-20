package chess.domain.chessboard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<Coordinate, Square> squares;

    public ChessBoard() {
        this.squares = ChessFactory.getInstance().create();
    }

    public void move(final Coordinate from, final Coordinate to) {
        final Square departure = squares.get(from);
        final Square arrival = squares.get(to);
        validateCanMove(from, to, departure);

        arrival.switchPieceState(departure);
        departure.switchPieceState(new Square());
    }

    private void validateCanMove(final Coordinate from, final Coordinate to, final Square departure) {
        final List<Coordinate> route = departure.findRoute(from, to);

        final List<Square> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public List<Square> getSquares() {
        return this.squares.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
