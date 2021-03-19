package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.Objects;

public class OutputView {
    private static final String EMPTY_FIELD = ".";
    private static final int LINE_COUNT = 8;

    public static void printManual() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3;");
    }

    public static void printBoard(Board board) {
        int cnt = 0;
        StringBuilder builder = new StringBuilder();

        for (Piece piece : board.getBoard().values()) {
            builder = combinePieceNameToLine(piece, ++cnt, builder);
        }
        System.out.println();
    }

    private static StringBuilder printLineWhenLineIsFull(int cnt, StringBuilder builder) {
        if (cnt % LINE_COUNT == 0) {
            System.out.println(builder.toString());
            return new StringBuilder();
        }
        return builder;
    }

    private static StringBuilder combinePieceNameToLine(Piece piece, int cnt, StringBuilder builder) {
        if (Objects.isNull(piece)) {
            builder.append(EMPTY_FIELD);
            return printLineWhenLineIsFull(cnt, builder);
        }
        builder.append(piece.getName());
        return printLineWhenLineIsFull(cnt, builder);
    }

    public static void printMessage(String commandMessage) {
        System.out.println(commandMessage);
    }
}
