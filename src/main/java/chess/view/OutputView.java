package chess.view;

import chess.dto.GameStatusDto;
import chess.dto.ScoreDto;

public class OutputView {

    private static final String START_MESSAGE_PREFIX = "> ";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStartMessage() {
        printEachStartPrefixMessage("체스 게임을 시작합니다.");
        printCommandMessage();
    }

    private static void printCommandMessage() {
        printEachStartPrefixMessage("게임 시작 : start");
        printEachStartPrefixMessage("게임 종료 : end");
        printEachStartPrefixMessage("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private static void printEachStartPrefixMessage(final String message) {
        System.out.println(START_MESSAGE_PREFIX + message);
    }

    public static void printGameStatus(final GameStatusDto gameStatusDto) {
        gameStatusDto.getGameStatus().forEach(OutputView::printGameStatusRow);
        System.out.println();
    }

    public static void printScore(final ScoreDto scoreDto) {
        System.out.println("백팀 점수: " + scoreDto.getWhiteScore());
        System.out.println("흑팀 점수: " + scoreDto.getBlackScore());
        System.out.println();
    }

    private static void printGameStatusRow(final String row) {
        System.out.println(row);
    }
}
