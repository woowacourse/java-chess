package chess2.view2;

import chess2.dto2.BoardViewDto;

public class OutputView {

    private static final String BLANK_LINE = System.lineSeparator();

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String END_COMMAND_MESSAGE = "> 프로그램 종료 : end";
    private static final String MOVE_COMMAND_MESSAGE = "> 체스 말 이동 : move source target (예. move b2 b3)";

    public void printGameInstructions() {
        String instructionMessage = GAME_START_MESSAGE + BLANK_LINE
                + START_COMMAND_MESSAGE + BLANK_LINE
                + END_COMMAND_MESSAGE + BLANK_LINE
                + MOVE_COMMAND_MESSAGE;

        print(instructionMessage);
    }

    public void printBoard(BoardViewDto dto) {
        StringBuilder builder = new StringBuilder();
        for (String rowDisplay : dto.display()) {
            builder.append(rowDisplay)
                    .append(BLANK_LINE);
        }
        print(builder.toString());
    }

    public static void print(String value) {
        System.out.println(value);
    }
}
