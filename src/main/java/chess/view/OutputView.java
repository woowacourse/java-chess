package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final Map<Position, Piece> pieces) {
        for (final Row row : Row.getRowsReverseOrder()) {
            printBoardInRow(pieces, row);
            System.out.println();
        }
        System.out.println();
    }

    private static void printBoardInRow(final Map<Position, Piece> pieces, final Row row) {
        for (Column column : Column.values()) {
            final Position position = Position.of(column, row);
            final Piece piece = pieces.get(position);
            final String symbol = getSymbolByColor(piece);
            System.out.print(symbol);
        }
    }

    private static String getSymbolByColor(final Piece piece) {
        final Symbol symbol = piece.getSymbol();
        final Color color = piece.getColor();
        final String value = symbol.getValue();

        if (color == Color.WHITE) {
            return value.toLowerCase(Locale.ROOT);
        }
        return value;
    }

    public static void printReplay(final String message) {
        System.out.println(message);
        System.out.println("다시 입력 바랍니다." + System.lineSeparator());
    }

    public static void printStatus(final Map<Color, Double> scores) {
        for (Color color : scores.keySet()) {
            System.out.println(color.name() + " : " + scores.get(color));
        }
    }

    public static void printWinner(final Color color) {
        System.out.println(color.name() + "팀이 이겼습니다.");
    }
}
