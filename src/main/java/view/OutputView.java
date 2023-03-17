package view;

import domain.Board;
import domain.Line;
import domain.piece.Piece;
import domain.type.PieceType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {


    private static final String EMPTY_SIGN = ".";

    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<Line> lines = board.getLines();
        IntStream.iterate(lines.size() - 1, order -> order >= 0, order -> order - 1)
            .forEach(order -> stringBuilder.append(makeLine(lines.get(order))).append(System.lineSeparator()));
        System.out.println(stringBuilder);
    }

    private static String makeLine(final Line line) {
        return line.getSquares()
            .stream()
            .map(square -> makePieceSign(square.getPiece()))
            .collect(Collectors.joining());
    }

    private static String makePieceSign(final Piece piece) {
        if (piece.isSameType(PieceType.EMPTY)) {
            return EMPTY_SIGN;
        }
        return PieceView.findSign(piece);
    }
}
