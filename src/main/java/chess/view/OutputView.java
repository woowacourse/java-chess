package chess.view;

import chess.dto.BoardDto;

public class OutputView {

    private static final String BLANK_LINE = System.lineSeparator();

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static void printGameInstructions() {
        String instructionMessage = GAME_START_MESSAGE + BLANK_LINE
                + START_COMMAND_MESSAGE + BLANK_LINE
                + END_COMMAND_MESSAGE + BLANK_LINE
                + MOVE_COMMAND_MESSAGE;

        print(instructionMessage);
    }

    public static void printBoard(BoardDto dto) {
        StringBuilder builder = new StringBuilder();
        for (String rowDisplay : dto.getDisplay()) {
            builder.append(rowDisplay)
                    .append(BLANK_LINE);
        }
        print(builder.toString());
    }

    private static void print(String value) {
        System.out.println(value);
    }
}
