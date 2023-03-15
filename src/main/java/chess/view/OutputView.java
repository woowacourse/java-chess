package chess.view;

import java.util.List;

public class OutputView {

    private static final String GAME_START = "체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_REQUEST = String.format("게임 시작은 %s, 종료는 %s 명령을 입력하세요.",
            Command.START.getAnswer(), Command.END.getAnswer());

    public void printStartMessage() {
        System.out.println(GAME_START);
        System.out.println(GAME_COMMAND_REQUEST);
    }

    public void printBoard(List<List<String>> board) {
        for (List<String> rank : board) {
            printRank(rank);
            System.out.println();
        }
    }

    private void printRank(final List<String> rank) {
        for (String value : rank) {
            System.out.print(value);
        }
    }

}
