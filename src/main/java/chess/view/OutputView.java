package chess.view;

import java.util.List;

public class OutputView {
    private static final int SIZE = 8;

    public void printAnnounce() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printInitChessBoard(List<String> symbols) {
        int count = 0;

        for (String symbol : symbols) {
            if (count % SIZE == 0) {
                printNextLine();
            }
            System.out.print(symbol);
            count ++;
        }
        printNextLine();
        printNextLine();
    }

    private void printNextLine() {
        System.out.println();
    }
}
