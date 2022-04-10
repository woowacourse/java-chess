package chess.console.view;

import chess.domain.Color;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;
import java.util.Optional;

public final class OutputView {

    private OutputView() {
    }

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start" +
                "\n> 게임 종료 : end" +
                "\n> 말 이동 : move source위치 target위치 - 예. move a2 a3" +
                "\n> 진행 상황 출력 : status");
    }

    public static void printBoard(final Board board) {
        for (int row : Row.valuesByDescending()) {
            printRow(board, row);
            System.out.println();
        }
    }

    private static void printRow(Board board, int row) {
        for (Column column : Column.values()) {
            Position position = Position.of(column.name() + row);
            printSymbol(board, position);
        }
    }

    private static void printSymbol(Board board, Position position) {
        Optional<Piece> wrappedPiece = board.findPiece(position);
        if (wrappedPiece.isPresent()) {
            System.out.print(wrappedPiece.get().symbol());
            return;
        }
        System.out.print(".");
    }

    public static void announceNotStarted() {
        System.out.println("게임을 먼저 start 해주세요.");
    }

    public static void announceWrongMoveCommands() {
        System.out.println("> 이동은 move source위치 target위치 형태로 입력하세요.\n> 예. move a2 a3");
    }

    public static void announceBadMovement(String message) {
        System.out.println(message);
    }

    public static void printScoreAndResult(Board board) {
        printScore(board);
        printResult(board.gameResult());
    }

    private static void printScore(Board board) {
        for (Color color : Color.values()) {
            System.out.println(color.text() + "의 점수: " + board.calculateScore(color));
        }
    }

    private static void printResult(Map<Result, Color> gameResult) {
        if (gameResult.containsKey(Result.WIN)) {
            System.out.println(gameResult.get(Result.WIN).text() + "이 이기고 있습니다.");
            return;
        }
        System.out.println("현재 무승부입니다.");
    }

    public static void printFinalResult(Board board) {
        printScore(board);
        System.out.println(board.winnersColor().text() + "이 승리했습니다.");
    }
}
