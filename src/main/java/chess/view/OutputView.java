package chess.view;

import chess.dto.console.BoardResponseDto;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + NEW_LINE +
                "> 게임 시작 : start" + NEW_LINE +
                "> 게임 종료 : end" + NEW_LINE +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final BoardResponseDto consoleBoardDto) {
        final List<List<String>> names = consoleBoardDto.getNames();
        int rowIndex = 8;
        for (List<String> namesOnSameRow : names) {
            printNamesOnSameRow(namesOnSameRow, rowIndex--);
        }
        System.out.println("abcdefgh");
    }

    private static void printNamesOnSameRow(final List<String> names, final int index) {
        for (String name : names) {
            System.out.print(name);
        }
        System.out.println(" " + index);
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
