package chess.view;

import chess.domain.game.Game;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.machine.Result;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Map;
import java.util.Optional;

public class OutputView {

    private static final String BLANK_PIECE = ".";

    private OutputView() {
    }

    public static void printBoard(final Game game) {
        for (int row : Row.valuesByDescending()) {
            printRow(game, row);
            System.out.println();
        }
    }

    private static void printRow(final Game game, final int row) {
        for (Column column : Column.values()) {
            Position position = Position.of(column.name() + row);
            printSymbol(game, position);
        }
    }

    private static void printSymbol(final Game board, final Position position) {
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

    public static void printScore(final Game board) {
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

    public static void announceCanNotRestart() {
        System.out.println("게임을 종료하고 다시 시작해주세요.");
    }
}
