package chess.view;

import chess.domain.Position;

import java.util.Map;

public class OutputView {
    private static final String CHESS_INIT_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String CHESS_START_GUIDE_MESSAGE = "> 게임 시작 : start";
    private static final String CHESS_END_GUIDE_MESSAGE = "> 게임 종료 : end";
    private static final String CHESS_MOVE_GUIDE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String BLACK_TEAM_SCORE = "블랙팀 점수: ";
    private static final String WHITE_TEAM_SCORE = "화이트팀 점수: ";
    public static final int BOARD_START = 0;
    public static final int BOARD_END = 7;

    private OutputView() {
    }

    public static void printChessStartMessage() {
        System.out.println(CHESS_INIT_MESSAGE);
        System.out.println(CHESS_START_GUIDE_MESSAGE);
        System.out.println(CHESS_END_GUIDE_MESSAGE);
        System.out.println(CHESS_MOVE_GUIDE_MESSAGE);
    }

    public static void printChessBoard(final Map<Position, String> chessBoard) {
        for (int row = BOARD_END; row >= BOARD_START; row--) {
            printChessBoardSingleLine(chessBoard, row);
            System.out.println();
        }
    }

    private static void printChessBoardSingleLine(final Map<Position, String> chessBoard, final int row) {
        for (int column = BOARD_START; column <= BOARD_END; column++) {
            System.out.print(chessBoard.getOrDefault(new Position(column, row), "."));
        }
    }

    public static void printChessGameResult(final double blackTeamScore, final double whiteTeamScore) {
        System.out.println(BLACK_TEAM_SCORE + blackTeamScore);
        System.out.println(WHITE_TEAM_SCORE + whiteTeamScore);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
