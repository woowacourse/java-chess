package chess.console.view;

import chess.console.GameManager;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.position.Row;
import chess.domain.position.StatusResult;
import chess.domain.board.Piece;
import java.util.Map;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Map<Position, Piece> board) {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Piece piece = board.get(new Position(column, row));
                System.out.print(PieceOutputText.of(piece));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printStatus(StatusResult status) {
        System.out.println("블랙 진영의 점수는 " + status.getBlackScore() + "입니다.");
        System.out.println("화이트 진영의 점수는 " + status.getWhiteScore() + "입니다.");

        printWinner(status.getResult());
    }

    private static void printWinner(Result result) {
        if (result.equals(Result.BLACK_WIN)) {
            System.out.println("블랙 진영이 이기고 있습니다.");
        }
        if (result.equals(Result.WHITE_WIN)) {
            System.out.println("화이트 진영이 이기고 있습니다.");
        }
        if (result.equals(Result.DRAW)) {
            System.out.println("무승부입니다.");
        }
    }

    public static void printBoard(GameManager gameManager) {
        printBoard(gameManager.getBoard());
    }

    public static void printStatus(GameManager gameManager) {
        printStatus(gameManager.getStatus());
    }
}
