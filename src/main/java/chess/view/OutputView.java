package chess.view;

import chess.controller.dto.BoardDto;

import java.util.List;

public class OutputView {

    public static void printInitialMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다.\n"
                        + "> 게임 시작 : start\n"
                        + "> 게임 종료 : end\n"
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(BoardDto boardDto) {
        boardDto.getBoard().forEach(line -> System.out.println(printLine(line)));
        System.out.println();
    }

    private static String printLine(List<String> line) {
        return String.join("", line);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printFinishMessage() {
        System.out.println("> 게임이 종료되었습니다.");
    }
}
