package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        System.out.println(BoardConverter.convert(board));
    }

    public static void printGameEnd() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public static void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
