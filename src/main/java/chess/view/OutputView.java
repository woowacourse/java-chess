package chess.view;

import java.util.List;

public final class OutputView {

    public void printStartPrefix(boolean isGameExist) {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 새로운 게임 시작 : start\n"
                + "> 게임 일시 정지 : pause\n"
                + "> 게임 점수 계산 후 종료 : status\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        if (isGameExist) {
            System.out.println("> 진행중인 게임 불러오기 : fetch");
        }
    }

    public void printChessState(List<List<String>> chessBoardState) {
        StringBuilder chessBoardView = new StringBuilder();
        chessBoardView.append(System.lineSeparator());

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

    public void printResult(List<String> results) {
        for (String result : results) {
            System.out.println(result);
        }
    }

    public void printPausedMessage() {
        System.out.println("게임이 일시정지 되었습니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
