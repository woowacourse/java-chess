package chess.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public static final String TEAN_IN_TURN_NOTICE = "%s팀 차례 입니다.\n";
    public static final int DRAW = 2;
    private static final int CHESS_BOARD_AREA = 64;
    private static final int CHESS_BOARD_WIDTH = 8;
    private static final String EMPTY_SQUARE_DISPLAY = ".";
    private static final String NEXT_LINE = "\n";
    private static final String START_GAME_NOTICE = "> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String END_GAME_NOTICE = "게임을 종료합니다.";
    public static final String DRAW_NOTICE = "점수가 동일하여 무승부 입니다.";
    public static final String RESULT_NOTICE = "%s팀이 승리하였습니다.\n";
    public static final int FIRST_INDEX = 0;
    public static final int ONLY = 1;
    public static final String DELIMITER = " : ";


    public void printStartNotice() {
        System.out.println(START_GAME_NOTICE);
    }

    public void printEndNotice() {
        System.out.println(END_GAME_NOTICE);
    }

    public void printCheckNewGameNotice() {
        System.out.println("> 새롭게 게임을 시작하시겠습니까? (예: y, 아니오: n)");
    }

    public void printEnterSavedGameRoomNumberNotice() {
        System.out.println("> 이어서 진행할 게임의 방 번호를 입력하세요. ex) 1");
    }

    public void printScore(Map<String, Double> scores) {
        for (Entry<String, Double> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + DELIMITER + entry.getValue());
        }
    }

    public void printTeamInTurn(String team) {
        System.out.printf(TEAN_IN_TURN_NOTICE, team);
    }

    public void printStartWinningTeam(List<String> teams) {
            if (teams.size() == ONLY) {
            System.out.printf(RESULT_NOTICE, teams.get(FIRST_INDEX));
        }
        if (teams.size() == DRAW) {
            System.out.println(DRAW_NOTICE);
        }
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
