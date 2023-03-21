package domain.board;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Line {

    private static final int LINE_MAX_COUNT = 8;
    private static final List<Piece> BLACK_PIECES = List.of(Rook.makeBlack(), Knight.makeBlack(), Bishop.makeBlack(),
        Queen.makeBlack(), King.makeBlack(), Bishop.makeBlack(), Knight.makeBlack(), Rook.makeBlack());
    private static final List<Piece> WHITE_PIECES = List.of(Rook.makeWhite(), Knight.makeWhite(), Bishop.makeWhite(),
        Queen.makeWhite(), King.makeWhite(), Bishop.makeWhite(), Knight.makeWhite(), Rook.makeWhite());
    private final List<Square> squares;

    private Line(final List<Square> squares) {
        this.squares = squares;
    }

    public static Line blackFront() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(i -> new Square(Pawn.makeBlack()))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line whiteFront() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(i -> new Square(Pawn.makeWhite()))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line blackBack() {
        final List<Square> squares = BLACK_PIECES.stream()
            .map(Square::new)
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line whiteBack() {
        final List<Square> squares = WHITE_PIECES.stream()
            .map(Square::new)
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line empty() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(count -> Square.empty())
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public Square getByCol(final int col) {
        return squares.get(col);
    }

    public List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Line line = (Line) o;
        return squares.equals(line.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
