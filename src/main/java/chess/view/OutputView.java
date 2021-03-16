package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printManual() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3;");
    }

    public static void printBoard(Board board) {
        int cnt = 0;
        StringBuilder builder = new StringBuilder("");

        for (Piece piece : board.getBoard().values()) {
            cnt++;
            if (piece == null) {
                builder.append(".");
                if (cnt % 8 == 0) {
                    System.out.println(builder.toString());
                    builder = new StringBuilder("");
                }
                continue;
            }
            builder.append(piece.getName());
            if (cnt % 8 == 0) {
                System.out.println(builder.toString());
                builder = new StringBuilder("");
            }
        }
        System.out.println();
    }
}
