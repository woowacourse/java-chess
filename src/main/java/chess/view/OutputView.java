package chess.view;

import java.util.Map;

public class OutputView {
    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final Map<Integer, String> pieces) {
        for (int i = 0; i < 64; i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            if (pieces.containsKey(i)) {
                System.out.print(pieces.get(i));
                continue;
            }
            System.out.print(".");
        }
        System.out.println();
    }
}
