package chess.view;

import chess.game.Board;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.piece.detail.Name;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printBoard(final Board board) {
        printBoardRow(board.getValue());
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
            final Name value = piece.getName();
            final Color color = piece.getColor();
            printByColor(value, color);
            return;
        }
        System.out.print(".");
    }

    private static void printByColor(final Name value, final Color color) {
        String name = value.getName();
        if (color.isBlack()) {
            System.out.print(name);
            return;
        }
        System.out.print(name.toLowerCase());
    }

    public static void printScore(final Map<Color, Double> score) {
        for (final Color color : score.keySet()) {
            System.out.println(color + "의 점수는: " + score.get(color).intValue());
        }
    }
}
