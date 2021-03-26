package chess.view;

import chess.domain.game.GameVisual;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();

    public static void printGuideMessage() {
        System.out.println("체스 게임을 시작합니다.\n"
                + "게임 시작 : start\n"
                + "게임 종료 : end\n"
                + "게임 이동 : move source위치 target위치 - 예: move b2 b3"
                + NEWLINE);
    }

    public static void print(GameVisual gameVisual) {

    }

    public static void printFinishedMessage() {
        System.out.println(NEWLINE + "게임이 종료되었습니다.");
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(NEWLINE + e.getMessage());
    }
}
