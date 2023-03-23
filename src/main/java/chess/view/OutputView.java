package chess.view;

import java.util.Map;

public class OutputView {
    private static final int CHESS_BOARD_AREA = 64;
    private static final int CHESS_BOARD_WIDTH = 8;
    private static final String EMPTY_SQUARE_DISPLAY = ".";
    private static final String NEXT_LINE = "\n";
    private static final String GAME_START_NOTICE = "> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public void printGameStartNotice() {
        System.out.println(GAME_START_NOTICE);
    }

    public void printChessBoard(final Map<Integer, String> pieces) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < CHESS_BOARD_AREA; i++) {
            addNextLine(result, i);
            result.append(getSquareDisplay(pieces, i));
        }
        result.append(NEXT_LINE);

        System.out.println(result);
    }

    private void addNextLine(final StringBuilder result, final int index) {
        if (index % CHESS_BOARD_WIDTH == 0) {
            result.append(NEXT_LINE);
        }
    }

    private String getSquareDisplay(final Map<Integer, String> pieces, final int index) {
        if (pieces.containsKey(index)) {
            return pieces.get(index);
        }
        return EMPTY_SQUARE_DISPLAY;
    }
}
