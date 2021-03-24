package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.Objects;

public class OutputView {
    private static final String EMPTY_FIELD = ".";
    private static final int LINE_COUNT = 8;
    private static final String STATUS_MESSAGE = "BLACK 팀의 점수 : %f, WHITE 팀의 점수 : %f" + System.lineSeparator();
    private static final String BLACK_WIN = "BLACK 팀이 유리한 상황입니다.";
    private static final String WHITE_WIN = "WHITE 팀이 유리한 상황입니다.";
    private static final String DRAW = "동등한 상황입니다.";

    public static void printManual() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 점수 상황 : status");
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
        System.out.println();
    }

    public static void printStatus(double blackStatus, double whiteStatus) {
        System.out.printf(STATUS_MESSAGE, blackStatus, whiteStatus);
        System.out.println(calculateCurrentWinningTeam(blackStatus, whiteStatus));
    }

    private static String calculateCurrentWinningTeam(double blackStatus, double whiteStatus) {
        if (blackStatus > whiteStatus) {
            return BLACK_WIN;
        }
        if (blackStatus < whiteStatus) {
            return WHITE_WIN;
        }
        return DRAW;
    }
}
