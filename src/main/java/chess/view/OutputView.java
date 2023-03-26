package chess.view;

import chess.controller.ScoreDto;

import static chess.controller.state.CommandType.*;

public final class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3";

    public static void print(final String message) {
        System.out.println(message);
    }

    public static void printStartMessage() {
        print(String.format(START_MESSAGE, START.name().toLowerCase(),
                END.name().toLowerCase(), MOVE.name().toLowerCase()));
    }

    public static void printStatus(ScoreDto scoreDto) {
        print(String.format("Black : %f, White : %f\n우승팀 : %s",
                scoreDto.getBlackScore(), scoreDto.getWhiteScore(), scoreDto.getWinner()));
        System.out.println(System.lineSeparator());
    }
}
