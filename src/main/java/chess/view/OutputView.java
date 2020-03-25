package chess.view;

import java.util.List;

public class OutputView {
    public static void printStartInformation() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(List<List<String>> board) {
        for (List<String> raw : board) {
            for (String acronym : raw) {
                System.out.print(acronym);
            }
            System.out.println();
        }
    }
}
