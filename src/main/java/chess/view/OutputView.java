package chess.view;

import chess.game.Board;
import chess.game.Column;
import chess.game.Position;
import chess.game.Row;
import chess.piece.Color;
import chess.piece.Name;
import chess.piece.Piece;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String EMPTY_PIECE = ".";

    public static void printBoard(final Board board) {
        printBoardRow(board.getValue());
    }

    public static void printScore(final Map<Color, Double> score) {
        for (final Color color : score.keySet()) {
            System.out.println(MessageFormat.format("{0}팀 스코어 : {1}점", color, score.get(color)));
        }
    }

    public static void printGameEnd(final Color winColor) {
        System.out.println("게임이 종료되었습니다.");
        System.out.println(winColor + "(이)가 승리했습니다.");
    }

    private static void printBoardRow(final Map<Position, Piece> board) {
        final List<Row> rows = Arrays.asList(Row.values());
        Collections.reverse(rows);
        for (final Row row : rows) {
            printBoardColumn(board, row);
            System.out.println();
        }
    }

    private static void printBoardColumn(final Map<Position, Piece> board, final Row row) {
        for (final Column column : Column.values()) {
            printPiece(board, row, column);
        }
    }

    private static void printPiece(final Map<Position, Piece> board, final Row row, final Column column) {
        final Position position = Position.of(column, row);
        if (board.containsKey(position)) {
            final Piece piece = board.get(position);
            printByColor(piece.getName(), piece.getColor());
            return;
        }
        System.out.print(EMPTY_PIECE);
    }

    private static void printByColor(final Name value, final Color color) {
        final String name = value.getName();
        if (color.isBlack()) {
            System.out.print(name);
            return;
        }
        System.out.print(name.toLowerCase());
    }
}
