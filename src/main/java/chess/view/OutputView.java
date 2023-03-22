package chess.view;

import java.util.List;

import chess.domain.dto.GameStatusDto;

public class OutputView {

    private static final String MESSAGE_PREFIX = "> ";

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printStartMessage() {
        printMessage("체스 게임을 시작합니다.");
    }

    public static void printCommandMessage() {
        printMessage("게임 시작 : start");
        printMessage("게임 종료 : end");
        printMessage("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    private static void printMessage(final String message) {
        System.out.println(MESSAGE_PREFIX + message);
    }

    public static void printGameStatus(final GameStatusDto gameStatusDto) {
        List<String> gameStatus = gameStatusDto.getGameStatus();
        gameStatus.forEach(OutputView::printRankStatus);
    }

    private static void printRankStatus(final String rankStatus) {
        System.out.println(rankStatus);
    }
}
