package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Line {

    private static final int LINE_MAX_COUNT = 8;
    private static final List<Piece> BACK_LINE_ORDER = List.of(Piece.ROOK, Piece.KNIGHT, Piece.BISHOP, Piece.QUEEN,
        Piece.KING, Piece.BISHOP, Piece.KNIGHT, Piece.ROOK);
    private final List<Square> squares;

    public Line(final List<Square> squares) {
        this.squares = squares;
    }

    public static Line blackFront() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(i -> new Square(ChessPiece.makeBlack(Piece.PAWN)))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line whiteFront() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(i -> new Square(ChessPiece.makeWhite(Piece.PAWN)))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line blackBack() {
        final List<Square> squares = BACK_LINE_ORDER.stream()
            .map(piece -> new Square(ChessPiece.makeBlack(piece)))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line whiteBack() {
        final List<Square> squares = BACK_LINE_ORDER.stream()
            .map(piece -> new Square(ChessPiece.makeWhite(piece)))
            .collect(Collectors.toList());
        return new Line(squares);
    }

    public static Line empty() {
        final List<Square> squares = IntStream.range(0, LINE_MAX_COUNT)
            .mapToObj(i -> Square.empty())
            .collect(Collectors.toList());
        return new Line(squares);
    }
}
