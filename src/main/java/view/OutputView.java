package view;

import domain.board.Board;
import domain.board.RowOfBoard;

public class OutputView {

    private static final String NEW_LINE = "\n";

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RowOfBoard row : board.getBoard()) {
            stringBuilder.append(String.join("", row.getRowOfBoard()))
                    .append(NEW_LINE);
        }

        System.out.println(stringBuilder.toString());
    }
}
