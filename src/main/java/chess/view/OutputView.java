package chess.view;

import chess.domain.Position;

import java.util.Map;

public class OutputView {
    private static final String CHESS_INIT_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String CHESS_START_GUIDE_MESSAGE = "> 게임 시작 : start";
    private static final String CHESS_END_GUIDE_MESSAGE = "> 게임 종료 : end";
    private static final String CHESS_MOVE_GUIDE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    private OutputView() {
    }

    public static void printChessStartMessage() {
        System.out.println(CHESS_INIT_MESSAGE);
        System.out.println(CHESS_START_GUIDE_MESSAGE);
        System.out.println(CHESS_END_GUIDE_MESSAGE);
        System.out.println(CHESS_MOVE_GUIDE_MESSAGE);
    }

    public static void printChessBoard(final Map<Position, String> chessBoard) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoard.getOrDefault(new Position(j, i), "."));
            }
            System.out.println();
        }
    }
}
