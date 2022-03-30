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
        int rankIndex = 8;
        System.out.println();
        for (int i = 0; i < board.size(); i++) {
            final Piece piece = board.get(pieces.get(i));
            System.out.print(piece.getName());
            rankIndex = separateRank(i, rankIndex);
        }
        System.out.println("abcdefgh");
    }

    private static int separateRank(final int index, int rankIndex) {
        if (index % FILE_SIZE == FILE_END_NUMBER) {
            System.out.println(" " + rankIndex);
            return rankIndex - 1;
        }
        return rankIndex;
    }

    public static void printTurnMessage(final String name) {
        System.out.printf("%s 턴 : ", name);
    }

    public static void printScore(final String turn, final double score) {
        System.out.printf("%s : %.1f점%n", turn, score);
    }

    public static void printResult(final String turn, final String result) {
        System.out.printf("%s : %s%n", turn, result);
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
    public static void printNewLine() {
        System.out.println();
    }
}
