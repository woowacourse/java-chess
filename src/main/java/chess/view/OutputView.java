package chess.view;

import java.util.List;

public class OutputView {
    private static final int SIZE = 8;

    public void printAnnounce() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
