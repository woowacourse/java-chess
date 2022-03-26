package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final int FILE_SIZE = 8;
    private static final int FILE_END_NUMBER = 7;

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + NEW_LINE +
            "> 게임 시작 : start" + NEW_LINE +
            "> 게임 종료 : end" + NEW_LINE +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        final List<Position> pieces = new ArrayList<>(board.keySet());
        for (int i = 0; i < board.size(); i++) {
            final Piece piece = board.get(pieces.get(i));
            System.out.print(piece.getName());
            separateRank(i);
        }
        System.out.println();
    }

    private static void separateRank(final int index) {
        if (index % FILE_SIZE == FILE_END_NUMBER) {
            System.out.println();
        }
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printTurnMessage(final String name) {
        System.out.printf("%s의 턴입니다.%n", name);
    }

    public static void printResult(final String turn, final String result) {
        System.out.printf("%s은 %s하셨습니다.%n", turn, result);
    }

    public static void printScore(final String turn, final double score) {
        System.out.printf("%s은 %.1f점 입니다.%n", turn, score);
    }
}
