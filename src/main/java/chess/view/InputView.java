package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String END_COMMAND = "end";
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int POSITION_LENGTH = 2;
    public static final String STATUS_COMMAND = "status";

    public static boolean inputInitialCommand() {
        try {
            return validateFirstTurnCommand(SCANNER.nextLine().toLowerCase());
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputInitialCommand();
        }
    }

    private static boolean validateFirstTurnCommand(final String command) {
        if (START_COMMAND.equals(command)) {
            return true;
        }
        if (END_COMMAND.equals(command)) {
            return false;
        }
        throw new IllegalStateException("잘못된 커맨드 입력입니다.");
    }

    public static List<String> inputRuntimeCommand() {
        try {
            final List<String> commands = Arrays.asList(SCANNER.nextLine().split(" "));
            validateRuntimeCommand(commands);
            return commands;
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputRuntimeCommand();
        }
    }

    private static void validateRuntimeCommand(final List<String> commands) {
        if (END_COMMAND.equals(commands.get(0))) {
            return;
        }
        if (isValidMoveCommand(commands)) {
            return;
        }
        throw new IllegalStateException("잘못된 커맨드 입력입니다.");
    }

    private static boolean isValidMoveCommand(final List<String> commands) {
        return MOVE_COMMAND.equals(commands.get(0)) && commands.size() == MOVE_COMMAND_SIZE &&
                commands.get(1).length() == POSITION_LENGTH && commands.get(2).length() == POSITION_LENGTH;
    }

    public static boolean isStatusInput() {
        return STATUS_COMMAND.equals(SCANNER.nextLine());
    }
}
