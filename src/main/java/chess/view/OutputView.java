package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.Objects;

public class OutputView {
    private static final String EMPTY_FIELD = ".";
    private static final int LINE_COUNT = 8;
    private static final String COMMAND_MESSAGE = "BLACK 팀의 점수 : %f, WHITE 팀의 점수 : %f %s";
    private static final String BLACK_WIN = "BLACK 팀이 유리한 상황입니다.";
    private static final String WHITE_WIN = "WHITE 팀이 유리한 상황입니다.";
    private static final String DRAW = "동등한 상황입니다.";

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

    public static void printStatus(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            System.out.printf(COMMAND_MESSAGE + "%n", blackScore, whiteScore, BLACK_WIN);
        }
        if (blackScore < whiteScore) {
            System.out.printf(COMMAND_MESSAGE + "%n", blackScore, whiteScore, WHITE_WIN);
        }
        if (blackScore == whiteScore) {
            System.out.printf(COMMAND_MESSAGE + "%n", blackScore, whiteScore, DRAW);
        }
    }
}
