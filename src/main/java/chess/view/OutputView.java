package chess.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;

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

    public static void printBoard(List<List<String>> nameBoard) {
        for (List<String> names : nameBoard) {
            final String rowNames = String.join("", names);
            System.out.println(rowNames);
        }
        System.out.println();
    }

    public static void printCurrentTurn(String turnDisplayName) {
        printMessage("현재 " + turnDisplayName + "턴입니다.");
    }

    public static void printScoreBySide(ScoreBySideDto scoreBySideDto) {
        Map<String, BigDecimal> scoreBySideForPrint = scoreBySideDto.getScoreBySideForPrint();
        for (String sideName : scoreBySideForPrint.keySet()) {
            printMessage(sideName + "Score : " + scoreBySideForPrint.get(sideName) + "점");
        }
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }
}
