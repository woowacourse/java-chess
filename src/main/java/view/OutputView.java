package view;

import domain.Board;
import domain.Line;
import domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {


    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<Line> lines = board.getLines();
        lines.forEach(line -> stringBuilder.append(makeLine(line)).append("\n"));
        System.out.println(stringBuilder);
    }

    private static String makeLine(final Line line) {
        return line.getSquares()
            .stream()
            .map(square -> makePieceSign(square.getPiece()))
            .collect(Collectors.joining());
    }

    private static String makePieceSign(final Piece piece) {
        if (piece == null) {
            return ".";
        }
        return PieceView.findSign(piece);
    }
}
