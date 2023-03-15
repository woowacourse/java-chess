package chess.view;

import java.util.List;

public class OutputView {

    private static final String GAME_START = "체스 게임을 시작합니다.";

    public void printStartMessage() {
        System.out.println(GAME_START);
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
