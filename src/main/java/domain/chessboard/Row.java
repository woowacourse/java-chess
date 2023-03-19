package domain.chessboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Row {

    private final List<Square> squares;

    private Row(final List<Square> squares) {
        this.squares = squares;
    }

    public static Row from(final RowFactory rowFactory) {
        return new Row(rowFactory.getSquareStatus()
                .stream()
                .map(Square::new)
                .collect(Collectors.toList()));
    }

    public List<Square> getRow() {
        return new ArrayList<>(squares);
    }

    public Square getSquare(final int coordinateX) {
        return squares.get(coordinateX);
    }

}
