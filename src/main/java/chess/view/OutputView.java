package chess.view;

import java.util.Map;
import java.util.stream.IntStream;

public class OutputView {

    private static final int MAP_SIZE = 64;
    private static final int LINE_SIZE = 8;

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final Map<Integer, String> pieces) {
        IntStream.range(0, MAP_SIZE).forEach((i) -> {
            if (i % LINE_SIZE == 0) {
                System.out.println();
            }
            System.out.print(pieces.getOrDefault(i, "."));
        });
        System.out.println();
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
