package chess.view;

import chess.domain.GameManager;
import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Result;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final String BLANK_PIECE = ".";

    private OutputView() {
    }

    public static void printBoard(final GameManager board) {
        for (int row : Row.valuesByDescending()) {
            printRow(board, row);
            System.out.println();
        }
    }

    private static void printRow(final GameManager board, final int row) {
        for (Column column : Column.values()) {
            Position position = Position.of(column.name() + row);
            printSymbol(board, position);
        }
    }

    private static void printSymbol(final GameManager board, final Position position) {
        final Optional<Piece> wrappedPiece = board.piece(position);
        if (wrappedPiece.isPresent()) {
            System.out.print(wrappedPiece.get().symbol());
            return;
        }
        System.out.print(BLANK_PIECE);
    }

    public static void announceNotStarted() {
        System.out.println("게임을 먼저 start 해주세요.");
    }

    public static void announceBadMoveCommand() {
        System.out.println("목적지와 출발지가 둘 다 필요합니다.");
    }

    public static void announceBadMovement(final String message) {
        System.out.println(message);
    }

    public static void printScore(final GameManager board) {
        for (Color color : Color.values()) {
            System.out.println(color.value() + "의 점수: " + board.calculateScore(color));
        }
        printResult(board.whoIsWin());
    }

    private static void printResult(final Map<Result, Color> gameResult) {
        if (gameResult.containsKey(Result.WIN)) {
            System.out.println(gameResult.get(Result.WIN).value() + "이 이기고 있습니다.");
            return;
        }
        System.out.println("현재 무승부입니다.");
    }
}
