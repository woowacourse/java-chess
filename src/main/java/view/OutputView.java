package view;

import controller.ChessBoardDto;
import controller.ScoreDto;

public final class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 새 게임 시작 : new");
        System.out.println("> 게임 불러오기 : load");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 점수 : status");
        System.out.println();
    }

    public static void printNotice(final String message) {
        System.out.println(System.lineSeparator() + message);
    }

    public static void printErrorMessage(final String message) {
        System.out.printf("[ERROR] : %s%n", message);
    }

    public static void printChessBoard(final ChessBoardDto chessBoardDto) {
        System.out.print(System.lineSeparator());

        for (int i = 0; i < 8; i++) {
            System.out.printf("%s  %d%n", chessBoardDto.getRows().get(i), 8 - i);
        }

        System.out.println(System.lineSeparator() + "abcdefgh" + System.lineSeparator());
    }

    public static void printScore(final ScoreDto scoreDto) {
        System.out.printf("White : %.1f%n", scoreDto.getWhiteScore());
        System.out.printf("Black : %.1f%n", scoreDto.getBlackScore());
        System.out.println(scoreDto.getWinner());
    }

}
