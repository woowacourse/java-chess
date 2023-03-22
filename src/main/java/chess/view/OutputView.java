package chess.view;

import java.util.List;

public final class OutputView {

    public void printStartPrefix() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessState(List<List<String>> chessBoardState) {
        StringBuilder chessBoardView = new StringBuilder();
        for (List<String> ranks : chessBoardState) {
            appendRankSymbols(chessBoardView, ranks);
            chessBoardView.append(System.lineSeparator());
        }

        System.out.print(chessBoardView);
    }

    private void appendRankSymbols(StringBuilder chessBoardView, List<String> ranks) {
        for (String symbol : ranks) {
            chessBoardView.append(symbol);
        }
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
