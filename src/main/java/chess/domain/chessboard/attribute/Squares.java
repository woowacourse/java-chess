package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

public class Squares {

    private final List<Square> squares;

    public Squares(final List<Square> squares) {
        this.squares = squares;
    }

    public static Squares empty() {
        return new Squares(Arrays.stream(File.values())
                .map(ignored -> Square.empty())
                .collect(Collectors.toList()));
    }

    public Square squareIn(final Position position) {
        File file = position.file();
        return squares.get(file.toColumn());
    }

    public boolean isEmpty(final Position position) {
        File file = position.file();
        Square square = squares.get(file.toColumn());
        return square.isEmpty();
    }

    public void remove(final Position position) {
        File file = position.file();
        squares.set(file.toColumn(), Square.empty());
    }

    public void put(final Position position, final Piece piece) {
        File file = position.file();
        squares.set(file.toColumn(), Square.from(piece));
    }

    public List<Square> getSquares() {
        return List.copyOf(squares);
    }
}
