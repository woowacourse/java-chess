package view;

import common.ExecuteContext;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String START_CHESS_GAME_MESSSAGE = "> 체스 게임을 시작합니다.";
    private static final String INTRODUCE_GAME_START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String INTRODUCE_GAME_END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String INTRODUCE_GAME_MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String INVALID_INPUT_ERROR_MESSAGE = "입력이 잘못 되었습니다. 다시 입력해 주세요.";
    private static final String COMMAND_INPUT_DELIMITER = " ";
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final int COMMAND_INDEX = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public boolean getEndIntent() {
        System.out.println(START_CHESS_GAME_MESSSAGE);
        System.out.println(INTRODUCE_GAME_START_COMMAND_MESSAGE);
        System.out.println(INTRODUCE_GAME_END_COMMAND_MESSAGE);
        System.out.println(INTRODUCE_GAME_MOVE_COMMAND_MESSAGE);
        final String order = ExecuteContext.workWithExecuteStrategy(() -> {
            final String input = scanner.nextLine();
            checkExpectedInput(input, START, END);
            return input;
        });
        return order.equals(END);
    }

    public List<String> getCommand() {
        final String[] inputs = scanner.nextLine().split(COMMAND_INPUT_DELIMITER);
        final String command = inputs[COMMAND_INDEX];
        checkExpectedInput(command, MOVE, START, END);
        return Arrays.stream(inputs).collect(Collectors.toList());
    }

    private void checkExpectedInput(final String input, final String... expectedInputs) {
        checkBlank(input);
        if (Arrays.asList(expectedInputs).contains(input)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }
}
