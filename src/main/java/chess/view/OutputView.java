package chess.view;

import chess.board.dto.BoardDto;
import java.util.List;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    public static void printGameStartMessage() {
        printMessage("> 체스 게임을 시작합니다.");
    }

    public static void printGameCommandInputMessage() {
        printMessage("> 게임 시작 : start" + NEWLINE
                + "> 게임 종료 : end" + NEWLINE
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(BoardDto boardDto) {
        final List<List<String>> nameBoard = boardDto.getNameBoard();

        for (List<String> names : nameBoard) {
            final String rowNames = String.join("", names);
            System.out.println(rowNames);
        }
        System.out.println();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }
}
