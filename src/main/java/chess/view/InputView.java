package chess.view;

import java.util.Scanner;

public class InputView {
    private static final String START_GAME_COMMAND = "start";
    private static final String END_GAME_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int MOVE_COMMAND_LENGTH = 3;

    private final Scanner scanner = new Scanner(System.in);

    public void terminate() {
        scanner.close();
    }

    public String getStartOrEndInput() {
        try {
            String input = scanner.nextLine();
            validateStartOrEndInput(input);
            return input;
        } catch (IllegalArgumentException e) {
            return getStartOrEndInput();
        }
    }

    private void validateStartOrEndInput(String input) {
        if (!input.equals(START_GAME_COMMAND) && !input.equals(END_GAME_COMMAND)) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }
    }

    public String getCommand() {
        String input = scanner.nextLine();

        if (input.startsWith(MOVE_COMMAND)) {
            String[] commands = input.split(MOVE_COMMAND_DELIMITER);
            validateMoveCommandFormat(commands);
            validateMoveCommandKeyword(commands);
            return input;
        }

        if (input.startsWith(STATUS_COMMAND)) {
            validateStatusInput(input);
            return input;
        }

        throw new IllegalArgumentException("status 혹은 move source위치 target위치 형식으로 입력해주세요.");
    }

    private void validateStatusInput(String input) {
        if (!input.equals(STATUS_COMMAND)) {
            throw new IllegalArgumentException("status 를 정확히 입력해주세요");
        }
    }

    private void validateMoveCommandFormat(String[] commands) {
        if (commands.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    private void validateMoveCommandKeyword(String[] commands) {
        if (!commands[0].equals(MOVE_COMMAND)) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }
}
