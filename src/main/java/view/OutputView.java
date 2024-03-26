package view;

import java.util.List;

public class OutputView {

    public void printGameGuide() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printBoard(List<List<String>> board) {
        board.forEach(this::printRow);
    }

    private void printRow(List<String> row) {
        row.forEach(System.out::print);
        System.out.println();
    }
}
