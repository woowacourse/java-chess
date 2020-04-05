package chess.view;

import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    private static final String EMPTY_SQUARE = ".";
    private static final int MAX_BOARD_SIZE = 8;

    @Override
    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void printBoard(final List<String> positions, final Map<String, String> board) {
        for (int i = 1; i <= positions.size(); i++) {
            String piece = board.get(positions.get(i - 1));
            System.out.print(printPiece(piece));
            checkNewLine(i);
        }
        System.out.println();
    }

    private void checkNewLine(final int i) {
        if (i % MAX_BOARD_SIZE == 0) {
            System.out.println();
        }
    }

    private String printPiece(String piece) {
        if (piece == null) {
            return EMPTY_SQUARE;
        }
        return piece;
    }

    @Override
    public void printStatus(double calculateScore, String teamName) {
        System.out.println(String.format("%s 팀의 점수는 %.1f 점입니다.", teamName, calculateScore));
    }

    @Override
    public void printWinner(String teamName) {
        System.out.println(String.format("%s 팀이 이겼습니다.", teamName));
    }
}
