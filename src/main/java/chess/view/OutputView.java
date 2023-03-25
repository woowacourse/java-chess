package chess.view;

import chess.board.dto.BoardDto;
import chess.piece.Side;
import java.util.List;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();

    public static void printGameStartMessage() {
        printMessage("> 체스 게임을 시작합니다.");
    }

    public static void printGameCommandInputMessage() {
        printMessage("> 게임 시작 : start" + NEWLINE
                + "> 게임 중단 : end" + NEWLINE
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

    public static void printCommandAfterGameEndInputMessage() {
        printMessage("> 게임 결과 확인 : status" + NEWLINE
                + "> 게임 종료 : end"
        );
    }

    public static void printGameResult(final Side winner, final double whiteScore, final double blackScore) {
        System.out.printf("승자 : %s%n"
                + "흰색 진영 : %.1f점%n"
                + "검은색 진영 : %.1f점", winner, whiteScore, blackScore);
    }
}
