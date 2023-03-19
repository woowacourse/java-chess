package chess.view;

import java.util.Map;

public class OutputView {
    private static final int CHESS_BOARD_AREA = 64;
    private static final int CHESS_BOARD_WIDTH = 8;
    private static final String EMPTY_SQUARE_DISPLAY = ".";

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(Map<Integer, String> pieces) {
        for (int i = 0; i < CHESS_BOARD_AREA; i++) {
            if (i % CHESS_BOARD_WIDTH == 0) {
                System.out.println();
            }
            if (pieces.containsKey(i)) {
                System.out.print(pieces.get(i));
                continue;
            }
            System.out.print(EMPTY_SQUARE_DISPLAY);
        }
        System.out.println();
    }
}
